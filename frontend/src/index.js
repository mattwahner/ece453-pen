
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './main.css';

const MIN_X = 180 - 45;
const MAX_X = 180 + 45;
const MIN_Y = 180 - 45;
const MAX_Y = 180 + 45;
const MIN_Z = 180 - 45;
const MAX_Z = 180 + 45;

class CanvasHandler {

    constructor(client) {
        this.client = client;
        this.canvas = document.getElementById("main");
        this.colorModal = document.getElementById("color-modal");
        this.colorModalFeedback = document.getElementById("color-modal-feedback");
        this.thicknessModal = document.getElementById("thickness-modal");
        this.thicknessModalFeedback = document.getElementById("thickness-modal-feedback");
        this.context = this.canvas.getContext("2d");
        this.init();
    }

    init() {
        window.addEventListener('resize', (event) => {
            this.sendCanvas();
        });
        this.sendCanvas();
        this.initState();
    }

    initState() {
        this.lastPoint = null;
        this.drawing = false;
        this.selectingColor = false;
        this.selectingThickness = false;
        this.clearingCanvas = false;
        this.thickness = 1;
        this.color = {
            red: 0,
            green: 0,
            blue: 0
        };
    }

    updateButtons(buttons) {
        this.updateState(buttons);
        this.colorModal.style.display = this.selectingColor ? "block" : "none";
        this.thicknessModal.style.display = this.selectingThickness ? "block" : "none";
        if (this.clearingCanvas) {
            this.clearCanvas();
            this.lastPoint = null;
        }
        if (!this.drawing)
            this.lastPoint = null;
    }

    updateState(buttons) {
        this.selectingColor = buttons.leftPressed;
        this.selectingThickness = buttons.rightPressed;
        this.clearingCanvas = buttons.middleDoublePressed;
        this.drawing = buttons.middlePressed && !this.clearingCanvas && !this.selectingColor && !this.selectingThickness;
    }

    clearCanvas() {
        this.context.clearRect(0, 0, this.canvas.width, this.canvas.height);
    }

    updateGyro(gyro) {
        this.updateColor(gyro);
        this.updateThickness(gyro);
    }

    static clampGyro(gyro, max) {
        let clampedX = gyro.x > MAX_X ? MAX_X : (gyro.x < MIN_X ? MIN_X : gyro.x);
        let clampedY = gyro.y > MAX_Y ? MAX_Y : (gyro.y < MIN_Y ? MIN_Y : gyro.y);
        let clampedZ = gyro.z > MAX_Z ? MAX_Z : (gyro.z < MIN_Z ? MIN_Z : gyro.z);

        let scaledX = max * (clampedX - MIN_X) / (MAX_X - MIN_X);
        let scaledY = max * (clampedY - MIN_Y) / (MAX_Y - MIN_Y);
        let scaledZ = max * (clampedZ - MIN_Z) / (MAX_Z - MIN_Z);

        return {
            x: scaledX,
            y: scaledY,
            z: scaledZ
        }
    }

    setColor(color) {
        this.colorModalFeedback.style.backgroundColor = `rgb(${color.red}, ${color.green}, ${color.blue})`;
        if (this.selectingColor)
            this.color = color;
        this.updateDrawing();
    }

    setThickness(thickness) {
        this.thicknessModalFeedback.innerText = `${thickness}`;
        if (this.selectingThickness)
            this.thickness = thickness;
        this.updateDrawing();
    }

    updateDrawing() {
        this.client.send('/app/drawing', {}, JSON.stringify({
            "red": this.color.red,
            "green": this.color.green,
            "blue": this.color.blue,
            "thickness": this.thickness
        }));
    }

    updateColor(gyro) {
        let position = CanvasHandler.clampGyro(gyro, 255);
        this.setColor({
            red: Math.floor(position.x),
            green: Math.floor(position.y),
            blue: Math.floor(position.z)
        });
    }

    updateThickness(gyro) {
        let position = CanvasHandler.clampGyro(gyro, 5);
        this.setThickness(Math.floor(position.x));
    }

    drawPoint(point) {
        if (!this.lastPoint) {
            this.lastPoint = point;
        } else if (this.drawing) {
            this.context.beginPath();
            this.context.lineWidth = this.thickness;
            this.context.strokeStyle = `rgb(${this.color.red}, ${this.color.green}, ${this.color.blue})`;
            this.context.moveTo(this.lastPoint.x, this.lastPoint.y);
            this.context.lineTo(point.x, point.y);
            this.context.stroke();
            this.lastPoint = point;
        }
    }

    sendCanvas() {
        this.canvas.width = window.innerWidth;
        this.canvas.height = window.innerHeight;
        this.client.send('/app/window', {}, JSON.stringify({
            "width": this.canvas.width,
            "height": this.canvas.height
        }));
    }

}

class ClientHandler {

    constructor(debug) {
        this.debug = debug;
        this.initStompClient();
    }

    initStompClient() {
        this.client = Stomp.over(() => new SockJS('/api/gs-points'));
        if (!this.debug)
            this.client.debug = null;
        this.client.connect({}, () => {
            this.subscribeToAll(this.client);
            this.canvasHandler = new CanvasHandler(this.client);
        });
    }

    logResponse(message) {
        let response = JSON.parse(message.body);
        if (this.debug)
            console.log(response);
        return response;
    }

    subscribeToAll(client) {
        this.subscribeButtons(client);
        this.subscribeGyro(client);
        this.subscribePoint(client);
    }

    subscribeButtons(client) {
        client.subscribe('/topic/buttons', (message) => {
            let response = this.logResponse(message);
            this.canvasHandler.updateButtons(response);
        });
    }

    subscribeGyro(client) {
        client.subscribe('/topic/gyro', (message) => {
            let response = this.logResponse(message);
            this.canvasHandler.updateGyro(response);
        });
    }

    subscribePoint(client) {
        client.subscribe('/topic/point', (message) => {
            let response = this.logResponse(message);
            this.canvasHandler.drawPoint(response);
        });
    }
}

new ClientHandler(true);
