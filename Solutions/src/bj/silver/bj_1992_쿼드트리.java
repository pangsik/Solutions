package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1992_쿼드트리 {
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		System.out.println(zip(N, 0, 0));

		br.close();
	}

	static boolean isTrue(int size, int istart, int jstart) {
		int tmp = map[istart][jstart];
		for (int i = istart; i < istart + size; i++) {
			for (int j = jstart; j < jstart + size; j++) {
				if (map[i][j] != tmp)
					return false;
			}
		}
		return true;
	}

	static StringBuilder zip(int size, int istart, int jstart) {
		StringBuilder sb = new StringBuilder();
		
		if (isTrue(size, istart, jstart)) {
			if (map[istart][jstart] == 1)
				return sb.append("1");
			else
				return sb.append("0");
		}

		int half = size / 2;
		return sb.append("(").
				append(zip(half, istart, jstart)).
				append(zip(half, istart, jstart + half)).
				append(zip(half, istart + half, jstart)).
				append(zip(half, istart + half, jstart + half)).
				append(")");
	}
}
