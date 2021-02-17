package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1010_다리놓기 {
	static int[][] dp = new int[30][30];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			System.out.println(comb(M, N));
		}
		
		br.close();
	}
	
	static int comb(int n, int r) {
		// 계산 이력이 있으면 그거 반환
		if (dp[n][r] > 0) {
			return dp[n][r];
		}
		
		// nCn == 1, nC0 == 1
		if (n == r || r == 0) {
			return dp[n][r] = 1;
		}
		
		// nCr = n-1Cr-1 + n-1Cr 하고 저장해줌
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}
}

