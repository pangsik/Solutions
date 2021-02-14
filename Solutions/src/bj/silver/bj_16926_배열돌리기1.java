package bj.silver;

import java.io.*;
import java.util.*;

public class bj_16926_배열돌리기1 {
	static int[] di = { 1, 0, -1, 0 }; // 하 우 상 좌
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int t = 0; t < R; t++) {
			int istart = 0;
			int iend = N;
			int jstart = 0;
			int jend = M;

			int d = 0; // 0 하 1 우 2 상 3 좌
			int i = 0, j = 0;
			int tmp1 = arr[0][0];
			int tmp2 = 0;
			int cnt = 0;
			
			while (cnt < N * M) {

				int ni = i + di[d];
				int nj = j + dj[d];

				if (ni >= istart && ni < iend && nj >= jstart && nj < jend) {
					tmp2 = arr[ni][nj];
					arr[ni][nj] = tmp1;
					tmp1 = tmp2;
					i = ni;
					j = nj;
					cnt++;
				}

				else {
					if (++d > 3) {
						d = 0;
						if (istart + 1 < N / 2) {
							i = ++istart;
							iend--;
							tmp1 = arr[i][j];
						}
						if (jstart + 1 < M / 2) {
							j = ++jstart;
							jend--;
							tmp1 = arr[i][j];
						}
					}
				}
			}
		}
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				System.out.print(arr[i][j] + " ");
//			}
//			System.out.println();
//		}
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);

		br.close();
	}
}
