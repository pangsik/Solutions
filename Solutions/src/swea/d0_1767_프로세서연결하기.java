package swea;

import java.io.*;
import java.util.*;

public class d0_1767_프로세서연결하기 {
	static int[] di = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dj = { 0, 0, -1, 1 };
	static int N, max, totalCnt, min, map[][];
	static ArrayList<int[]> list;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<int[]>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i == 0 || j == 0 || i == N - 1 || j == N - 1) // 가장자리는 무시
						continue;
					if (map[i][j] == 1) {
						list.add(new int[] { i, j });
						totalCnt++;
					}
				}
			}
			go(0, 0);
			System.out.println("#" + tc + " " + min);
		}
		br.close();
	}

	static void go(int index, int cCnt) { // index : 부분집합에 고려할 코어 인덱스, cCnt : 연결된 코어 개수
		if (index == totalCnt) {
			int res = getLength(); // 놓아진 전선 수 구하기

			if (max < cCnt) {
				max = cCnt;
				min = res;
			} else if (max == cCnt) {
				if (res < min)
					min = res;
			}
			return;
		}

		if (cCnt + (totalCnt - index) < max) // 현재까지 연결된 코어 수 + 남은 코어 수가 max보다 작으면 그만
			return;

		// 코어 선택해서 전선 놓기 (4방향)
		int[] cur = list.get(index);
		int r = cur[0]; // 행
		int c = cur[1]; // 열
		for (int d = 0; d < 4; d++) {
			if (isAvailable(r, c, d)) {
				// 전선 놓기
				setStatus(r, c, d, 2);

				// 다음 코어로 넘어가기
				go(index + 1, cCnt + 1);

				// 놓은 전선 되돌리기
				setStatus(r, c, d, 0);
			}
		}

		// 코어 선택안하기
		go(index + 1, cCnt);
	}

	static void setStatus(int r, int c, int d, int s) {
		int nr = r;
		int nc = c;

		while (true) {
			nr += di[d];
			nc += dj[d];

			// 경계 벗어남.. 전원이 연결됨
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				break;
			map[nr][nc] = s;
		}
	}

	static boolean isAvailable(int r, int c, int d) {
		int nr = r;
		int nc = c;

		while (true) {
			nr += di[d];
			nc += dj[d];

			// 경계 벗어남.. 전원이 연결됨
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				break;

			if (map[nr][nc] != 0)
				return false;
		}

		return true;
	}

	static int getLength() {
		int lCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2)
					lCnt++;
			}
		}
		return lCnt;
	}
}

// 코어 or 전선
// map의 가장자리에 Power 흐름.. 전선을 가장자리까지 연결시켜줘야겠죠?
// 가장자리에 있는 코어는 이미 전원 연결된걸로 간주
// 코어 상하좌우 방향으로 전선 연결 가능 (직선으로만 가능)
// 전선 겹치기 불가능
// 코어-코어는 전원 연결 안됨
// 맵, 코어자리 정보 준다
// 코어를 최대한 많이 사용하면서 전선길이 합이 최소가 되는 값을 구해라 (사용되지 않는 코어 존재할 수 있음)

// dfs.. 1인곳 찾아서 상하좌우로 연결 다 해보기..
// 가장자리는 굳이 탐색 안해도 될 듯!

// 빈칸 0, 코어 1, 전선 2