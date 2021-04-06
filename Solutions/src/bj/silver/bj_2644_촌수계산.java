package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2644_촌수계산 {
	static boolean[][] g;
	static boolean[] visited;
	static Queue<Integer> q;
	static int N, chon = -1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken()) - 1;
		int b = Integer.parseInt(st.nextToken()) - 1;
		int M = Integer.parseInt(br.readLine());
		g = new boolean[N][N];
		visited = new boolean[N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			g[x][y] = true;
			g[y][x] = true;
		}

		bfs(a, b);
		System.out.println(chon);

		br.close();
	}

	static void bfs(int a, int b) {
		q = new LinkedList<Integer>();
		visited[a] = true;
		q.add(a);
		int cnt = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int cur = q.poll();
				if (cur == b) {
					chon = cnt;
					return;
				}
				for (int j = 0; j < N; j++) {
					if (g[cur][j] && !visited[j]) {
						q.offer(j);
						visited[j] = true;
					}
				}
			}
			cnt++;
		}
	}
}

// 