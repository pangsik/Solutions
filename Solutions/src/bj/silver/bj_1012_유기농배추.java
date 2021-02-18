package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1012_유기농배추 {
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static int answer, N, M;
	static boolean check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			answer = 0;

			// 배추 위치 입력
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					check = false;
					dfs(i, j);
					if (check) answer++;
				}
			}

			sb.append(answer).append("\n");
		}

		System.out.println(sb);

		br.close();
	}

	static void dfs(int i, int j) {
		if (visited[i][j] || map[i][j] == 0)
			return;

		visited[i][j] = true;
		check = true;

		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
				dfs(ni, nj);
			}
		}
	}
}
