package com.succez.wangzr.wzqfornet.control;

import java.util.HashMap;

import com.succez.wangzr.wzqfornet.control.Control;
import com.succez.wangzr.wzqfornet.tool.Constant;
import com.succez.wangzr.wzqfornet.tool.Point;

public class AiAction {
	/**url中的参数存放的容器*/
	private HashMap<String, String> map = new HashMap<String, String>();

	/**ai的控制对象的存在周期是整个程序运行的周期*/
	private static Control control = new Control(15);

	public AiAction(String url) {
		int indexEqual = url.indexOf('='), indexAnd = -1;
		int lastIndex = url.lastIndexOf('=');
		String key, value;
		while (lastIndex != indexEqual) {
			key = url.substring(indexAnd + 1, indexEqual);
			indexAnd = url.indexOf('&', indexEqual + 1);
			value = url.substring(indexEqual + 1, indexAnd);
			map.put(key, value);
			indexEqual = url.indexOf('=', indexAnd + 1);
		}
		map.put(url.substring(indexAnd + 1, indexEqual), url.substring(indexEqual + 1));
	}

	/**
	 * 读取从浏览器传过来的浏览器的uri中的参数
	 * @param key
	 * @return
	 */
	public String getParameter(String key) {
		return map.get(key);
	}

	public String action() {
		if (getParameter("isStart") != null) {
			control.setAiLevel(Integer.parseInt(getParameter("aiLevel")));
			control.setable(7, 7, Constant.BLACKCHESS);
			return "<news><x>7</x><y>7</y><winer>0</winer></news>";
		}
		else {
			int x = Integer.parseInt(getParameter("x"));
			int y = Integer.parseInt(getParameter("y"));
			control.setable(x, y, Constant.WHITECHESS);
			if (control.isWin(x, y)) {
				return "<news><winer>-1</winer></news>";
			}
			else {
				Point point = control.pcPlay();
				
				return "<news><x>"+Integer.toString(point.positionX)+"</x><y>"+Integer.toString(point.positionY)+"</y><winer>"+Integer.toString(control.getWiner())+"</winer></news>";
			}

		}
	}
	public static Control getControl() {
		return control;
	}

}
