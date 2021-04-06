package bj.gold;

import java.util.*;
import java.io.*;

public class bj_2636_치즈dfs {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int N, M, hour = 0, remain = 0, lastCheese = 0;
	static int[][] map;
	static boolean[][] air;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					remain++;
			}
		}
		
		melt();
		System.out.println(hour);
		System.out.println(lastCheese);
		
		br.close();
	}
	
	static void melt() {
		while (remain > 0) {
			lastCheese = remain;
			
			// 1.바깥공기 위치 판단
			air = new boolean[N][M];
			dfsAir(0, 0);
			
			// 2.치즈 녹이기
			meltCheese();
			
			hour++;
		}
	}
	
	// 1.바깥공기 판단 (air 배열에 저장)
	static void dfsAir(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0 && !air[nx][ny]) {
				air[nx][ny] = true;
				dfsAir(nx, ny);
			}
		}
	}
	
	// 2.치즈 녹이기
	static void meltCheese() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && isEdge(i, j)) {
					map[i][j] = 0;
					remain--;
				}
			}
		}
	}
	
	// 바깥 공기랑 닿은 치즈인지 확인
	static boolean isEdge(int x, int y) {
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (air[nx][ny]) {
				return true;
			}
		}
		
		return false;
	}
	
	static class Point {
		private int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

// 1.바깥 공기 위치를 판단
// 2.바깥 공기 위치를 기준으로 녹을 치즈들 선정
// 3.남은 치즈가 있는지 확인 후 남아있다면 1~2 반복
// 






