package Algo_Essential;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
// 정점 수, 간선 수, 간선 관계.. 무향 그래프
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
public class AdjMatrixTest {

	static int N;
	static boolean[][] adjMatrix;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		adjMatrix = new boolean[N][N];

		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[to][from] = adjMatrix[from][to] = true; // 무향이니까 양쪽 모두 같은 값 대칭으로 넣어줌
		}

		bfs();

		br.close();
	}

	private static void bfs() {

		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[N];

		// 탐색시작정점 : 0으로 출발
		int start = 0;
		queue.offer(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();
			// 현재 정점에 관련된 처리
			System.out.println((char) (current + 65));

			// 인접정점 탐색
			for (int i = 0; i < N; i++) {
				if (adjMatrix[current][i] // 인접정점
						&& !visited[i]) { // 미방문 정점
					queue.offer(i);
					visited[i] = true;
				}
			}
		}
	}

}
