package bj.gold;

import java.util.*;
import java.io.*;

public class bj_14502_연구소 {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] original, map;
	static int N, M, max = 0;
	static ArrayList<Pos> virus = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		original = new int[N][M];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
				if (original[i][j] == 2)
					virus.add(new Pos(i, j));
			}
		}

		wallDfs(0, 0, 0);

		System.out.println(max);

		br.close();
	}

	// 벽 3개 세우고 bfs 돌기
	static void wallDfs(int x, int y, int cnt) {
		if (cnt == 3) {
			init();
			for (Pos p : virus) {
				virusBfs(p);
			}

			int tmp = safetyZone();
			if (max < tmp) {
				max = tmp;
			}
			
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (original[i][j] == 0) {
					original[i][j] = 1;
					wallDfs(i, j, cnt + 1);
					original[i][j] = 0;
				}
			}
		}
	}

	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				map[i][j] = original[i][j];
		}
	}

	// 바이러스 bfs 돌기
	static void virusBfs(Pos start) {
		Queue<Pos> queue = new LinkedList<>();
		queue.offer(new Pos(start.x, start.y));

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
					queue.offer(new Pos(nx, ny));
					map[nx][ny] = 2;
				}
			}
		}
	}

	static int safetyZone() {
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				if (map[i][j] == 0)
					safe++;
		}
		return safe;
	}

	static class Pos {
		private int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}

// 0 빈 칸
// 1 벽
// 2 바이러스
// 바이러스는 벽이 없으면 모든 빈 칸으로 퍼져나갈 수 있음
// 모~~든 빈 칸에 벽 3개 세워보기? ****
// 벽 3개 세우고 바이러스 bfs 돌기
// 그러고 빈 칸 수 세서 max값 비교
// 
