package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2739_구구단 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		for (int i = 1; i < 10; i++) {
			System.out.println(N + " * " + i + " = " + (N * i));
		}

		br.close();
	}
}

// 