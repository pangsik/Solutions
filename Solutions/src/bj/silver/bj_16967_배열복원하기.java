package bj.silver;

import java.io.*;
import java.util.*;

public class bj_16967_배열복원하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int[][] B = new int[H + X][W + Y];
		int[][] A = new int[H][W];

		for (int i = 0; i < H + X; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W + Y; j++) {
				B[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				// 둘 다 겹치는 경우
				if (i >= X && i < H && j >= Y && j < W) {
					// 겹친 부분에서 이동 후 그 자리 A값 빼줌
					A[i][j] += B[i][j] - A[i - X][j - Y];
				}
				
				// 이동 전 배열만 겹치는 경우
				else if (i < H && j < W) {
					A[i][j] += B[i][j];
				}
			}
		}
		
		for(int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++)
				System.out.print(A[i][j] + " ");
			System.out.println();
		}

		br.close();
	}
}

// (i, j)가 두 배열 모두에 포함되지 않으면, Bi,j = 0이다.
// (i, j)가 두 배열 모두에 포함되면, Bi,j = Ai,j + Ai-X,j-Y이다.
// (i, j)가 두 배열 중 하나에 포함되면, Bi,j = Ai,j 또는 Ai-X,j-Y이다.