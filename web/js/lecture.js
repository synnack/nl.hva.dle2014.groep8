// WebRTC compatibility glue
window.URL = window.URL || window.webkitURL || window.mozURL;
navigator.getUserMedia  = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

if (typeof RTCPeerConnection !== "undefined") {
    window.RTCPeerConnection = RTCPeerConnection;
} else if (typeof webkitRTCPeerConnection !== "undefined") {
    window.RTCPeerConnection = webkitRTCPeerConnection;
} else if (typeof mozRTCPeerConnection !== "undefined") {
    window.RTCPeerConnection = mozRTCPeerConnection;
} else if (typeof msRTCPeerConnection !== "undefined") {
	window.RTCPeerConnection = msRTCPeerConnection;
} else {
    alert("This browser will not work.");
}

if (typeof RTCSessionDescription !== "undefined") {
    window.RTCSessionDescription = RTCSessionDescription;
} else if (typeof webkitRTCSessionDescription !== "undefined") {
    window.RTCSessionDescription = webkitRTCSessionDescription;
} else if (typeof mozRTCSessionDescription !== "undefined") {
    window.RTCSessionDescription = mozRTCSessionDescription;
} else if (typeof msRTCSessionDescription !== "undefined") {
    window.RTCSessionDescription = msRTCSessionDescription;
} else {
    alert("This browser will not work.");
}


var WebSocketDispatcher = function (url) {
    var handlers = {};
    var socket;
    var queue = [];

    var init_socket = function () {
        socket = new WebSocket(url);
        socket.onmessage = function (event) {
            var message = JSON.parse(event.data);

            dispatch(message.message_type, message.data);
        };

        socket.onopen = function () {
            while (socket.readyState === 1 && queue[0]) {
                socket.send(queue.shift());
            }
        };

        socket.onclose = function () {
            setTimeout(function () { init_socket(); }, 1000);
        };

    };
    init_socket();

    var dispatch = function (message_type, data) {
        if (!handlers[message_type]) {
            return;
        }

        for (var i = 0; i < handlers[message_type].length; i++) {
            handlers[message_type][i](data);
        }
    };

    this.bind = function (message_type, fn) {
        handlers[message_type] = handlers[message_type] || [];
        handlers[message_type].push(fn);
    };

    this.send = function (message_type, data) {
        var message = JSON.stringify({
            'message_type': message_type,
            'data': data
        });
        if (socket.readyState === 1) {
            socket.send(message);
        } else {
            queue.push(message);
        }
    };
};


var Chat = function(wsocket) {
    var $chatWindow;
    var $message;
    
    var Chat = function() {
        $chatWindow = $('#response');
        $message    = $('#message');
        wsocket.bind('CHAT_MESSAGE', messageReceived);
    };
    
    var messageReceived = function(msg) {
        // Nice XSS vulnerability here! Can you exploit it? ;-)
        // Will fix if there's still time! Use W3C DOM API!
        var $messageLine = $('<tr><td class="user label label-info">' + msg.sender
                        + '</td><td class="message badge">' + msg.message
                        + '</td></tr>');
        $chatWindow.append($messageLine);
        $('#response tr:last')[0].scrollIntoView();
    };
    
    this.sendMessage = function(wsocket) {
        var msg =
            {
                'message': $message.val(), 
                "sender": nickName, 
                "received": ""
            };
        wsocket.send('CHAT_MESSAGE', msg);

        $message.val('').focus();
    };

    Chat();
};

var Video = function(wsocket) {
    var offer;
    
    var Video = function() {
        pc = new RTCPeerConnection(null);
        
        
        if (manage) {
            navigator.getUserMedia({audio: true, video: {optional: [ {minWidth:800} ]}}, function (stream) {
                $("video")[0].src = window.URL.createObjectURL(stream);
                pc.addStream(stream);
                $("video")[0].play();
            });
        } else {
           pc.onaddstream = addStreamToVideoElement;
           pc.createOffer(localOffer, error);
        }
        pc.onicecandidate = addExtraCandidate;
        wsocket.bind('OFFER_SDP', offerReceived);
    };
    
    var error = function (e) {
        console.log("An error occured during the set up of the video stream");
        console.log(e);
    };
    
    var addStreamToVideoElement = function(obj) {
            console.log(obj);
            var video = $('video')[0];
            video.src = window.URL.createObjectURL(obj.stream);
            video.play();
    };
    
    // This function is necessary for chromium as chromium does not add candidate information by itself
    var addExtraCandidate = function (e) {
        // null is the last candidate, send the offer.
        if (e.candidate === null) {
            wsocket.send('OFFER_SDP', {
                'sdp': offer.sdp
            });
            return;
        }

        // Add the candidate right after m=audio or m=video.
        var sdp = offer.sdp.split('\r\n');
        for (var i = 0; i < sdp.length; i++) {
            if (sdp[i].indexOf('m=' + e.candidate.sdpMid) === 0) {
                // FIXME: i + 3 is after the c=IN and a=rtcp SDP attributes
                // May need to add more checks and not hardcode 3 based on an arbitrary length
                sdp.splice(i + 3, 0, e.candidate.candidate.trim());
                break;
            }
        }
        offer.sdp = sdp.join("\r\n");
    };
    
    var localOffer = function (new_offer) {
        pc.setLocalDescription(new_offer, function () {
            offer = new_offer;
        }, error);
    };
    
    var offerReceived = function(data) {
        if (manage) {
            var offer = new RTCSessionDescription({'type': 'answer', 'sdp': data['sdp']});
                pc.setRemoteDescription(offer, function() {
            }, error);
    };
    
    Video();
};



$(document).ready(function() {
    // Autofocus
    $message = $('#message');
    $message.focus();
    
    var wsocket = new WebSocketDispatcher(serviceLocation);
    chat = new Chat(wsocket);
    
    video = new Video(wsocket);
    
    $('#do-chat').submit(function(evt) {
        evt.preventDefault();
        chat.sendMessage(wsocket);
    });
});
