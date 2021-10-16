package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.14
 * 풀이 시간 : 25분
 * 코멘트 : 어려움 없이 풀긴 했으나 max값 갱신, continue 빼먹는 실수 함.. 집중!
 */

public class Solution_sw_1949_등산로조성_2 {
	static int N, K, maxLen;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static ArrayDeque<int[]> peaks;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int peakHeight = 0;
			map = new int[N][N];
			peaks = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > peakHeight) {
						peaks.clear();
						peaks.offer(new int[] { i, j });
						peakHeight = map[i][j];
					}
					else if (map[i][j] == peakHeight) {
						peaks.offer(new int[] { i, j });
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
		
		while (!peaks.isEmpty()) {
			int[] cur = peaks.poll();
			visited = new boolean[N][N];
			visited[cur[0]][cur[1]] = true;
			
			dfs(cur[0], cur[1], 1, true);
		}
	}

	private static void dfs(int x, int y, int len, boolean flag) {
		if (maxLen < len) {
			maxLen = len;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (!check(nx, ny))
				continue;
			
			// 그냥 갈 수 있는 경우
			if (map[nx][ny] < map[x][y]) {
				visited[nx][ny] = true;
				dfs(nx, ny, len + 1, flag);
				visited[nx][ny] = false;
				continue;
			}
			
			// 깎아서 갈 수 있는 경우
			if (flag && map[nx][ny] - K < map[x][y]) {
				int origin = map[nx][ny];
				map[nx][ny] = map[x][y] - 1;
				
				visited[nx][ny] = true;
				dfs(nx, ny, len + 1, !flag);
				visited[nx][ny] = false;
				
				map[nx][ny] = origin;
			}
		}
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
			return false;
		
		return true;
	}
}

// N*N
// 등산로는 가장 높은 봉우리에서 시작
// 높 -> 낮 이동 가능, 4방으로 이동 가능
// 딱 한 군데를 K만큼 깎을 수 있음

// 가장 높은 봉우리들 저장
// 각 봉우리들에서 모두 시작해보기
// 가능한 방향 모두 탐색
// 단, 현재 위치보다 높거나 같을 때 => 깎기 사용 여부 체크, 깎았을 때 이동 가능 여부 체크
// 가능하다면 현재 높이 -1 해주고 진입, 되돌아오면 복구해주기 (visited도)
