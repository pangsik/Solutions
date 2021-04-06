package Algo_Essential;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
7
8
0 1
0 2
1 3
1 4
2 4
3 5
4 5
5 6
*/
/**
 * @author THKim
 */
public class AdjListTest_node {

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}

		public Node(int vertex) {
			super();
			this.vertex = vertex;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}
	}

	static int N;
	static Node[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		adjList = new Node[N];

		StringTokenizer st = null;
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}

		System.out.println("===============bfs================");
//		bfs();

		visited = new boolean[N];
		System.out.println("===============dfs================");
		dfs(0);

		br.close();
	}

	private static void bfs() {

		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		int start = 0;
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			System.out.println((char) (current + 65));

			for (Node temp = adjList[current]; temp != null; temp = temp.next) {

				if (!visited[temp.vertex]) {
					queue.offer(temp.vertex);
					visited[temp.vertex] = true;
				}
			}
		}
	}

	private static void dfs(int current) {
		visited[current] = true;
		System.out.println((char) (current + 65));

		for (Node temp = adjList[current]; temp != null; temp = temp.next) {
			if (!visited[temp.vertex]) {
				dfs(temp.vertex);
			}
		}
	}
}
