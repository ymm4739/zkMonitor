var ws = null;
if(ws==null) {
    var url = window.location.href;
    var path = url.substr(url.lastIndexOf("path=") + 5);
    ws = new WebSocket("ws://localhost:8081/websocket/zk/" + window.btoa(path));
//var ws = new WebSocket("wss://echo.websocket.org");
    ws.onopen = function (evt) {
        console.log("Connection open ...");

    };

    ws.onmessage = function (evt) {
        if(window.doWebsocket && typeof doWebsocket==="function") {
            doWebsocket(evt.data);
        }
    };

    ws.onclose = function (evt) {
        console.log("Connection closed.");
    };
}
