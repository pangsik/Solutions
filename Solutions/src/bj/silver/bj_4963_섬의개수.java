package bj.silver;

import java.io.*;
import java.util.*;

public class bj_4963_섬의개수 {
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int w = -1, h = -1;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) break;
			
			map = new int[h][w];
			visited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 0) {
						visited[i][j] = true;
					}
				}
			}

			int cnt = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (!visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}

			System.out.println(cnt);

		}

		br.close();
	}

	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<int[]>();
		visited[x][y] = true;
		
		queue.offer(new int[] {x, y});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int d = 0; d < 8; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (nx >= 0 && nx < h && ny >= 0 && ny < w && !visited[nx][ny]) {
					queue.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
				}
			}
		}
	}
}

// 8방 탐색 bfs