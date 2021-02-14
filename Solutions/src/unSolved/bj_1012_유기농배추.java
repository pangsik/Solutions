package unSolved;

import java.io.*;
import java.util.*;

public class bj_1012_유기농배추 {
	static int[] di = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dj = {0, 1, 0, -1};
	static int[][] map;
	static boolean[][] visit;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visit = new boolean[N][M];
			answer = 0;
			
			// 배추 위치 입력
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			
			
			sb.append(answer).append("\n");
		}
		
		System.out.println(sb);

		br.close();
	}
	
	static void dfs() {
		
	}
}

// 다음 순열 구하는 규칙 찾아서 swap해서 풀기 ㅠ
