package bj.silver;

import java.io.*;
import java.util.*;

public class bj_14889_스타트와링크 {
	static int N, min = Integer.MAX_VALUE;
	static int[][] power;
	static int[] numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		power = new int[N][N];
		numbers = new int[N / 2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		System.out.println(min);

		br.close();
	}

	static void comb(int start, int cnt) {
		if (cnt == N / 2) {
			int startSum = 0, linkSum = 0;
			
			int[] numbers2 = new int[N / 2];
			int tmp = 0;
			int tmp2 = 0;
			for (int i = 0; i < N; i++) {
				if (tmp < N / 2 && numbers[tmp] == i) {
					tmp++;
					continue;
				}
				numbers2[tmp2++] = i;
			}
			
			for (int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					startSum += power[numbers[i]][numbers[j]];
					startSum += power[numbers[j]][numbers[i]];
				}
			}
			
			for (int i = 0; i < N / 2; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					linkSum += power[numbers2[i]][numbers2[j]];
					linkSum += power[numbers2[j]][numbers2[i]];
				}
			}
			
			min = min < Math.abs(startSum - linkSum) ? min : Math.abs(startSum - linkSum);
			
			return;
		}

		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			comb(i + 1, cnt + 1);
		}
	}
}

// N 짝수
// N / 2  스타트팀, 링크팀
// nCn/2 구해서 