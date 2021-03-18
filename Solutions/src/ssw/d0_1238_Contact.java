package ssw;

import java.io.*;
import java.util.*;

public class d0_1238_Contact {
	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}

	static Node[] stdList;
	static int[] visited;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		for (int tc = 1; tc <= 10; tc++) {
			StringBuilder sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine(), " ");
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			stdList = new Node[101];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < len / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				stdList[from] = new Node(to, stdList[from]);
			}

			sb.append("#").append(tc).append(" ").append(bfs(start));
			System.out.println(sb);
		}
	}

	static int bfs(int start) {
		visited = new int[101];
		Queue<Integer> queue = new LinkedList<>();

		queue.offer(start);
		visited[start] = 1;
		int current, size, depth = 1;

		while (!queue.isEmpty()) {
			size = queue.size();
			depth++;

			while (--size >= 0) {
				current = queue.poll();
				for (Node temp = stdList[current]; temp != null; temp = temp.next) {
					if (visited[temp.vertex] == 0) {
						queue.offer(temp.vertex);
						visited[temp.vertex] = depth;
					}
				}
			}
		}
		int max = 0;
		for (int i = 1; i < 101; i++) {
			if (visited[i] == depth - 1)
				max = i;
		}
		
		return max;
	}
}
// 유향 그래프
// bfs
// 가장 마지막에 연락받는 사람들 중 번호가 가장 큰 사람.. (마지막 depth 노드들 체크)