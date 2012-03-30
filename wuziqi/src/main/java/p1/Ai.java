package p1;

public class Ai {

	public static final int SUCCESS_5 = 140;
	public static final int LIVE_4 = 130;
	public static final int DOUBLE_DEAD_4 = 120;
	public static final int DEAD_4_LIVE_3 = 110;
	public static final int DOUBLE_LIVE_3 = 100;
	public static final int DEAD_3_LIVE_3 = 80;
	public static final int DEAD_4 = 60;
	public static final int LIVE_3 = 50;
	public static final int DOUBLE_LIVE_2 = 40;
	public static final int DEAD_3 = 30;
	public static final int LIVE_2 = 20;
	public static final int DEAD_2 = 10;
	public static final int SINGLE = 0;

	public static final int EMPTY = 1;
	public static final int CROWD = 0;

	public static final int BLACK = 1;
	public static final int WHITE = -1;

	public static final int CROSS_BORDER = 0;
	public static final int DIFFERENT = -1;
	public static final int SAME = 1;

	public static class Point {
		int p_x;
		int p_y;

		Point(int x, int y) {
			p_x = x;
			p_y = y;
		}
	}

	public int[][] table = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	// 比较两个位置的棋子是否相同
	public int compare(int current_x, int current_y, int next_x, int next_y) {
		if (next_x < 0 || next_x > 14)
			return CROSS_BORDER;
		else if (next_y < 0 || next_y > 14)
			return CROSS_BORDER;
		else if (table[current_x][current_y] == table[next_x][next_y])
			return SAME;
		return DIFFERENT;
	}

	// 分别判断横竖撇捺四个方向上的连子情况
	private int count_h(int test_x, int test_y) {
		int num_h = 0;
		int left_empty = EMPTY;
		int right_empty = EMPTY;
		int comparison = DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x - i, test_y);
			switch (comparison) {
			case CROSS_BORDER:
				left_empty = CROWD;
				break;
			case SAME:
				num_h++;
				break;
			default:
				if (table[test_x - i][test_y] != 0)
					left_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x + i, test_y);
			switch (comparison) {
			case CROSS_BORDER:
				right_empty = CROWD;
				break;
			case SAME:
				num_h++;
				break;
			default:
				if (table[test_x + i][test_y] != 0)
					right_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}
		return analysis(num_h, left_empty, right_empty);
	}

	private int count_s(int test_x, int test_y) {
		int num_s = 0;
		int left_empty = EMPTY;
		int right_empty = EMPTY;
		int comparison = DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x, test_y - i);
			switch (comparison) {
			case CROSS_BORDER:
				left_empty = CROWD;
				break;
			case SAME:
				num_s++;
				break;
			default:
				if (table[test_x][test_y - i] != 0)
					left_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x, test_y + i);
			switch (comparison) {
			case CROSS_BORDER:
				right_empty = CROWD;
				break;
			case SAME:
				num_s++;
				break;
			default:
				if (table[test_x][test_y + i] != 0)
					right_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}

		return analysis(num_s, left_empty, right_empty);

	}

	private int count_p(int test_x, int test_y) {
		int num_p = 0;
		int left_empty = EMPTY;
		int right_empty = EMPTY;
		int comparison = DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x - i, test_y + i);
			switch (comparison) {
			case CROSS_BORDER:
				left_empty = CROWD;
				break;
			case SAME:
				num_p++;
				break;
			default:
				if (table[test_x - i][test_y + i] != 0)
					left_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x + i, test_y - i);
			switch (comparison) {
			case CROSS_BORDER:
				right_empty = CROWD;
				break;
			case SAME:
				num_p++;
				break;
			default:
				if (table[test_x + i][test_y - i] != 0)
					right_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}

		return analysis(num_p, left_empty, right_empty);

	}

	private int count_n(int test_x, int test_y) {
		int num_n = 0;
		int left_empty = EMPTY;
		int right_empty = EMPTY;
		int comparison = DIFFERENT;

		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x - i, test_y - i);
			switch (comparison) {
			case CROSS_BORDER:
				left_empty = CROWD;
				break;
			case SAME:
				num_n++;
				break;
			default:
				if (table[test_x - i][test_y - i] != 0)
					left_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}
		for (int i = 1; i != 5; i++) {
			comparison = compare(test_x, test_y, test_x + i, test_y + i);
			switch (comparison) {
			case CROSS_BORDER:
				right_empty = CROWD;
				break;
			case SAME:
				num_n++;
				break;
			default:
				if (table[test_x + i][test_y + i] != 0)
					right_empty = CROWD;
			}
			if (comparison != SAME)
				break;
		}

		return analysis(num_n, left_empty, right_empty);

	}

	// 分析棋形
	private int analysis(int num, int left, int right) {
		if (num >= 4)
			return SUCCESS_5;
		else
			switch (num) {
			case 3:
				if (left == EMPTY && right == EMPTY)
					return LIVE_4;
				else if (left == EMPTY && right == CROWD)
					return DEAD_4;
				else if (left == CROWD && right == EMPTY)
					return DEAD_4;
				return SINGLE;
			case 2:
				if (left == EMPTY && right == EMPTY)
					return LIVE_3;
				else if (left == EMPTY && right == CROWD)
					return DEAD_3;
				else if (left == CROWD && right == EMPTY)
					return DEAD_3;
				return SINGLE;
			case 1:
				if (left == EMPTY && right == EMPTY)
					return LIVE_2;
				else if (left == EMPTY && right == CROWD)
					return DEAD_2;
				else if (left == CROWD && right == EMPTY)
					return DEAD_2;
				return SINGLE;
			default:
				return SINGLE;
			}
	}

	// 估值函数
	private int rate(int test_x, int test_y) {
		int[] temp = { -1, -1 };
		int two = 0;// 记录形成多种局面的情况
		int situation = count_h(test_x, test_y);
		switch (situation) {
		case SUCCESS_5:
			return SUCCESS_5;
		case LIVE_4:
			return LIVE_4;
		default:
			if (situation > temp[1])
				temp[1] = situation;
			if (temp[0] < temp[1]) {
				situation = temp[0];
				temp[0] = temp[1];
				temp[1] = situation;
			}
		}
		situation = count_s(test_x, test_y);
		switch (situation) {
		case SUCCESS_5:
			return SUCCESS_5;
		case LIVE_4:
			return LIVE_4;
		default:
			if (situation > temp[1])
				temp[1] = situation;
			if (temp[0] < temp[1]) {
				situation = temp[0];
				temp[0] = temp[1];
				temp[1] = situation;
			}
		}
		situation = count_p(test_x, test_y);
		switch (situation) {
		case SUCCESS_5:
			return SUCCESS_5;
		case LIVE_4:
			return LIVE_4;
		default:
			if (situation > temp[1])
				temp[1] = situation;
			if (temp[0] < temp[1]) {
				situation = temp[0];
				temp[0] = temp[1];
				temp[1] = situation;
			}
		}
		situation = count_n(test_x, test_y);
		switch (situation) {
		case SUCCESS_5:
			return SUCCESS_5;
		case LIVE_4:
			return LIVE_4;
		default:
			if (situation > temp[1])
				temp[1] = situation;
			if (temp[0] < temp[1]) {
				situation = temp[0];
				temp[0] = temp[1];
				temp[1] = situation;
			}
		}
		two = temp[0] + temp[1];
		if (two > 35) {
			switch (two) {
			case DOUBLE_DEAD_4:
				return DOUBLE_DEAD_4 + 10;
			case DEAD_4_LIVE_3:
				return DEAD_4_LIVE_3 + 20;
			case DOUBLE_LIVE_3:
				return DOUBLE_LIVE_3;
			case DEAD_3_LIVE_3:
				return DEAD_3_LIVE_3;
			case DOUBLE_LIVE_2:
				return DOUBLE_LIVE_2;
			default:
				return temp[0];
			}
		}
		return temp[0];
	}

	// ai查找下棋点
	public Point find_1() {
		int max = -1;// 记录最大的估分
		int temp = 0;
		int max_x = 0, max_y = 0;
		for (int i = 0; i != 14; i++) {
			for (int j = 0; j != 14; j++) {
				if (table[i][j] == 0) {
					table[i][j] = WHITE;
					temp = rate(i, j) + 1;
					if (temp > max) {
						max = temp;
						max_x = i;
						max_y = j;
					}
					table[i][j] = BLACK;
					temp = rate(i, j);
					if (temp > max) {
						max = temp;
						max_x = i;
						max_y = j;
					} 
					table[i][j] = 0;
				}
			}
		}
		Point point = new Point(max_x, max_y);
		table[max_x][max_y]=BLACK;
		System.out.println(max);
		return point;
	}
}