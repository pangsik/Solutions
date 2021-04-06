package jo;

import java.io.*;
import java.util.*;

public class jo_1681_해밀턴순환회로 {
	static int N, min = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0] = true;
		dfs(0, 0, 0);
		System.out.println(min);
		
		br.close();
	}
	
	static void dfs(int cur, int cnt, int cost) { // 현재 위치, 방문 지점 수, 누적 비용
		// 모든 지점 방문 완료
		if (cnt == N - 1) {
			if (map[cur][0] == 0) return; // 회사로 돌아가는 길이 없을 때
			cost += map[cur][0]; // 회사로 돌아가는 비용
			min = min < cost ? min : cost;
			return;
		}
		
		// 가지치기
		if (cost >= min)
			return;
		
		// 탐색
		for (int i = 1; i < N; i++) {
			if (visited[i] || map[cur][i] == 0) continue; // 이미 방문 or 길 없는 경우
			
			visited[i] = true;
			dfs(i, cnt + 1, cost + map[cur][i]);
			visited[i] = false;
		}
	}
}

// 