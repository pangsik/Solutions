package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1864_문어숫자 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		while (true) {
			String octo = br.readLine();
			if (octo.equals("#"))
				break;

			int multi = octo.length() - 1;
			int tmp = 0;
			int sum = 0;

			for (int i = 0; i < octo.length(); i++) {
				switch (octo.charAt(i)) {
				case '-':
					tmp = 0;
					break;

				case '\\':
					tmp = 1;
					break;

				case '(':
					tmp = 2;
					break;

				case '@':
					tmp = 3;
					break;

				case '?':
					tmp = 4;
					break;

				case '>':
					tmp = 5;
					break;

				case '&':
					tmp = 6;
					break;

				case '%':
					tmp = 7;
					break;

				case '/':
					tmp = -1;
					break;
				}

				sum += tmp * Math.pow(8, multi--);
			}

			System.out.println(sum);
		}

		br.close();
	}
}

// - : 0
// \ : 1
// ( : 2
// @ : 3
// ? : 4
// > : 5
// & : 6
// % : 7
// / : -1

// (@&
// 2 3 6 = 2 * 8^2 + 3 * 8 + 6