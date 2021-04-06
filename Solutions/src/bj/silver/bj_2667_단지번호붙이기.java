package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2667_단지번호붙이기 {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
				if (map[i][j] == 0) {
					visited[i][j] = true;
				}
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					pq.offer(bfs(i, j));
				}
			}
		}

		System.out.println(pq.size());
		int size = pq.size();
		for (int i = 0; i < size; i++) {
			System.out.println(pq.poll());
		}
		
		br.close();
	}

	static int bfs(int x, int y) {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[x][y] = true;

		queue.offer(new int[] { x, y });
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			cnt++;

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
		
		return cnt;
	}
}

