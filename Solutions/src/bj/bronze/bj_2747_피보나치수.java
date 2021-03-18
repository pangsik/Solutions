package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2747_피보나치수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int n1 = 0, n2 = 0;
		int tmp1, tmp2;
		
		for (int i = 0; i < N; i++) {
			if (i == 0 || i == 1) {
				n1 = n2 = 1;
			}
			else {
				tmp1 = n1;
				tmp2 = n2;
				n1 = tmp2;
				n2 = tmp1 + tmp2;
			}
		}
		
		System.out.println(n2);

		br.close();
	}

//	static int fibo(int n) {
//		if (n == 0 || n == 1) {
//			return n;
//		}
//		return fibo(n - 1) + fibo(n - 2);
//	}
}

// 