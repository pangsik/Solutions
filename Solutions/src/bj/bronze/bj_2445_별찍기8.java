package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2445_별찍기8 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < 2 * N - 1; i++) {
			for (int j = 0; j < 2 * N; j++) {
				if (i < N) {
					if (i >= j || 2 * N - 1 - i <= j)
						System.out.print("*");

					else
						System.out.print(" ");
				}

				else if (i >= N) {
					if (2 * N - 1 - i > j || j > i)
						System.out.print("*");

					else
						System.out.print(" ");
				}
			}
			System.out.println();
		}

		br.close();
	}
}
