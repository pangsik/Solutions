package unSolved;

import java.io.*;
import java.util.*;

public class bj_1010_다리놓기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int R = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			//comb(0, 0);
			
			// mCn 개수만 구하기
			System.out.println(combination(N, R));
		}
		
		br.close();
	}
	
	// nCr = (n - r + 1) / r * nC(r-1)
	// nC0 = 1
	static int combination(int n, int r) {
		double sum = 1;
		
		// nC1 부터 nCr 까지 위 공식 그대로 적용해서 진행..
		for (int i = 1; i <= r; i++) 
			sum = sum * ((n - i + 1) / (double) i);
		
		return (int) sum;
	}
	
	// mCn
//	static void comb(int cnt, int start) {
//		if (cnt == N) {
//			answer++;
//			return;
//		}
//		
//		for (int i = start; i < M; i++) {
//			comb(cnt + 1, i + 1);
//		}
//	}
}
