package bj.gold;

import java.util.*;
import java.io.*;

public class bj_17144_미세먼지안녕 {
	static class Pos {
		int x, y, val;

		public Pos(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	static int R, C, T;
	static int[][] map;
	static ArrayList<Pos> dust;
	static ArrayList<Pos> aircleaner;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		dust = new ArrayList<>();
		aircleaner = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					aircleaner.add(new Pos(i, j, -1));
				} else if (map[i][j] != 0) {
					dust.add(new Pos(i, j, map[i][j]));
				}
			}
		}

		System.out.println(goodbye());
	}

	static int goodbye() {
		while (T-- > 0) {
			// 미세먼지 확산
			diffuse();
			
			// 공기청정기 작동
			airclean();
		}

		return result();
	}

	// 확산
	static void diffuse() {
		for (int i = 0; i < dust.size(); i++) {
			Pos cur = dust.get(i);
			int cnt = 0;
			int tmp = cur.val / 5;

			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];

				if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
					map[nx][ny] += tmp;
					cnt++;
				}

			}
			map[cur.x][cur.y] = map[cur.x][cur.y] - cur.val + (cur.val - tmp * cnt);
		}
		setDust();
	}

	// 공기청정기
	static void airclean() {
		Pos top = aircleaner.get(0);
		Pos bot = aircleaner.get(1);
		
		// 위쪽 청정기		
		// 좌
		for (int x = top.x - 1; x > 0; x--) {
			map[x][0] = map[x - 1][0];
		}
		
		// 상
		for (int y = 0; y < C - 1; y++) {
			map[0][y] = map[0][y + 1];
		}
		
		// 우
		for (int x = 0; x < top.x; x++) {
			map[x][C - 1] = map[x + 1][C - 1];
		}
		
		// 하
		for (int y = C - 1; y > 0; y--) {
			map[top.x][y] = map[top.x][y - 1];
		}
		map[top.x][1] = 0;
		
		// 아래쪽 청정기		
		// 좌
		for (int x = bot.x + 1; x < R - 1; x++) {
			map[x][0] = map[x + 1][0];
		}
		
		// 하
		for (int y = 0; y < C - 1; y++) {
			map[R - 1][y] = map[R - 1][y + 1];
		}

		
		// 우
		for (int x = R - 1; x > bot.x; x--) {
			map[x][C - 1] = map[x - 1][C - 1];
		}
		
		// 상
		for (int y = C - 1; y > bot.y; y--) {
			map[bot.x][y] = map[bot.x][y - 1];
		}
		map[bot.x][1] = 0;
		
		setDust();
	}
	
	static void setDust() {
		dust.clear();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					dust.add(new Pos(i, j, map[i][j]));
				}
			}
		}
	}

	// 남은 미세먼지 양
	static int result() {
		int result = 0;

		for (int i = 0; i < dust.size(); i++) {
			Pos cur = dust.get(i);
			result += map[cur.x][cur.y];
		}

		return result;
	}
}

// 1.미세먼지 확산
// 인접 네 방향으로 확산
// 빈 칸이 아니면 확산 X (공기청정기, 범위 밖)
// 확산되는 양 : A / 5 (소수점 버림)
// 확산 후 남는 양 : A - (A / 5) * 확산된 방향의 개수

// 2.공기청정기 작동
// 위쪽 공기청정기는 반시계, 아래쪽은 시계방향 순환.. 그림 참고
// 작동시 바람 방향대로 한 칸씩 이동
// 공기청정기로 들어온 먼지들은 정화됨
// 공기청정기 : -1

// 미세먼지 위치 어레리에 저장해두고 확장될 때 새로운 어레리에 추가되는 미세먼지들 넣기
// 

