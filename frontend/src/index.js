
import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './main.css';

const canvas = document.getElementById("main");

const client = Stomp.over(() => {
    return new SockJS('/api/gs-points');
});

window.addEventListener('resize', (event) => {
    if (client.connected) {
        sendCanvas();
    }
});

const subscribe = (client) => {
    subscribeButtons(client);
    subscribeGyro(client);
    subscribePoints(client);
};

const subscribeButtons = (client) => {
    client.subscribe('/topic/buttons', (message) => {
        console.log(JSON.parse(message.body))
    });
};

const subscribeGyro = (client) => {
    client.subscribe('/topic/gyro', (message) => {
        console.log(JSON.parse(message.body))
    });
};

const subscribePoints = (client) => {
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
};

client.connect({}, () => {
    sendCanvas();
    subscribe(client);
});

const sendCanvas = () => {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    client.send('/app/window', {}, JSON.stringify({
        "width": canvas.width,
        "height": canvas.height
    }));
};
