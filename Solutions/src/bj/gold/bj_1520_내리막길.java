package bj.gold;

import java.util.*;
import java.io.*;

public class bj_1520_내리막길 {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N, M, cnt;
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		visited[0][0] = true;
		dfs(0, 0);
		
		System.out.println(cnt);
		
		br.close();
	}
	
	static void dfs(int x, int y) {
		if (x == N - 1 && y == M - 1) {
			cnt++;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] < map[x][y] && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny);
				visited[nx][ny] = false;
			}
		}
	}
}
