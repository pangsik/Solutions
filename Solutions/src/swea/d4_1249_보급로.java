package swea;

import java.io.*;
import java.util.*;

class Pos {
	int x, y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class d4_1249_보급로 {
	static int[][] map, cost;
	static boolean[][] visited;
	static int N;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1249.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String tmp = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp.charAt(j) - '0';
				}
			}

			sb.append('#').append(tc).append(' ').append(getMin()).append('\n');
		}
		System.out.println(sb);

		br.close();
	}

	private static int getMin() {
		int min = Integer.MAX_VALUE;
		Queue<Pos> q = new LinkedList<>();
		cost = new int[N][N];
		
		// 시작 (0,0)
		q.offer(new Pos(0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Pos p = q.poll();

			// 끝 (N-1,N-1) min값 비교
			if (p.x == N - 1 && p.y == N - 1) {
				min = min < cost[N - 1][N - 1] ? min : cost[N - 1][N - 1];
			}

			// 가지치기
			if (min <= cost[p.x][p.y]) {
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int nx = p.x + di[d];
				int ny = p.y + dj[d];
				// 맵 밖으로 안나가고
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					// 방문 이력이 없을 때 or 새로운 길이 비용이 더 적을 때
					if (!visited[nx][ny] || cost[nx][ny] > cost[p.x][p.y] + map[nx][ny]) {
						q.offer(new Pos(nx, ny));
						visited[nx][ny] = true;
						cost[nx][ny] = cost[p.x][p.y] + map[nx][ny];
					}
				}
			}
		}

		return min;
	}
}

// 