package bj.gold;

import java.util.*;
import java.io.*;

public class bj_1753_최단경로 {
	static class Node implements Comparable<Node> {
		int vertex;
		int dist;

		public Node(int vertex, int dist) {
			super();
			this.vertex = vertex;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine()) - 1;

		ArrayList<Node>[] adjList = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken()) - 1; // from
			int to = Integer.parseInt(st.nextToken()) - 1; // to
			int weight = Integer.parseInt(st.nextToken()); // weight
			adjList[from].add(new Node(to, weight));
		}

		int[] distance = new int[V];
		boolean[] visited = new boolean[V];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0;

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(K, distance[K]));

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			if (visited[cur.vertex])
				continue;
			visited[cur.vertex] = true;

			// current를 경유지로 해서 갈 수 있는 비용이 적다면 갱신
			for (Node node : adjList[cur.vertex]) {
				// 연결이 됐는지 확인하는 작업 필요없다(인접 리스트니까)
				if (!visited[node.vertex] && distance[node.vertex] > distance[cur.vertex] + node.dist) {
					distance[node.vertex] = distance[cur.vertex] + node.dist;
					queue.offer(new Node(node.vertex, distance[node.vertex]));
				}
			}
		}

		for (int i = 0; i < V; i++) {
			if (distance[i] == Integer.MAX_VALUE)
				System.out.println("INF");
			else
				System.out.println(distance[i]);
		}

		br.close();
	}
}
