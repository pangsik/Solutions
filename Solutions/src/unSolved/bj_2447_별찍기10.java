package unSolved;

import java.io.*;
import java.util.*;

public class bj_2447_별찍기10 {
	static char[][] star;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		star = new char[N][N];

		stars(N, 0, 0);

		br.close();
	}

	static void stars(int N, int x, int y) {
		if (N == 3) {

		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				stars(N / 3, N / 3 * i, N / 3 * j);
			}
		}
	}
}
