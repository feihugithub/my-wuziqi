var XMLHttpReq = false;
var isStart = 0;
var winer = 0;// 记录是否有胜利者
// 创建XMLHttpRequest对象
function createXMLHttpRequest() {
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
function sendRequest(url) {
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse;
	XMLHttpReq.send(null);
}
// Ajax响应服务器响应的方法
function processResponse() {
	if (XMLHttpReq.readyState == 4) {
		if (XMLHttpReq.status == 200) {
			winer = XMLHttpReq.responseXML.getElementByTagName("winer")[0].firstChild.data;
			if (winer == -1) {
				isStart = 0;
			}
			else {
				x = XMLHttpReq.responseXML.getElementsByTagName("x")[0].firstChild.data;
				y = XMLHttpReq.responseXML.getElementsByTagName("y")[0].firstChild.data;
				X = x * 37 + 18.5;
				Y = y * 37 + 18.5;
				paintchess();
				if (winer == 1) {
					isStart = 0;
				}
			}

		}
		else {
			window.alert("there is something wrong");
		}
	}
}
function start() {
	var startValue = document.getElementById("start").value;
	if (startValue == "开始") {
		isStart = 1;
		alert("现在可以开始游戏了！祝你好运！");
		startValue = "重新开始";
		sendRequest('aiAction?isStart='+isStart);
	}
	else {
		window.location.reload();
	}

}
function userchess(e) {
	if (winer == 0 && isStart == 1) {
		positionTransform(e);
		paintchess();
		sendRequest('aiAction?x='+x+'&y='+y);
	}
	else {
		alert("您还没有开始游戏！");
	}
}