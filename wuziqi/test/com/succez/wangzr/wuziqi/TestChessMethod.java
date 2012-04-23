package com.succez.wangzr.wuziqi;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import com.succez.wangzr.wuziqi.algorithm.ChessMethod;
import com.succez.wangzr.wuziqi.tools.Constant;
import com.succez.wangzr.wuziqi.tools.Point;
import com.succez.wangzr.wuziqi.ui.ChessPanel;

/**
 * ChessMethod测试类
 * <p>Copyright: Copyright (c) 2012<p>
 * <p>succez<p>
 * @author feihu
 * @createdate 2012-4-9
 */
public class TestChessMethod extends TestCase{

	public void testPcPlay(){
		ChessMethod method=new ChessMethod(15);
		method.setable(7, 7, Constant.BLACKCHESS);
	}
}