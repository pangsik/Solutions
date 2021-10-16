package swea;

import java.util.*;
import java.io.*;

public class d0_1767_프로세서연결하기2 {
	static class Core {
		int x, y;

		public Core(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌 (시계방향)
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static ArrayList<Core> arr;
	static int N, max, minLen;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			arr = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						arr.add(new Core(i, j));
					}
				}
			}
			max = 0;
			minLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(minLen).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	static void dfs(int cnt, int num, int sum) { // num : 코어 수, sum : 전선 길이 합
		// 남은 코어 수 + 현재 선택된 코어 수 < max 이면 가지치기
		if ((arr.size() - cnt + 1) + num < max) {
			return;
		}

		// 모든 코어 확인됐으면 선택된 코어 수가 최대임을 확인하고 전선 길이 합 최소 갱신
		if (cnt == arr.size()) {
			if (max < num) {
				max = num;
				minLen = sum;
			} else if (max == num) {
				minLen = minLen < sum ? minLen : sum;
			}
			return;
		}

		Core current = arr.get(cnt);

		// 코어가 가장자리에 있는 경우.. 아무 작업도 하지 않고 선택된 개수만 +1 해주고 dfs
		if (current.x == 0 || current.y == 0) {
			dfs(cnt + 1, num + 1, sum);
		}

		else {
			// 코어가 선택되는 경우 4방향.. 전선 연결 가능한지 확인 후 가능하면 set해서 dfs.. 갔다가 나올땐 free시켜주기
			for (int d = 0; d < 4; d++) {
				if (check(current.x, current.y, d)) {
					int tmp = set(current.x, current.y, d);
					dfs(cnt + 1, num + 1, sum + tmp);
					free(current.x, current.y, d);
				}
			}

			// 코어가 선택되지 않는 경우.. 아무 작업도 하지 않고 넘어감
			dfs(cnt + 1, num, sum);
		}
	}

	// 전선 연결 가능 true / 불가능 false 리턴
	static boolean check(int x, int y, int d) {
		while (true) {
			int nx = x + di[d];
			int ny = y + dj[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (map[nx][ny] != 0) {
					return false;
				}
				x = nx;
				y = ny;
			}

			else {
				return true;
			}
		}
	}

	// 전선 연결.. (x, y):코어 좌표.. (검사 하고 연결할 것)
	static int set(int x, int y, int d) {
		int cnt = 0;
		while (true) {
			int nx = x + di[d];
			int ny = y + dj[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				map[nx][ny] = 2;
				x = nx;
				y = ny;
				cnt++;
			}

			else {
				break;
			}
		}
		return cnt;
	}

	// 전선 해제..
	static void free(int x, int y, int d) {
		while (true) {
			int nx = x + di[d];
			int ny = y + dj[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				map[nx][ny] = 0;
				x = nx;
				y = ny;
			}

			else {
				break;
			}
		}
	}
}

// 가장자리에 파워 흐름
// 전선 교차 X
// 가장자리에 위치한 코어는 파워 연결된 것으로 간주
// 빈 칸 : 0
// 코어 : 1
// 전선 : 2
// dfs로 모든 코어 모든 방향으로 전선 뻗어보기