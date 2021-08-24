<%--
  Created by IntelliJ IDEA.
  User: 86138
  Date: 2021/4/26
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>WS</title>
</head>
<body>




</body>

<script>

    "use strict";
    let ws = null;

    window.onload = function () {
        let host = `ws://localhost:8080/servlet_websocket_war_exploded/chat`
        ws_connect(host).then(() =>{
            ws_send("hello webSocket...");
        });


        ws_send("hello webSocket...");
    }

    async function ws_connect(host) {
        if ('WebSocket' in window) {
            ws = await new WebSocket(host);
        } else if ('MozWebSocket' in window) {
            ws = await new MozWebSocket(host);
        } else {
            console.log('Error: WebSocket is not supported by this browser.');
            return;
        }

        // open → message → close。
        // open —— 连接已建立，
        // message —— 接收到数据，
        // error —— WebSocket 错误，
        // close —— 连接已关闭。

         ws.onopen = function () {
             console.log('Info: WebSocket connection opened.');
             // ws_send("hello webSocket...");
         };

         ws.onmessage = function (message) {
             console.log(message.data);
         };

         ws.onclose = function () {
             console.log('Info: WebSocket closed.');
         };



         ws.onerror = function(error) {
             alert(`[error] ${error.message}`);
         };
    };

    function ws_send(msg) {
        ws.send(msg);
    }










</script>
</html>
