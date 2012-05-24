// -1代表白棋，1代表黑棋
var x = 0;// 用户选择的点对应在arr数组中的坐标
var y = 0;
var X = 0;// 用户选择的点对应在页面上的绝对坐标
var Y = 0;
var isStart = 0;
var winer = 0;
var owner = 1;// 在绘制棋子的时候用到，根据其确定该绘什么颜色的棋子
var aiLevel = 1;
var exclusion = 0;// 互斥信号量，该值为1时用户才能下棋
var XMLHttpReq = false;
var arr = new Array(15);// 客户端和服务器端都会维护一个数组去记录下棋的状况，客户端只是用来查询下棋点是否合法。
for (var i = 0; i < 15; i++) {
	arr[i] = new Array(15);
	for (var j = 0; j < 15; j++) {
		arr[i][j] = 0;
	}
}
function paintchess() {
	if (x != -1 && arr[x][y] == 0) {// 指定合法的下棋点
		var img = document.createElement("img");
		var panel = document.getElementById("chesspanel");
		img.style.position = "absolute";
		img.style.top = Y + "px";
		img.style.left = X + "px";
		if (owner == -1) {
			img.setAttribute("src", "/white.png");
		}
		else {
			img.setAttribute("src", "/black.png");
		}
		arr[x][y] = owner;
		owner = -owner;
		showThink(owner);
		panel.appendChild(img);
	}
}
function positionTransform(e) {// 因为用户点击的位置往往不是要下棋的准确位置，要通过计算转换成准确的位置
	x = parseInt(e.clientX / 37);
	y = parseInt(e.clientY / 37);
	var tempx = e.clientX % 37;
	var tempy = e.clientY % 37;
	if (tempx < 18.5 && tempy < 18.5) {
		x = x - 1;
		y = y - 1;
	}
	else if (tempx > 18.5 && tempy > 18.5) {
	}
	else if (tempx < 18.5 && tempy > 18.5) {
		x = x - 1;
	}
	else if (tempx > 18.5 && tempy < 18.5) {
		y = y - 1;
	}
	else {
		x = -1;
		y = -1;
	}
	X = x * 37 + 18.5;
	Y = y * 37 + 18.5;
}
function userchess(e) {
	if (winer == 0 && isStart == 1) {
		if (exclusion == 1) {
			exclusion = 0;
			positionTransform(e);
			paintchess();
			var news = "您在(" + x + "," + y + ")下了棋子，这是一个不错的选择哦！";
			showNews(news);
			sendRequest('aiAction?x=' + x + '&y=' + y);
		}
		else {
			alert("don't hurry,please wait the ai");
		}
	}
	else {
		alert("您还没有开始游戏！");
	}
}
function startGame() {
	var startnode = document.getElementById("start");
	if (startnode.value == "开始") {
		debugger;
		isStart = 1;
		if (document.choose.aiLevel[1].checked)
			aiLevel = -1;
		var news;
		if (aiLevel == 1)
			news = "您选择了初级电脑，它智力有限，您要让着它啊！"
		else
			news = "您选择了高级电脑，它技术还行，你要小心了！"
		showNews(news);
		alert("现在可以开始游戏了！祝您好运！");
		startnode.value = "重新开始";
		sendRequest('aiAction?isStart=' + isStart + '&aiLevel=' + aiLevel);
	}
	else {
		location.reload();
	}
}
function showNews(news) {
	document.all.show.value += news + '\n';
	document.all.show.scrollTop += 20;
}
function showThink(owner) {
	var think = "let me see!"
	if (owner == -1) {
		document.all.think1.value = think;
		document.all.think2.value = "";
	}
	else {
		document.all.think1.value = "";
		document.all.think2.value = think;
	}
}
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
	debugger;
	createXMLHttpRequest();
	XMLHttpReq.open("GET", url, true);
	XMLHttpReq.onreadystatechange = processResponse;
	XMLHttpReq.send(null);
}
// Ajax响应服务器响应的方法
function processResponse() {
	if (XMLHttpReq.readyState == 4) {
		if (XMLHttpReq.status == 200) {
			winer = XMLHttpReq.responseXML.getElementsByTagName("winer")[0].firstChild.data;
			if (winer == -1) {
				isStart = 0;
				alert("congratulations！You win！");
				var news = "您在(" + x + "," + y + ")下了棋子，您真是个天才，您胜出了！";
				showNews(news);
			}
			else {
				x = XMLHttpReq.responseXML.getElementsByTagName("x")[0].firstChild.data;
				y = XMLHttpReq.responseXML.getElementsByTagName("y")[0].firstChild.data;
				X = x * 37 + 18.5;
				Y = y * 37 + 18.5;
				paintchess();
				var news = "ai在(" + x + "," + y + ")下了棋子，它已经渐渐不支了...！";
				showNews(news);
				if (winer == 1) {
					isStart = 0;
					alert("sorry！You lose！");
					news = "wow，它获胜了，您大意了，再来一局吧！";
					showNews(news);
				}
			}
			exclusion = 1;
		}
		else {
			window.alert("there is something wrong");
		}
	}
}