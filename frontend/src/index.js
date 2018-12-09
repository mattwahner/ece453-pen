
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './main.css';

class CanvasHandler {

    constructor(client) {
        this.client = client;
        this.canvas = document.getElementById("main");
        this.init();
    }

    init() {
        window.addEventListener('resize', (event) => {
            if (this.client.connected) {
                this.sendCanvas();
            }
        });
        this.sendCanvas();
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

    constructor() {
        this.client = Stomp.over(() => new SockJS('/api/gs-points'));
        this.client.connect({}, () => {
            this.subscribeToAll(this.client);
        });
    }

    subscribeToAll(client) {
        this.subscribeButtons(client);
        this.subscribeGyro(client);
        this.subscribePoint(client);
    }

    subscribeButtons(client) {
        client.subscribe('/topic/buttons', (message) => {
            console.log(JSON.parse(message.body))
        });
    }

    subscribeGyro(client) {
        client.subscribe('/topic/gyro', (message) => {
            console.log(JSON.parse(message.body))
        });
    }

    subscribePoint(client) {
        client.subscribe('/topic/point', (message) => {
            let response = JSON.parse(message.body);
            console.log(response);
            const context = canvas.getContext("2d");
            context.fillStyle = "red";
            response.points.forEach((point) => {
                context.beginPath();
                context.lineWidth = 0;
                context.arc(point.x, point.y, 5, 0, 2 * Math.PI);
                context.fillStyle = "black";
                context.fill();
                context.stroke();
            });
        });
    }

}
