package RTS;

import java.io.*;
import java.util.*;

public class bj_23289_온풍기안녕_못품 {
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Heater extends Pos {
		int d;
		public Heater(int x, int y, int d) {
			super(x, y);
			this.d = d;
		}
	}
	static int R, C, K, W;
	static int[][] map;
	static ArrayList<Heater> heaters;
	static ArrayList<Pos> checkList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		heaters = new ArrayList<>();
		checkList = new ArrayList<>();
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				int input = Integer.parseInt(st.nextToken());
				if (input > 0 && input < 5) 
					heaters.add(new Heater(i, j, input));
				else if (input == 5) 
					checkList.add(new Pos(i, j));
			}
		}
		
		W = Integer.parseInt(br.readLine());
		for (int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(Solution());
		
		br.close();
	}
	
	private static int Solution() {
		int chocolate = 0;
		while (chocolate < 100) {
			// 온풍기 바람 방출
			heat();
			
			// 온도 조절
			tempControl();
			
			// 온도 감소
			tempDecrease();
			
			// 초콜릿++
			chocolate++;
			
			// 조사하는 모든 칸의 온도가 K 이상인지 검사
			if (isEnd())
				break;
		}
		
		return chocolate;
	}

	private static void heat() {
		for (Heater cur : heaters) {
			
		}
	}
	
	private static void tempControl() {
		
	}

	private static void tempDecrease() {
		for (int i = 0; i < R; i++) {
			map[i][0] = map[i][0] > 0 ? map[i][0] - 1 : 0;
			map[i][C-1] = map[i][C-1] > 0 ? map[i][C-1] - 1 : 0;
		}
		
		for (int j = 1; j < C - 1; j++) {
			map[0][j] = map[0][j] > 0 ? map[0][j] - 1 : 0;
			map[R-1][j] = map[R-1][j] > 0 ? map[R-1][j] - 1 : 0;
		}
	}

	private static boolean isEnd() {
		for (Pos cur : checkList) {
			if (map[cur.x][cur.y] < K)
				return false;
		}
		
		return true;
	}

	private static boolean isLineOut(int nx, int ny) {
		return nx < 0 || nx >= R || ny < 0 || ny >= C;
	}
}

// 벽을 어떻게 저장할 거고,, 온풍기 바람쏘는 규칙 어떻게 적용할 것인가
// 

// R*C
// 초기 온도는 모든 칸 0
// 1.모든 온풍기에서 바람이 한 번 나옴
// 2.온도가 조절됨
// 3.온도가 1 이상인 가장 바깥쪽 칸의 온도 1씩 감소
// 4.초콜릿을 하나 먹음 ?? 이런 ㅅㅂ 몇번 돌았는지 구하라는거네 어이가없네
// 5.조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K 이상이면 테스트 중단, 아니면 1부터 다시 시작

// 온풍기에서 바람이 나오면 온풍기가 바라보는 바로 앞 칸 온도 5도 상승
// 그 다음 이 바람은 다른 칸으로 이동하며 온도 상승시킴
// (x,y)에 온풍기 바람이 도착해 온도가 k(>1)만큼 상승했다면, (x-1, y+1),(x,y+1),(x+1,y+1)의 온도도 k-1만큼 상승
// 그런 칸이 존재하지 않으면 바람은 이동 안함
// 어어떤 칸에 같은 온풍기에서 나온 바람이 여러 번 도착해도 여러 번 상승하지 않음

// 벽 정보는 다 저장해놓고.. 그냥 다 봐 ??

// 일부 칸과 칸 사이에는 벽이 있어서 바람 못지나감

// 오른쪽으로 가는 경우
// (x,y) - (x-1,y),(x,y+1),(x+1,y)
// (x-1,y) - (x-1,y+1)
// (x+1,y) - (x+1,y+1)


// 왼쪽으로 가능 경우
// (x,y) - (x-1,y),(x,y-1),(x+1,y)
// (x-1,y) - (x-1,y-1)
// (x+1,y) - (x+1,y-1)


// 아래로 가는 경우
// (x,y) - (x,y-1),(x+1,y),(x,y+1)
// (x,y-1) - (x+1,y-1)
// (x,y+1) - (x+1,y+1)


// 위로 가는 경우
// (x,y) - (x,y-1),(x-1,y),(x,y+1)
// (x,y-1) - (x-1,y-1)
// (x,y+1) - (x-1,y+1)



// 온풍기가 2개 이상인 경우 각 온풍기에 의해 상승한 온도를 모두 합한 값이 해당 값의 온도
// 온풍기가 있는 칸도 다른 온풍기에 의해 온도 상승 가능

// 온도가 조절되는 과정
// 모든 인접한 칸에 대해 온도가 높은 칸 -> 낮은 칸으로  ⌊(두 칸의 온도의 차이)/4⌋만큼 온도가 조절됨
// 온도가 높은 칸은 이 값만큼 온도 감소, 낮은 칸은 온도 상승
// 벽이 있으면 온도 조절 X

// 0: 빈 칸
// 1: 방향이 오른쪽인 온풍기가 있음
// 2: 방향이 왼쪽인 온풍기가 있음
// 3: 방향이 위인 온풍기가 있음
// 4: 방향이 아래인 온풍기가 있음
// 5: 온도를 조사해야 하는 칸
















