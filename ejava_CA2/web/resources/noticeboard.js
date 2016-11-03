var socket = null;
var i = 0;
function configure()
{
    var original = document.getElementById('contents');
    original.id= "contents" + i;
    
    document.getElementById("showNoticeForm").style.display = "none";
    document.getElementById("showNoticeBox:postTextAgain").style.display = "block";
    document.getElementById("showNotices").style.display = "block";
    var sel = document.getElementById("showNoticeForm:category").value;
    
    var url = '/ejavaca2/api/noticeboard';
    
    jQuery.ajax( {
    url: url,
    type: 'GET',
    data: { category: sel },
    success: function( response ) {
        console.log("success");
        writeDBData(response);
    }
    } );
    
    document.getElementById("noticeCategory").innerHTML = "Category: "+sel;
    socket = new WebSocket("ws://localhost:65138/ejavaca2/notices");
   
    socket.onmessage = function(evt) {
		// {message: "the message" , timestamp: "time" }
		var msg = JSON.parse(evt.data);
                var selectedCat = document.getElementById("showNoticeForm:category").value;
                if(selectedCat === "All"){
                    writeData(msg);
                }
                else if(selectedCat === msg.category){
                    writeData(msg);
                }
	};
        
    socket.onopen = function() {
        write("Connected to notice server");
	};
        
    socket.onclose = function() {
	write("Disconnected from notice server");
	};
     
    var write = function(text){
        document.getElementById("connection").innerHTML = text;
    };
    
    var writeData = function(entry) {
        var currentDiv = document.getElementById("contents"+i);
        if(currentDiv.childNodes[1].innerHTML !== "NO POSTS YET"){
            duplicate();
        }
        var parentDiv = document.getElementById("contents"+i);
        parentDiv.childNodes[1].innerHTML = entry.title;
        parentDiv.childNodes[3].childNodes[1].innerHTML = entry.postedOn;
        parentDiv.childNodes[3].childNodes[3].innerHTML = entry.postedBy;
        parentDiv.childNodes[3].childNodes[5].innerHTML = entry.category;
        parentDiv.childNodes[3].childNodes[7].innerHTML = entry.content;
	};
    //return false;
    
    var writeDBData = function(response){
    var child = 0;
    for(var i=0;i<response.length-1;i++){
        duplicate();
    }
    response.forEach(function(entry) {
        var parentDiv = document.getElementById("contents"+child);
        parentDiv.childNodes[1].innerHTML = entry.title;
        parentDiv.childNodes[3].childNodes[1].innerHTML = entry.postedOn;
        parentDiv.childNodes[3].childNodes[3].innerHTML = entry.postedBy;
        parentDiv.childNodes[3].childNodes[5].innerHTML = entry.category;
        parentDiv.childNodes[3].childNodes[7].innerHTML = entry.content;
        child++;
});
};
    var duplicate = function() {
        var clone = original.cloneNode(true); // "deep" clone
        clone.id = "contents" + ++i;
        original.parentNode.prepend(clone);
    };


}

function again()
{
    document.getElementById("showNoticeForm").style.display = "block";
    document.getElementById("showNoticeBox:postTextAgain").style.display = "none";
    document.getElementById("showNotices").style.display = "none";
    socket.close();
}