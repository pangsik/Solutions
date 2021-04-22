package ssw;

import java.io.*;
import java.util.*;

public class d4_5643_키순서4_DFS_GT {
	static int N, M, adj[][];
	static int[] gtCnt, ltCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 학생 수
			M = Integer.parseInt(br.readLine()); // 키 비교 횟수
			adj = new int[N + 1][N + 1];
			gtCnt = new int[N + 1];
			ltCnt = new int[N + 1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a < b
				adj[a][b] = 1;
			} // 친구 키 관계로 인접행렬 대입
			
			int answer = 0;			
			for (int i = 1; i <= N; i++) {
				dfs(i, i, new boolean[N + 1]);
			}
			
			for (int i = 1; i <= N; i++) {
				if (gtCnt[i] + ltCnt[i] == N - 1) answer++;
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	private static void dfs(int cur, int src, boolean[] visited) {
		visited[cur] = true;
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i] && adj[cur][i] == 1) {
				dfs(i, src, visited);
				gtCnt[src]++;
				ltCnt[i]++;
			}
		}
	}
}
