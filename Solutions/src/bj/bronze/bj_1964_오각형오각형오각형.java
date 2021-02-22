package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1964_오각형오각형오각형 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		long N = Integer.parseInt(br.readLine());
		long cnt = (((3l * N * N) + (5l * N) + 2l) / 2l) % 45678;

		System.out.println(cnt);

		br.close();
	}
}

// N = 1 : 5
// N = 2 : 5 + 7 = 12
// N = 3 : 5 + 7 + 10 = 22

// 