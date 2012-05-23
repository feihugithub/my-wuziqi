//-1代表白棋，1代表黑棋
var panel = document.getElementById("chesspanel");
var x = 0;//用户选择的点对应在arr数组中的坐标
var y = 0;
var X = 0;//用户选择的点对应在页面上的绝对坐标
var Y = 0;
var owner = 1;//在绘制棋子的时候用到，根据其确定该绘什么颜色的棋子
var arr = new Array(15);//客户端和服务器端都会维护一个数组去记录下棋的状况，客户端只是用来查询下棋点是否合法。
for (var i = 0; i < 15; i++) {
	arr[i] = new Array(15);
	for (var j = 0; j < 15; j++) {
		arr[i][j] = 0;
	}
}
function paintchess() {
	if (x != -1 && arr[x][y] == 0) {//指定合法的下棋点
		var img = document.createElement("img");
		img.style.position = "absolute";
		img.style.top = Y + "px";
		img.style.left = X + "px";
		if (owner == -1) {
			img.setAttribute("src", "white.png");
		}
		else {
			img.setAttribute("src", "black.png");
		}
		owner = -owner;
		panel.appendChild(img);
	}
}
function positionTransform(e) {//因为用户点击的位置往往不是要下棋的准确位置，要通过计算转换成准确的位置
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