var wsocket;
var $message;
var $chatWindow;

function onMessageReceived(evt) {
    var msg = JSON.parse(evt.data); // native API
    var $messageLine = $('<tr><td class="user label label-info">' + msg.sender
                    + '</td><td class="message badge">' + msg.message
                    + '</td></tr>');
    $chatWindow.append($messageLine);
    $('#response tr:last')[0].scrollIntoView();
}
function sendMessage() {
    var msg = JSON.stringify({'message': $message.val(), "sender": nickName, "received": ""});
    wsocket.send(msg);
    $message.val('').focus();
}

function connectToChatserver() {
    wsocket = new WebSocket(serviceLocation);
    wsocket.onmessage = onMessageReceived;
}

$(document).ready(function() {
    $message = $('#message');
    $chatWindow = $('#response');
    $message.focus();
    connectToChatserver();

    $('#do-chat').submit(function(evt) {
        evt.preventDefault();
        sendMessage();
    });
});
