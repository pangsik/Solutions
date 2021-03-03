package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2447_별찍기10 {
	static char[][] star;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		star = new char[N][N];

		stars(N, 0, 0, false);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(star[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);

		br.close();
	}

	static void stars(int N, int x, int y, boolean check) {
		// 공백인 경우
		if (check) {
			for (int i = x; i < x + N; i++) {
				for (int j = y; j < y + N; j++) {
					star[i][j] = ' ';
				}
			}
			return;
		}
		
		if (N == 1) {
			star[x][y] = '*';
			return;
		}
		
		int nextSize = N / 3;
		int cnt = 0;
		for (int i = x; i < x + N; i += nextSize) {
			for (int j = y; j < y + N; j += nextSize) {
				cnt++;
				if (cnt == 5) {
					stars(nextSize, i, j, true);
				}
				else {
					stars(nextSize, i, j, false);
				}
			}
		}
	}
}
