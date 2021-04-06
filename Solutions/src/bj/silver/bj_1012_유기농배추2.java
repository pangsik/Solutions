package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1012_유기농배추2 {
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static int M, N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new int[M][N];
			visited = new boolean[M][N];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
		
		br.close();
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] == 1 && !visited[nx][ny]) {
				dfs(nx, ny);
			}
		}
	}
}

// 