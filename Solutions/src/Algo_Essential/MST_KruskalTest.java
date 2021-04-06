package Algo_Essential;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST_KruskalTest {
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
		if (parents[a] == a) return a;
//		return findSet(parents[a]); // path compression 전
		return parents[a] = findSet(parents[a]); // path compression 후
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		parents = new int[V];
		edgeList = new Edge[E];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		} // 간선리스트 입력
		
		// 1.간선리스트 가중치 기준 오름차순 정렬 (Edge 클래스에서 Comparable 해둔거)
		Arrays.sort(edgeList);
		
		makeSet();
		
		int result = 0; // 총 가중치
		int count = 0; // 선택한 간선 수
		
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) { // 싸이클 발생 안함
				result += edge.weight;
				if (++count == V - 1) break;
			}
		}
		
		System.out.println(result);
		
		br.close();
	}
}
