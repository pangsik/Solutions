package unSolved;

import java.util.*;
import java.io.*;

public class bj_16236_아기상어 {
	static class shark {
		int x, y;

		shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, size, X, Y;
	static shark s;
	static int[][] map;
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌 (시계방향)
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					X = i;
					Y = j;
				}
			}
		}

		size = 2;
		int cnt = 0, time = 0;

		Queue<shark> queue = new LinkedList<>();
		int[][] visited;

		while (check()) {
			visited = new int[N][N];
			queue.offer(new shark(X, Y));
			visited[X][Y] = 1;

			int minDist = Integer.MAX_VALUE;
			int minX = Integer.MAX_VALUE;
			int minY = Integer.MAX_VALUE;

			while (!queue.isEmpty()) {
				shark current = queue.poll();

				for (int d = 0; d < 4; d++) {
					int nx = current.x + di[d];
					int ny = current.y + dj[d];

					// 맵 밖으로 나가면 컷, 아니면 그 위치까지의 거리정보 저장
					if (!(nx >= 0 && nx < N && ny >= 0 && ny < N))
						continue;

					// 못지나가는 자리면 컷 (본인보다 큰 물고기 or 이미 방문한 자리)
					if (map[nx][ny] > size || visited[nx][ny] != 0)
						continue;

					// 먹을 수 있는 물고기일 때 그 물고기 자리가 가장 가까운 자리인지 체크
					if (map[nx][ny] != 0 && map[nx][ny] < size) {
						// 새 방문지가 더 가까우면 min 바꿔줌
						if (visited[nx][ny] < minDist) {
							minDist = visited[nx][ny];
							minX = nx;
							minY = ny;
						}
						// 새 방문지랑 min 거리가 같다면
						else if (visited[nx][ny] == minDist) {
							// 더 위쪽에 있는 놈을 채택
							if (minX > nx) {
								minX = nx;
								minY = ny;
							}
							// 둘 다 같은 x좌표에 있으면 더 왼쪽에 있는 놈을 채택
							else if (minX == nx) {
								if (minY > ny) {
									minX = nx;
									minY = ny;
								}
							}
						}
					}
					visited[nx][ny] = visited[current.x][current.y] + 1;
					queue.offer(new shark(nx, ny));
				}
			}
			// 먹을놈 좌표 구했으면 (먹을놈이 있으면)
			if (minX != Integer.MAX_VALUE) {
				time += minDist;
				if (++cnt == size) {
					size++;
					cnt = 0;
				}
				map[X][Y] = 0;
				map[minX][minY] = 9;
				X = minX;
				Y = minY;
			}
			else
				break;
		}
		
		System.out.println(time);
		
		br.close();
	}

	// 먹을거 남았나?
	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && map[i][j] < size)
					return true;
			}
		}
		return false;
	}
}
// N * N
// 아기상어 크기 2.. 1초에 상하좌우로 한 칸씩 이동
// 본인보다 큰 물고기 칸은 지나갈 수 없음
// 본인과 같은 물고기는 지나갈 수 있지만 먹을 수 없음
// 본인보다 작은 물고기만 먹을 수 있음

// 아기상어 이동경로 결정 방법
// 1.먹을게 없으면 엄마 부름
// 2.먹을 수 있는 물고기 중 가장 가까운 물고기한테 감
// 2-1.거리는 아기상어 칸에서 물고기한테 이동할 때 지나야하는 칸의 개수의 최소값
// 2-2.거리가 가까운 고기가 많으면 가장 위의 물고기, 그러한 고기가 여러 마리면 가장 왼쪽 고기

// 1칸 이동 -> 1초.. 먹는 시간은 따로 없음 (도착과 동시에 먹었다고 봄)
// 본인 크기와 같은 수만큼 먹으면 크기 1 증가
// 엄마 안부르고 몇초동안 먹고있는지 구해라

// 0: 빈 칸
// 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
// 9: 아기 상어의 위치