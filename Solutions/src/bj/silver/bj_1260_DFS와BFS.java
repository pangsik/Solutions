package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1260_DFS와BFS {
	static boolean[] visited;
	static boolean[][] itemList;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()) + 1;
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		itemList = new boolean[N][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			itemList[to][from] = itemList[from][to] = true; // 무향
		}

		visited = new boolean[N];
		dfs(V);
		System.out.println();

		bfs(V);

		br.close();
	}

	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.print(current + " ");

			for (int i = 0; i < N; i++) {
				if (itemList[current][i] && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

	static void dfs(int current) {
		visited[current] = true;
		System.out.print(current + " ");

		for (int i = 0; i < N; i++) {
			if (itemList[current][i] && !visited[i]) {
				dfs(i);
			}
		}
	}
}
