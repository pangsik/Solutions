package ssw;

import java.io.*;
import java.util.*;

public class d3_9229_한빈이와SpotMart {
	static int N, M, maxSum;
	static int[] snacks;
	
	static void comb(int cnt, int start, int sum) {
		if (cnt == 2) {
			if (sum <= M && maxSum < sum) 
					maxSum = sum;
			return;
		}
		
		for (int i = start; i < N; i++) {
			sum += snacks[i];
			comb(cnt + 1, i + 1, sum);
			sum -= snacks[i];
		}
	}
	
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input_d3_9229.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 과자 봉지 수
			M = Integer.parseInt(st.nextToken()); // 무게 합 제한
			maxSum = 0;

			snacks = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			comb(0, 0, 0);
			if (maxSum == 0)
				maxSum = -1;
			System.out.println("#" + tc + " " + maxSum);
		}

		br.close();
	}
}