package bj.gold;

import java.io.*;
import java.util.*;

public class bj_14500_테트로미노 {
	static int N, M, max;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		max = 0;
		solution();
		
		System.out.println(max);
		
		br.close();
	}
	
	private static void solution() {
		// 모든 좌표에 대해 모든 경우 수행
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				square(i, j);
				line(i, j);
				rieul(i, j);
				nieun(i, j);
				oh(i, j);
			}
		}
	}

	private static void square(int x, int y) {
		// 사각형 만들 수 있는 범위
		if (x + 1 < N && y + 1 < M) {
			int sum = map[x][y] + map[x + 1][y] + map[x][y + 1] + map[x + 1][y + 1];
			max = Math.max(max, sum);
		}
	}

	private static void line(int x, int y) {
		int sum = 0;		
		// 세로
		if (x + 3 < N) {
			sum = map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 3][y];
			max = Math.max(max, sum);
		}
		
		// 가로
		if (y + 3 < M) {
			sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x][y + 3];
			max = Math.max(max, sum);
		}
	}

	private static void rieul(int x, int y) {
		int sum = 0;		
		// 서있는 모양
		if (x + 2 < N && y + 1 < M) {
			sum = map[x][y] + map[x + 1][y] + map[x + 1][y + 1] + map[x + 2][y + 1];
			max = Math.max(max, sum);
			
			sum = map[x + 1][y] + map[x][y + 1] + map[x + 1][y + 1] + map[x + 2][y];
			max = Math.max(max, sum);
		}
		
		// 누워있는 모양
		if (x + 1 < N && y + 2 < M) {
			sum = map[x + 1][y] + map[x][y + 1] + map[x + 1][y + 1] + map[x][y + 2];
			max = Math.max(max, sum);
			
			sum = map[x][y] + map[x][y + 1] + map[x + 1][y + 1] + map[x + 1][y + 2];
			max = Math.max(max, sum);
		}
	}

	private static void nieun(int x, int y) {
		int sum = 0;		
		if (x + 2 < N && y + 1 < M) {
			// 기본
			sum = map[x][y] + map[x + 1][y] + map[x + 2][y] + map[x + 2][y + 1];
			max = Math.max(max, sum);
			
			// 기본 오른쪽 180도 회전
			sum = map[x][y] + map[x][y + 1] + map[x + 1][y + 1] + map[x + 2][y + 1];
			max = Math.max(max, sum);
			
			// 좌우반전
			sum = map[x][y + 1] + map[x + 1][y + 1] + map[x + 2][y + 1] + map[x + 2][y];
			max = Math.max(max, sum);
			
			sum = map[x][y] + map[x][y + 1] + map[x + 1][y] + map[x + 2][y];
			max = Math.max(max, sum);
		}
		
		if (x + 1 < N && y + 2 < M) {
			// 기본 오른쪽 90도 회전
			sum = map[x][y] + map[x + 1][y] + map[x][y + 1] + map[x][y + 2];
			max = Math.max(max, sum);
			
			// 기본 오른쪽 270도 회전
			sum = map[x + 1][y] + map[x + 1][y + 1] + map[x + 1][y + 2] + map[x][y + 2];
			max = Math.max(max, sum);
			
			// 좌우반전
			sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 2];
			max = Math.max(max, sum);
			
			sum = map[x][y] + map[x + 1][y] + map[x + 1][y + 1] + map[x + 1][y + 2];
			max = Math.max(max, sum);
		}
	}

	private static void oh(int x, int y) {
		int sum = 0;
		
		// 기본, 기본 180도 회전
		if (x + 1 < N && y + 2 < M) {
			sum = map[x][y] + map[x][y + 1] + map[x][y + 2] + map[x + 1][y + 1];
			max = Math.max(max, sum);

			sum = map[x + 1][y] + map[x + 1][y + 1] + map[x + 1][y + 2] + map[x][y + 1];
			max = Math.max(max, sum);
		}
		
		// 기본 90도, 270도
		if (x + 2 < N && y + 1 < M) {
			sum = map[x + 1][y] + map[x][y + 1] + map[x + 1][y + 1] + map[x + 2][y + 1];
			max = Math.max(max, sum);

			sum = map[x][y] + map[x + 1][y] + map[x + 1][y + 1] + map[x + 2][y];
			max = Math.max(max, sum);
		}
	}
}

// N*M
// 테트로미노 5가지 종류를 놓아서 얻을 수 있는 가장 큰 수 구하기
// 1. ㅡ : 2가지
// 2. ㅁ : 1가지
// 3. ㄴ : 4가지
// 4. ㄹ : 2가지
// 5. ㅗ : 4가지
















