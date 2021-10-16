package swea;

import java.io.*;
import java.util.*;

public class d4_5643_키순서5_DFS_Memo {
	static int N, M, adj[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 학생 수
			M = Integer.parseInt(br.readLine()); // 키 비교 횟수
			adj = new int[N + 1][N + 1];
			
			for (int i = 0; i < N; i++) {
				adj[i][0] = -1;
			}
			// 나보다 큰 애들 다 구하고 작은애들 찾을거라 큰 애들 구한 값으로 작은애들 바로바로 채우면 됨
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a < b
				adj[a][b] = 1;
			} // 친구 키 관계로 인접행렬 대입
			
			int answer = 0;			
			for (int i = 1; i <= N; i++) {
				// 메모의 상태를 보고 아직 탐색 전이면 자신보다 큰 학생 쭉 따라서 탐색
				if (adj[i][0] == -1) dfs(i);
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					adj[0][j] += adj[i][j];
				}
			} // 자신보다 작은 학생의 수 카운팅
			
			for (int i = 1; i <= N; i++) {
				if (adj[i][0] + adj[0][i] == N - 1) answer++;
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	private static void dfs(int cur) {
		for (int i = 1; i <= N; i++) {
			if (adj[cur][i] == 1) {
				// 아직 탐색하지 않았으면 탐색하러 들어가기
				if (adj[i][0] == -1) dfs(i);
				
				// i번 학생을 탐색하고 돌아왔거나, 이미 탐색돼있는 상태
				// i번 학생보다 큰 학생이 있을 때
				if (adj[i][0] > 0) {
					for (int j = 1; j <= N; j++) {
						if (adj[i][j] == 1) adj[cur][j] = adj[i][j];
					}
				}
			}
		}
		
		int cnt = 0;
		for (int i = 1; i <= N; i++) cnt += adj[cur][i]; // cur 학생보다 큰 학생 수 세서 cnt에 저장 후 메모칸에 적기
		adj[cur][0] = cnt;
	}
}
