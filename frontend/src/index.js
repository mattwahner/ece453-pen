

import SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';

const client = Stomp.over(() => {
    return new SockJS('http://localhost/api/gs-points');
});

client.connect({}, () => {
    client.subscribe('/topic/points', (message) => {
        document.getElementById("index").innerHTML = message;
    });
});
