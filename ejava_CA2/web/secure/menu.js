var context = document.getElementById("ctx").textContent;
var url  = "ws://"+window.location.hostname+":"+window.location.port+context+"/notices";
var socket = new WebSocket(url);
    socket.onopen = function() {
        console.log("connection open");
	};
                
    socket.onclose = function() {
	console.log("connection closed");
	};

function sendMessage(){
    console.log("called send");
    var title = document.getElementById("postForm:title").value !== "" ? document.getElementById("postForm:title").value : "NO TITLE";
    var category = document.getElementById("postForm:category").value;
    var content = document.getElementById("postForm:content").value !== "" ? document.getElementById("postForm:content").value : "NO CONTENT";
    socket.send(title+":"+category+":"+content);
}
