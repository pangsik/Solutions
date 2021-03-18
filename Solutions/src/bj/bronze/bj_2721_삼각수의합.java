package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2721_삼각수의합 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int sum = 0;
			int n = Integer.parseInt(br.readLine());
			for (int k = 1; k <= n; k++) {
				sum += k * triangle(k + 1);
			}
			System.out.println(sum);
		}
		
		br.close();
	}
	
	static int triangle(int k) {
		return k * (k + 1) / 2;
	}
}

// 삼각수란?.. 1부터 시작하는 연속된 자연수의 합
// ex) n = 3
// => 1 + 2 + 3
// n * (n + 1) / 2 공식이 있다~