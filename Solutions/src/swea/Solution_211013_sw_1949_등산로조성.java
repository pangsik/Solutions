package swea;

import java.io.*;
import java.util.*;

// 풀이 시간 : 약 40분
// dfs 들어갈 때 방문 처리, 나올 때 해제하는거 주의!

public class Solution_211013_sw_1949_등산로조성 {
	static int N, K, maxLen;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static ArrayDeque<int[]> tops;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			tops = new ArrayDeque<int[]>();
			int maxTop = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > maxTop) {
						tops.clear();
						tops.add(new int[] { i, j });
						maxTop = map[i][j];
					} else if (map[i][j] == maxTop) {
						tops.add(new int[] { i, j });
					}
				}
			}
			
			Solution();
			
			System.out.println("#" + tc + " " + maxLen);
		}
		
		br.close();
	}
	
	private static void Solution() {
		maxLen = 0;
		
		// 모든 봉우리에 대해 수행
		while (!tops.isEmpty()) {
			int[] pos = tops.poll();

			visited = new boolean[N][N];
			visited[pos[0]][pos[1]] = true;
			// 시작 위치, 현재까지 등산로 길이, 지형 깎기 가능 여부
			dfs(pos[0], pos[1], 1, true);
		}
	}

	private static void dfs(int x, int y, int len, boolean flag) {
		if (len > maxLen) {
			maxLen = len;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (!check(nx, ny))
				continue;
			
			// 갈 수 있으면 안파고 그냥 진행
			if (map[nx][ny] < map[x][y]) {
				visited[nx][ny] = true;
				dfs(nx, ny, len + 1, flag);
				visited[nx][ny] = false;
				continue;
			}
			
			// 가려는 위치가 현재 위치보다 높거나 같을 경우 깎을 수 있는지 확인
			if (flag && map[nx][ny] - K < map[x][y]) {
				// 깎아서 진행할 수 있는 경우 최대한 높게 깎기 (현재 위치A - 1)
				// 단, 다시 돌아오면 원래대로 돌려놓기
				int original = map[nx][ny];

				visited[nx][ny] = true;
				map[nx][ny] = map[x][y] - 1;
				dfs(nx, ny, len + 1, !flag);
				map[nx][ny] = original;
				visited[nx][ny] = false;
			}
		}
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] == true) {
			return false;
		}
		
		return true;
	}
}

// N*N
// 등산로 조성 규칙
// 1.가장 높은 봉우리에서 시작
// 2.산으로 오라갈 수 있도록 높은 지형 -> 낮은 지형으로 가로 or 세로로 연결
// 3.긴 등산로를 위해 "딱 한 곳"을 정해서 K 깊이만큼 지형을 깎을 수 있음

// 지형 높이는 1 이상 20 이하
// 깎을 수 있는 최대 깊이는 1 이상 5 이하
// 가장 높은 봉우리는 최대 5개
// 지형을 1보다 작게 만드는 것도 가능

// 입력받을 때 가장 높은 봉우리 저장하기 (어레리? 큐?)
// 각 봉우리에서 출발, dfs로 탐색하며 가능한 모든 방향 다 가보기
// 깎을 때는 (이동 위치 높이 - K)가 현재 높이보다 작으면 깎고 들어가기