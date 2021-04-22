package ssw;

import java.io.*;
import java.util.*;

public class d4_5643_키순서 {
	static int N, M;
	static boolean[][] adj;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()); // 학생 수
			M = Integer.parseInt(br.readLine()); // 키 비교 횟수
			adj = new boolean[N + 1][N + 1];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a < b
				adj[a][b] = true;
			}
			
			int answer = 0;
			for (int i = 1; i <= N; i++) {
				if (bfs(i)) answer++;
			}
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	// 시작점보다 큰 친구 수 + 작은 친구 수 == N - 1 이면 모든 친구들과 상하관계가 성립함!!
	static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean visited[] = new boolean[N + 1];
		q.offer(start);
		visited[start] = true;
		
		int cnt = 0;
		
		// start보다 큰 친구들 수 구하기
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				// 방문 안했고 본인보다 큰 수라면
				if (!visited[i] && adj[cur][i]) {
					q.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		q.offer(start);
		// start보다 작은 친구들 수 구하기
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 1; i <= N; i++) {
				// 방문 안했고 본인보다 작은 수라면
				if (!visited[i] && adj[i][cur]) {
					q.offer(i);
					visited[i] = true;
					cnt++;
				}
			}
		}
		
		if (cnt == N - 1) return true;
		else return false;
	}
}


// 1~N번 학생들 두 명씩 키 비교결과 일부 제공 (모든 학생의 키는 다르다)
// 키 비교 결과로부터 모든 학생 중 오름차순으로 자신이 몇 번째인지 알 수 있는 학생 수 출력하기
// 