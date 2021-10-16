package swea;

import java.util.*;
import java.io.*;

/*
 * @date : 21.10.16
 * 풀이 시간 : 25분
 * 코멘트
 * bfs를 통해 마름모를 그리는게 포인트인 문제
 * 첫 시도에서는 마름모를 매번 새로 그렸는데, 그냥 bfs 깊이 1 깊어질 때 마다 체크를 하면 됐던 일.. 선홍이형 코드 Good
 * 여러 풀이 보고 바로 풀어서 금방 푼 듯, 좀 지나서 다시 풀어보기
 */

public class Solution_sw_2117_홈방범서비스_2 {
	static int N, M, answer;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 1;
			Solution();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bfs(i, j);
			}
		}
	}

	private static void bfs(int x, int y) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		visited = new boolean[N][N];
		
		visited[x][y] = true;
		dq.add(new int[] { x, y });
		
		int cnt = 0;
		int K = 0;
		while (!dq.isEmpty()) {
			K++;
			int size = dq.size();
			
			for (int i = 0; i < size; i++) {
				int[] cur = dq.poll();
				int curX = cur[0];
				int curY = cur[1];
				cnt += map[curX][curY];
				
				for (int d = 0; d < 4; d++) {
					int nx = curX + di[d];
					int ny = curY + dj[d];
					
					if (!check(nx, ny))
						continue;
					
					visited[nx][ny] = true;
					dq.add(new int[] { nx, ny });
				}
			}
			
			if (cnt * M - getCost(K) >= 0) {
				answer = Math.max(answer, cnt);
			}
		}
	}

	private static int getCost(int K) {
		return K * K + (K - 1) * (K - 1);
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
			return false;
		
		return true;
	}
}



















