

import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

const client = Stomp.over(() => {
    return new SockJS('http://localhost:8080/app');
});

client.connect({}, () => {
    client.subscribe('/topic/points', (message) => {
        document.getElementById("index").innerHTML = message;
    });
});

