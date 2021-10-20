package bj.gold;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.20
 * 풀이 시간 : 60분
 * 코멘트
 * 진짜 엿같은 문제.. di dj 스펠링 실수해서 틀렸음
 * 그래도 이딴 문제는 그냥 대놓고 하드코딩 하는게 맘 편할듯
 */

public class bj_20057_마법사상어와토네이도_1 {
	static int N, answer;
	static int[] di = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] dj = { -1, 0, 1, 0 };	
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		Solution();
		
		System.out.println(answer);
		
		br.close();
	}
	
	private static void Solution() {
		int x = N / 2;
		int y = N / 2;
		
		int d = 0;
		int val = 1;
		
		while (true) {
			for (int i = 0; i < val; i++) {
				x += di[d];
				y += dj[d];
				
				if (!check(x, y))
					return;
				
				tornado(x, y, d);
			}
			
			d = (d + 1) % 4;
			
			for (int i = 0; i < val; i++) {
				x += di[d];
				y += dj[d];

				tornado(x, y, d);
			}

			d = (d + 1) % 4;
			val++;
		}
	}

	private static void tornado(int x, int y, int d) {
		int total = map[x][y];
		int D = d;
		int amount = 0; // 이동할 모래 양
		int nx = x;
		int ny = y;
		
		// 직진(2칸) 5%
		amount = (int) (map[x][y] * 0.05);
		total -= amount;
		nx = x + di[d]*2;
		ny = y + dj[d]*2;
		moveSand(nx, ny, amount);
		
		
		// 좌우 7%
		amount = (int) (map[x][y] * 0.07);
		
		total -= amount;
		d = (D + 1) % 4;
		nx = x + di[d];
		ny = y + dj[d];
		moveSand(nx, ny, amount);
		
		total -= amount;
		d = (D + 3) % 4;
		nx = x + di[d];
		ny = y + dj[d];
		moveSand(nx, ny, amount);
		
		
		// 좌우(2칸) 2%
		amount = (int) (map[x][y] * 0.02);
		
		total -= amount;
		d = (D + 1) % 4;
		nx = x + di[d]*2;
		ny = y + dj[d]*2;
		moveSand(nx, ny, amount);
		
		total -= amount;
		d = (D + 3) % 4;
		nx = x + di[d]*2;
		ny = y + dj[d]*2;
		moveSand(nx, ny, amount);
		
		
		// 좌하, 우하 1%
		amount = (int) (map[x][y] * 0.01);
		
		total -= amount;
		d = (D + 1) % 4;
		nx = x + di[d];
		ny = y + dj[d];
		d = (D + 2) % 4;
		nx += di[d];
		ny += dj[d];
		moveSand(nx, ny, amount);

		total -= amount;
		d = (D + 3) % 4;
		nx = x + di[d];
		ny = y + dj[d];
		d = (D + 2) % 4;
		nx += di[d];
		ny += dj[d];
		moveSand(nx, ny, amount);
		
		
		// 좌상, 우상 10%
		amount = (int) (map[x][y] * 0.1);

		total -= amount;
		d = (D + 1) % 4;
		nx = x + di[d];
		ny = y + dj[d];
		d = D;
		nx += di[d];
		ny += dj[d];
		moveSand(nx, ny, amount);

		total -= amount;
		d = (D + 3) % 4;
		nx = x + di[d];
		ny = y + dj[d];
		d = D;
		nx += di[d];
		ny += dj[d];
		moveSand(nx, ny, amount);
		
		// 상(1칸) 나머지
		d = D;
		nx = x + di[d];
		ny = y + dj[d];
		moveSand(nx, ny, total);
		
		map[x][y] = 0;
	}
	
	private static void moveSand(int nx, int ny, int amount) {
		if (!check(nx, ny)) {
			answer += amount;
		}
		else {
			map[nx][ny] += amount;
		}
	}
	
	private static boolean check(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			return false;
		}
		
		return true;
	}
}

// N*N
// 중앙에서 시작, 소용돌이 모양으로 나옴
// 토네이도 한 칸 이동마다 특정 방향 위치에 일정 비율로 날림
// nx,ny 기준, 현재 진행 방향 기준 직진이 상이라고 했을 때
// 좌우 7%
// 좌우(2칸) 2%
// 좌하, 우하 1%
// 좌상, 우상 10%
// 상(2칸) 5%
// 상 나머지%

// nx,ny에는 모래가 남지 않음
// 다른 칸에 모래가 날리면 원래 있던 모래 + 날린 모래가 됨
// 격자 밖으로 모래가 나가면 answer에 더해주기





