

import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import './main.css';

const client = Stomp.over(() => {
    return new SockJS('http://localhost/api/gs-points');
});

window.addEventListener('resize', (event) => {
    if (client.connected) {
        sendCanvas();
    }
});

client.connect({}, () => {
    sendCanvas();
    client.subscribe('/topic/points', (message) => {
        console.log(message);
        client.send('/app/test', {}, 'test');
    });
});

const sendCanvas = () => {
    let canvas = document.getElementById("main");
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    client.send('/app/window', {}, JSON.stringify({
        "width": canvas.width,
        "height": canvas.height
    }));
}
