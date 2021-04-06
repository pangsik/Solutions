package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2606_바이러스 {
	static boolean[][] g;
	static boolean[] visited;
	static int N, cnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터 수
		int M = Integer.parseInt(br.readLine()); // 연결된 쌍의 수
		
		g = new boolean[N][N];
		visited = new boolean[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			g[x][y] = true;
			g[y][x] = true;
		}
		
		dfs(0);
		
		System.out.println(cnt);
		
		br.close();
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		
		for (int i = 0; i < N; i++) {
			if (g[cur][i] && !visited[i]) {
				cnt++;
				dfs(i);
			}
		}
	}
}

// 