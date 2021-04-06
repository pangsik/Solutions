package ssw;

import java.io.*;
import java.util.*;

public class d4_3124_최소스패닝트리 {
	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
//			return this.weight - o.weight;
			return Integer.compare(this.weight, o.weight);
		}

	}

	static int V, E; // V : 정점 개수, E : 간선 개수
	static int parents[];
	static Edge[] edgeList;

	static void makeSet() {
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = findSet(parents[a]); // path compression 후
	}

	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_d0_1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			parents = new int[V];
			edgeList = new Edge[E];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgeList);
			
			makeSet();
			
			long result = 0;
			int count = 0;
			
			for (Edge edge : edgeList) {
				if (union(edge.from, edge.to)) {
					result += edge.weight;
					if (++count == V - 1) break;
				}
			}

			sb.append('#').append(tc).append(' ').append(result);
			System.out.println(sb);
		}

		br.close();
	}
}
// 