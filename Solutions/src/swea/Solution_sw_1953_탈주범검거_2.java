package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.14
 * 풀이 시간 : 15분
 * 코멘트 : 쉽게 풀긴 했으나 푼지 얼마 안 된 문제라 그런듯.. 나중에 또 풀어보기
 */

public class Solution_sw_1953_탈주범검거_2 {
	static int N, M, R, C, L, answer;
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static int[][] directions = {
			{}, // 0
			{ 0, 1, 2, 3 }, // 1 상 하 좌 우
			{ 0, 2 }, // 2 상 하
			{ 3, 1 }, // 3 좌 우
			{ 0, 1 }, // 4 상 우
			{ 2, 1 }, // 5 하 우
			{ 2, 3 }, // 6 하 좌
			{ 0, 3 }, // 7 상 좌
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Solution();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution() {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { R, C });
		visited[R][C] = true;
		answer = 1;
		
		int lv = 0;
		while (!dq.isEmpty()) {
			if (++lv == L)
				break;
			
			int size = dq.size();
			for (int i = 0; i < size; i++) {
				int[] cur = dq.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				int[] dir = directions[map[curX][curY]];
				for (int d : dir) {
					int nx = curX + di[d];
					int ny = curY + dj[d];
					
					if (!check(nx, ny, curX, curY))
						continue;
					
					dq.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					answer++;
				}
			}
		}
	}

	private static boolean check(int nx, int ny, int curX, int curY) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || visited[nx][ny])
			return false;
		
		int[] dir = directions[map[nx][ny]];
		for (int d : dir) {
			int nnx = nx + di[d];
			int nny = ny + dj[d];
			
			if (nnx == curX && nny == curY)
				return true;
		}
		
		return false;
	}
}

// 1 : 상 하 좌 우
// 2 : 상 하
// 3 : 좌 우
// 4 : 상 우
// 5 : 하 우
// 6 : 하 좌
// 7 : 상 좌


