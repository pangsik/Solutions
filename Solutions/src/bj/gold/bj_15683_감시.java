package bj.gold;

import java.util.*;
import java.io.*;

public class bj_15683_감시 {
	static int N, M, min;
	static int[][] original;
	static ArrayList<int[]> cctvs;
	
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		original = new int[N][M];
		cctvs = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
				if (original[i][j] >= 1 && original[i][j] <= 5) {
					cctvs.add(new int[] { i, j });
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		dfs(0, original);
		
		System.out.println(min);
		
		br.close();
	}
	
	// dfs
	static void dfs(int idx, int[][] map) {
		// 모든 cctv 선택 완료
		if (idx == cctvs.size()) {
			// 사각지대 카운트 및 최소값 갱신
			blindSpot(map);
			return;
		}
		
		// 현재 선택된 cctv 좌표 및 종류
		int[] cur = cctvs.get(idx);
		int x = cur[0];
		int y = cur[1];
		int cctv = map[x][y]; // 1~5
		
		// cctv 종류 따라서 n번 회전시킨 경우의 수를 따짐
		switch (cctv) {
		case 1: // 한 방향
			for (int d = 0; d < 4; d++) {
				int[][] copy = copy(map);
				set(x, y, d, copy);
				
				dfs(idx + 1, copy);
			}
			
			break;
			
		case 2: // 양쪽으로 두 방향 
			for (int d = 0; d < 2; d++) {
				int[][] copy = copy(map);
				set(x, y, d, copy);
				set(x, y, d + 2, copy);
				
				dfs(idx + 1, copy);
			}
			
			break;
			
		case 3: // ㄱ
			for (int d = 0; d < 4; d++) {
				int[][] copy = copy(map);
				set(x, y, d, copy);
				set(x, y, (d + 1) % 4, copy);
				
				dfs(idx + 1, copy);
			}
			break;
			
		case 4: // 세 방향
			for (int d = 0; d < 4; d++) {
				int[][] copy = copy(map);
				set(x, y, d, copy);
				set(x, y, (d + 1) % 4, copy);
				set(x, y, (d + 2) % 4, copy);
				
				dfs(idx + 1, copy);
			}
			
			break;
			
		case 5: // 회전 x
			int[][] copy = copy(map);
			set(x, y, 0, copy);
			set(x, y, 1, copy);
			set(x, y, 2, copy);
			set(x, y, 3, copy);
			
			dfs(idx + 1, copy);
			
			break;
		}
		
	}
	
	// cctv 감시 위치 표시
	static void set(int x, int y, int d, int[][] map) {
		int nx = x;
		int ny = y;
		// 밖으로 나가거나 벽이면 스톱
		while(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != 6) {
			x = nx;
			y = ny;
			
			// 감시구역으로.. (cctv인 경우는 그냥 지나감)
			if (map[x][y] == 0) {
				map[x][y] = 7;
			}
			
			nx = x + di[d];
			ny = y + dj[d];
		}
	}
	
	// 맵 복사
	static int[][] copy(int[][] map) {
		int[][] copy = new int[N][M];
		for (int i = 0 ; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
			}
		}
		
		return copy;
	}
	
	// 사각지대 카운트 및 최소값 갱신
	static void blindSpot(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		min = Integer.min(min, cnt);
	}
}

// cctv 종류는 5가지
// 1 : 한 방향 (4)
// 2 : 양쪽으로 두 방향 (2)
// 3 : ㄱ자모양 두 방향 (4)
// 4 : 세 방향 (4)
// 5 : 네 방향 (1)

// 0 : 빈 칸
// 6 : 벽
// 1 ~ 5 : cctv

// 7 : 감시구역

// 벽 너머로는 감시 못함
// cctv 너머로는 감시 가능

// 사무실 상태, cctv 주어졌을 때 사각지대의 최소 크기를 구해라