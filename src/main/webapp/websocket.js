var wsUri = "ws://" + document.location.host + "/demo-chat/websocket";
var websocket = new WebSocket(wsUri);

var username;
websocket.onopen = function (evt) {
    onOpen(evt)
};
websocket.onmessage = function (evt) {
    onMessage(evt)
};
websocket.onerror = function (evt) {
    onError(evt)
};
var output = document.getElementById("output");
var userField = document.getElementById("chat:userField");
var chatlogField = document.getElementById("chat:chatlogField");
var textField = document.getElementById("chat:textField");

function join() {
    username = textField.value;
    websocket.send(username + " joined");
    document.getElementById("chat:join").disabled = true;
}

function send_message() {
    var msg = new Object();
    msg.user = username;
    msg.content = textField.value;
    websocket.send(JSON.stringify(msg));
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onMessage(evt) {
    console.log("onMessage : " + evt.data);
    writeToScreen("RECEIVED: " + evt.data);
    if (evt.data.indexOf("joined") != -1) {
        userField.innerHTML += evt.data.substring(0, evt.data.indexOf(" joined")) + "\n";
    } else {
        var msg = JSON.parse(evt.data)
        chatlogField.innerHTML += msg.content + " said " + msg.user + "\n";
    }
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

