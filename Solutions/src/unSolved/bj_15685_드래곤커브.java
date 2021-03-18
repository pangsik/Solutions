package unSolved;

import java.util.*;
import java.io.*;

public class bj_15685_드래곤커브 {
	static int[] di = { 0, -1, 0, 1 }; // 시계방향.. 우 상 좌 하
	static int[] dj = { 1, 0, -1, 0 };
	static int[][] map = new int[100][100];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); // 시작
			int y = Integer.parseInt(st.nextToken()); // 지점
			int d = Integer.parseInt(st.nextToken()); // 커브 방향 (0 우 1 상 2 좌 3 하)
			int g = Integer.parseInt(st.nextToken()); // 세대 수
			dc(x, y, d, g, 0);
		}
		
		System.out.println(getCnt());

		br.close();
	}

	static void dc(int x, int y, int d, int g, int lv) {
		if (lv > g) {

			return;
		}
		
		

		dc(x, y, d, g, lv + 1);
	}

	static int getCnt() {
		int cnt = 0;

		for (int i = 0; i < 99; i++) {
			for (int j = 0; j < 99; j++) {
				if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1)
					cnt++;
			}
		}

		return cnt;
	}
}

// 우 / 상 / 좌 상 / 좌 하 좌 상
// 반
// 반 시
// 반 반 시 시
// 0
// 0 1
// 0 1 2 1
// 0 1 2 1 2 3 2 1
// 0 1 2 1 2 3 2 1 2 3 0 3 2 3 2 1