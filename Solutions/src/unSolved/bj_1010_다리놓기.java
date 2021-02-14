package unSolved;

import java.io.*;
import java.util.*;

public class bj_1010_다리놓기 {
	static int N, M, answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//comb(0, 0);
			
			// mCn 개수만 구하기
			
		}
		
		br.close();
	}
	
	// mCn
	static void comb(int cnt, int start) {
		if (cnt == N) {
			answer++;
			return;
		}
		
		for (int i = start; i < M; i++) {
			comb(cnt + 1, i + 1);
		}
	}
}
