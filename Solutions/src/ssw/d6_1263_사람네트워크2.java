package ssw;

import java.io.*;
import java.util.*;

public class d6_1263_사람네트워크2 {
	static final int INF = 999999999;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d6_1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int[][] distance = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					distance[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && distance[i][j] == 0)
						distance[i][j] = INF;
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k)
						continue; // 출발지와 경유지가 같다면 다음 출발지
					for (int j = 0; j < N; j++) {
						if (i == j || k == j)
							continue; // 경유지와 목적지가 같거나 출발지가 곧 목적지라면 패스
						if (distance[i][k] != INF && distance[k][j] != INF
								&& distance[i][j] > distance[i][k] + distance[k][j]) {
							distance[i][j] = distance[i][k] + distance[k][j];
						}
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += distance[i][j];
				}
				if (min > sum) min = sum;
			}
			
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}

// 영향력 : 본인에서 다른 모든 사람까지 최단거리의 합
// 최단거리 합 => 플로이드 워셜
