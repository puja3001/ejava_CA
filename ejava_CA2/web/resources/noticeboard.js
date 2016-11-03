var socket = null;
function configure()
{
    document.getElementById("showNoticeForm").style.display = "none";
    document.getElementById("postTextAgain").style.display = "block";
    var sel = document.getElementById("showNoticeForm:category").value;
    
    var url = 'http://domain.com/path/endpoint';

    $.post(url, {category: sel}, function(response){
        // callback success
    }, function(response) {
        // callback error
    });
    
    document.getElementById("noticeCategory").innerHTML = "Category: "+sel;
    socket = new WebSocket("ws://localhost:65138/ejavaca2/notices");
   
    socket.onmessage = function(evt) {
		// {message: "the message" , timestamp: "time" }
		var msg = JSON.parse(evt.data);
                var selectedCat = document.getElementById("category").value;
                if(selectedCat === msg.category){
                    var toWrite = "\nTitle: "+msg.title+"\nPosted By: "
                    +msg.postedBy+"\nPosted On: "+msg.postedOn+"\nCategory: "+msg.category
                    +"\nContent: "+msg.content;
                    writeToChatboard(toWrite);
                }
	};
        
    socket.onopen = function() {
    writeToChatboard("Connected to chat server");
	};
        
    socket.onclose = function() {
	writeToChatboard("Disconnected from chat server");
	};
        
    var writeToChatboard = function(text) {
		$("#content").val(text + "\n" + $("#content").val());
	};
    //return false;
}

function again()
{
    document.getElementById("showNoticeForm").style.display = "block";
    document.getElementById("postTextAgain").style.display = "none";
    socket.close();
}