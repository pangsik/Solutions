package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1325_효율적인해킹 {
	static int N;
	static ArrayList<Integer>[] adjList;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()) + 1;
		int M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
		}

		int max = 0;
		for (int i = 1; i < N; i++) {
//			int cnt = bfs(i);
			visited = new boolean[N];
			int cnt = dfs(i);
			if (max < cnt) {
				max = cnt;
				sb.setLength(0);
				sb.append(i).append(' ');
			} else if (cnt == max) {
				sb.append(i).append(' ');
			}
		}

		System.out.println(sb);

		br.close();
	}

	static int bfs(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		queue.offer(start);
		visited[start] = true;

		int cnt = 0;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			cnt++;

			for (int tmp : adjList[cur]) {
				if (!visited[tmp]) {
					queue.offer(tmp);
					visited[tmp] = true;
				}
			}
		}

		return cnt;
	}
	
	static int dfs (int cur) {
		visited[cur] = true;
		int ret = 1;
		
		for (int tmp : adjList[cur]) {
			if (!visited[tmp]) {
				ret += dfs(tmp);
			}
		}
		
		return ret;
	}
}

// N : 노드 수
// M : 간선 수
// 단방향?
// 인접 리스트