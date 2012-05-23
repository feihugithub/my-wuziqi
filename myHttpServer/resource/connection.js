var XMLHttpReq = false;
var isStart = 0;
var winer = 0;// 记录是否有胜利者
var aiLevel=-1;//初级是1，高级是-1
// 创建XMLHttpRequest对象
function createXMLHttpRequest() {//（逻辑没有错误）
	if (window.XMLHttpRequest) {
		XMLHttpReq = new XMLHttpRequest();
	}
	else if (window.ActiveXObject) {
		try {
			XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e) {
			try {
				XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e) {
			}
		}
	}
}
// Ajax发送请求的方法
function sendRequest(url) {//（逻辑没有错误）
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse;
	XMLHttpReq.send(null);
}
// Ajax响应服务器响应的方法
function processResponse() {//（逻辑没有错误）
	if (XMLHttpReq.readyState == 4) {
		if (XMLHttpReq.status == 200) {
			winer = XMLHttpReq.responseXML.getElementsByTagName("winer")[0].firstChild.data;
			if (winer == -1) {
				isStart = 0;
				alert("congratulations！You win！");
			}
			else {
				x = XMLHttpReq.responseXML.getElementsByTagName("x")[0].firstChild.data;
				y = XMLHttpReq.responseXML.getElementsByTagName("y")[0].firstChild.data;
				X = x * 37 + 18.5;
				Y = y * 37 + 18.5;
				paintchess();
				if (winer == 1) {
					isStart = 0;
					alert("sorry！You lose！");
				}
			}

		}
		else {
			window.alert("there is something wrong");
		}
	}
}
function startGame() {//（逻辑没有错误）
	var startnode = document.getElementById("start");
	if (startnode.value == "开始") {
		isStart = 1;
		alert("现在可以开始游戏了！祝你好运！");
		startnode.value = "重新开始";
		sendRequest('aiAction?isStart='+isStart+'&aiLevel='+aiLevel);
	}
	else {
		location.reload();
	}

}
function userchess(e) {//（逻辑没有问题）
	if (winer == 0 && isStart == 1) {
		positionTransform(e);
		paintchess();
		sendRequest('aiAction?x='+x+'&y='+y);
	}
	else {
		alert("您还没有开始游戏！");
	}
}