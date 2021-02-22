package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2163_초콜릿자르기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int sum = 0;

		sum += N - 1;
		sum += (M - 1) * N;

		System.out.println(sum);

		br.close();
	}
}
