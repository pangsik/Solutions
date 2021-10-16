package swea;

import java.io.*;
import java.util.*;

public class d0_5644_무선충전 {
	static int M, bcCnt;
	static int[] pathA, pathB, playerA, playerB;
	static int[][] bc;

	static int dx[] = { 0, 0, 1, 0, -1 };
	static int dy[] = { 0, -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		playerA = new int[2];
		playerB = new int[2];

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			bcCnt = Integer.parseInt(st.nextToken());

			playerA[0] = playerA[1] = 1;
			playerB[0] = playerB[1] = 10;

			pathA = new int[M + 1];
			pathB = new int[M + 1];
			bc = new int[bcCnt][4];

			StringTokenizer stA = new StringTokenizer(br.readLine(), " ");
			StringTokenizer stB = new StringTokenizer(br.readLine(), " ");

			// i = 0값은 0으로 남아있다.. (0:그대로) => 가장 처음자리도 충전이 가능한지 확인하기 위함
			for (int i = 1; i <= M; i++) {
				pathA[i] = Integer.parseInt(stA.nextToken());
				pathB[i] = Integer.parseInt(stB.nextToken());
			}

			for (int i = 0; i < bcCnt; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				bc[i][0] = Integer.parseInt(st.nextToken()); // x
				bc[i][1] = Integer.parseInt(st.nextToken()); // y
				bc[i][2] = Integer.parseInt(st.nextToken()); // 거리
				bc[i][3] = Integer.parseInt(st.nextToken()); // 충전량
			}

			sb.append('#').append(tc).append(' ').append(move()).append('\n');
		}
		System.out.println(sb);

		br.close();
	}

	private static int move() {
		int totalSum = 0;

		// 매 시간마다 각 위치에서 두 플레이어의 최대 충전량을 계산하여 합산
		for (int t = 0; t <= M; t++) { // 초기 위치도 충전하기 위해.. 아까 위에서 pathA,B 크기 M+1로 줬죠?
			// 두 플레이어 본인 이동정보에 따라 이동
			playerA[0] += dx[pathA[t]];
			playerA[1] += dy[pathA[t]];
			playerB[0] += dx[pathB[t]];
			playerB[1] += dy[pathB[t]];

			// 현재 위치에서 최대 충전량 계산
			totalSum += getMaxCharge();
		}

		return totalSum;
	}

	private static int getMaxCharge() {
		int max = 0;
		// 중복순열
		for (int a = 0; a < bcCnt; a++) { // 플레이어 A가 선택한 BC
			for (int b = 0; b < bcCnt; b++) { // 플레이어 B가 선택한 BC
				int sum = 0;
				int amountA = check(a, playerA[0], playerA[1]);
				int amountB = check(b, playerB[0], playerB[1]);
				
				// 두 충전소가 다른 경우
				if (a != b) sum = amountA + amountB;
				// 같은 경우... A만 되는 경우는 A 선택, B만 되는 경우는 B 선택, 둘 다 되는 경우는 반 나눠서 선택 => 하나만 선택하는거랑 값이 같음
				else sum = Math.max(amountA, amountB);
				
				if (max < sum)
					max = sum;
			}
		}

		return max;
	}

	// 가능하면 충전량, 불가능하면 0 리턴
	private static int check(int a, int x, int y) {
		return Math.abs(x - bc[a][0]) + Math.abs(y - bc[a][1]) <= bc[a][2] ? bc[a][3] : 0;
	}
}

// BC(Battery Charger)의 충전 범위가 C일 때, BC와의 거리가 C 이하면 BC에 접속 가능
// 두 지점 A(x1, y1), B(x2, y2) 사이의 거리 : |x1 - x2| + |y1 - y2|
// 겹치는 범위에 들어간다면 둘 중 하나 선택해서 접속 가능

// 맵 그리기 (BC정보..) 10*10
// A는 0,0에서 / B는 9,9에서 출발
// 사용자는 시작 위치(0초)부터 충전 가능
// 사용자 A, B는 같은 위치로 이동할 수도 있음.. 단, 맵 밖으로 나가진 않음