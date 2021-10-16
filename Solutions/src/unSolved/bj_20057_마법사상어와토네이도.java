package unSolved;

import java.io.*;
import java.util.*;

public class bj_20057_마법사상어와토네이도 {
	static int N, answer;
	static int[][] map;
	static int[] di = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] dj = { -1, 0, 1, 0 };
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
		solution();
		
		System.out.println(answer);
		
		br.close();
	}
	
	private static void solution() {
		// 토네이도 위치 (중앙에서 시작)
		int r = N / 2;
		int c = N / 2;
		
		// 토네이도 진행 방향 (좌측 먼저 시작)
		int d = 0;
		
		// 한 방향으로 전진할 칸 수 go, 전진한 칸 수 cnt
		int go = 1;
		int cnt = 0;
		
		// 토네이도 0,0 도착 시 소멸
		while (r != 0 || c != 0) {
			// 토네이도 진행
			r += di[d];
			c += dj[d];
			
			// 바람 흩날리기
			tornado(r, c, d);
			
			// 전진 후 방향 전환
			if (go == ++cnt) {
				d = (d + 1) % 4;
				cnt = 0;
				
				// 방향 2번 바꾼 후 진행 수 1 증가
				if (d % 2 == 0) {
					go++;
				}
			}
		}
		
	}

	private static void tornado(int r, int c, int d) {
		double[] rates = { 0.07, 0.02, 0.1, 0.05 };
		int cnt = 0;
		
		for (int i = 0; i < 3; i++) {
			
			int nx = r + di[d] * i;
			int ny = c + dj[d] * i;
			
			// 5%
			if (i == 2) {
				if (check(nx, ny))
					map[nx][ny] += map[r][c] * rates[cnt];
				else {
					answer += map[r][c] * rates[cnt];
				}
				break;
			}
			
			for (int j = 1; j < 3 - i; j++) {
				int nd, nnx, nny;
				
				nd = (d + 1) % 4;
				// 우측 방향
				nnx = nx + di[nd] * j;
				nny = ny + dj[nd] * j;
				if (check(nnx, nny))
					map[nnx][nny] += map[r][c] * rates[cnt];
				else {
					answer += map[r][c] * rates[cnt];
				}
				
				nd = (d + 3) % 4;
				// 좌측 방향
				nnx = nx + di[nd] * j;
				nny = ny + dj[nd] * j;
				if (check(nnx, nny))
					map[nnx][nny] += map[r][c] * rates[cnt];
				else {
					answer += map[r][c] * rates[cnt];
				}
				
				cnt++;
			}
		}
		
		int nd = (d + 2) % 4;
		int nx = r + di[nd];
		int ny = c + dj[nd];
		int nnx, nny;
		
		nd = (d + 1) % 4;
		nnx = nx + di[nd];
		nny = ny + dj[nd];
		if (check(nnx, nny))
			map[nnx][nny] += map[r][c] * rates[cnt];
		else {
			answer += map[r][c] * rates[cnt];
		}
		
		nd = (d + 3) % 4;
		nnx = nx + di[nd];
		nny = ny + dj[nd];
		if (check(nnx, nny))
			map[nnx][nny] += map[r][c] * rates[cnt];
		else {
			answer += map[r][c] * rates[cnt];
		}
		
		map[r][c] = 0;
	}
	
	private static boolean check(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
			return false;
		
		return true;
	}
}

// N*N 격자, 가운데에서 토네이도 시작
// 가려는 방향에 모래 흩날리는데 비율이 적혀있는 칸이 맵 밖으로 나가는 모래 양을 구하라
// 알파 칸 : 55%, 그냥 비율별로 다 날리고 나머지 갖다 옮기는게 편할 듯

// 1.토네이도 시작 위치 구하기 (중앙)
// 2.토네이도 진행 반복문 작성 (칸마다 어느 방향을 향하고 있는지 구할 것)
// 3.바람 흩날려 모래 이동하는 메서드 작성

// 좌1 하1 우2 상2 좌3 하3 우4 상4 좌5 하5 우6 상6





