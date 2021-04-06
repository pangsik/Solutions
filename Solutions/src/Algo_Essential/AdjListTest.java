package Algo_Essential;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
 * 가중치 없는 양방향 그래프
 */
public class AdjListTest {
	static int N;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<Integer>();
		}

		StringTokenizer st = null;
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
			adjList[to].add(from);
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

			for (int temp : adjList[current]) {

				if (!visited[temp]) {
					queue.offer(temp);
					visited[temp] = true;
				}
			}
		}
	}

	private static void dfs(int current) {
		visited[current] = true;
		System.out.println((char) (current + 65));

		for (int temp : adjList[current]) {
			if (!visited[temp]) {
				dfs(temp);
			}
		}
	}
}
