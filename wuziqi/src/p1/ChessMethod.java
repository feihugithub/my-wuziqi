package p1;

//棋局类    负责人机对战和双人对战的控制
public class ChessMethod {


	public static final int p_p = 1;
	public static final int p_pc = 0;
	public Ai ai = new Ai();

	public int winer = 0;
	public int owner = Ai.BLACK;
	public int mode = p_p;

	// 双人对战
	public void play_p_p(int x, int y, int space, int radius) {
		Ai.Point p = new Ai.Point(x, y);
		p = transform(p, space, radius);
		if (p.p_x != -1) {
			if (ai.table[p.p_x][p.p_y] == 0) {
				ai.table[p.p_x][p.p_y] = owner;
				owner = -owner;
			}
			if (judge(p.p_x, p.p_y))
				winer = -owner;
		}

	}

	// public static int[][] getable(){
	// return table;
	// }
	// 人机对战
	public boolean play_p_pc(int x, int y, int space, int radius) {
		Ai.Point p = new Ai.Point(x, y);
		p = transform(p, space, radius);
		if (p.p_x != -1) {
			if (ai.table[p.p_x][p.p_y] == 0){
				ai.table[p.p_x][p.p_y] = Ai.WHITE;
				if (judge(p.p_x, p.p_y))
					winer = Ai.WHITE;
				return true;
			}
				
		}return false;
	}

	// 重置
	public void reset() {
		for (int i = 0; i != 15; i++)
			for (int j = 0; j != 15; j++)
				if (ai.table[i][j] != 0)
					ai.table[i][j] = 0;
	}

	// 判断当前棋子能否使棋手胜利
	public boolean judge(int x, int y) {
		int h = 0, s = 0, p = 0, n = 0;// h代表横向上的棋子个数，s代表竖向上的棋子个数，p代表撇向上棋子个数，n代表捺向上棋子的个数
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x + i, y))
				h++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x - i, y))
				h++;
			else
				break;
		}
		if (h >= 4)
			return true;
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x, y + i))
				s++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x, y - i))
				s++;
			else
				break;
		}
		if (s >= 4)
			return true;
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x + i, y - i))
				p++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x - i, y + i))
				p++;
			else
				break;
		}
		if (p >= 4)
			return true;
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x - i, y - i))
				n++;
			else
				break;
		}
		for (int i = 1; i != 5; i++) {
			if (compare(x, y, x + i, y + i))
				n++;
			else
				break;
		}
		if (n >= 4)
			return true;
		return false;
	}

	// 比较两个棋子的颜色是否一样
	private boolean compare(int current_x, int current_y, int next_x, int next_y) {
		if (next_x < 0 || next_x > 14)
			return false;
		else if (next_y < 0 || next_y > 14)
			return false;
		else if (ai.table[current_x][current_y] == ai.table[next_x][next_y])
			return true;
		return false;
	}

	// 转换坐标
	public Ai.Point transform(Ai.Point p, int space, int radius) {
		if (p.p_x % space < radius && p.p_y % space < radius) {
			p.p_x = p.p_x / space - 1;
			p.p_y = p.p_y / space - 1;
			return p;
		}

		else if (p.p_x % space > (space - radius)
				&& p.p_y % space > (space - radius)) {
			p.p_x = p.p_x / space;
			p.p_y = p.p_y / space;
			return p;

		} else if (p.p_x % space > (space - radius) && p.p_y % space < radius) {
			p.p_x = p.p_x / space;
			p.p_y = p.p_y / space - 1;
			return p;

		} else if (p.p_x % space < radius && p.p_y % space > (space - radius)) {
			p.p_x = p.p_x / space - 1;
			p.p_y = p.p_y / space;
			return p;

		} else {
			p.p_x = -1;
			return p;
		}
	}
}
