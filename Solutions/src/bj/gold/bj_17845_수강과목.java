package bj.gold;

import java.util.*;
import java.io.*;

public class bj_17845_수강과목 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[K + 1][N + 1];
		
		int[] v = new int[K + 1];
		int[] wi = new int[K + 1];
		
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			v[i] = Integer.parseInt(st.nextToken()); // 중요도
			wi[i] = Integer.parseInt(st.nextToken()); // 공부시간 (w)
		}
		
		for (int i = 1; i <= K; i++) {
			for (int w = 1; w <= N; w++) {
				if (wi[i] > w) {
					dp[i][w] = dp[i - 1][w];
				}
				else {
					dp[i][w] = Integer.max(dp[i - 1][w], v[i] + dp[i - 1][w - wi[i]]);
				}
			}
		}
		
		System.out.println(dp[K][N]);
		
		br.close();
	}
}
