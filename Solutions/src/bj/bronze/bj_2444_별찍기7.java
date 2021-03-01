package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2444_별찍기7 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < 2 * N - 1; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				if (N - j <= i + 1 && N - j > i * -1 && i - j < N && 2 * N - 1 - j > i - N + 1)
					System.out.print("*");
				else if (j < N)
					System.out.print(" ");
			}
			System.out.println();
		}

		br.close();
	}
}
