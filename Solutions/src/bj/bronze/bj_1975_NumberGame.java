package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1975_NumberGame {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int cnt = 0;

			int number = Integer.parseInt(br.readLine());
			int n = 2;

			while (number >= n) {
				int num = number;

				while (num >= n) {
					if (num % n == 0) {
						num = num / n;
						cnt++;
					}

					else
						break;
				}
				n++;
			}
			System.out.println(cnt);
		}
		br.close();
	}
}

// 0나온다? cnt++ 1나온다? 스탑
