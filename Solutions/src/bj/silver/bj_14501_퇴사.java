package bj.silver;

import java.io.*;
import java.util.*;

public class bj_14501_퇴사 {
	static int N, max = 0;
	static int[] T, P;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		T = new int[N];
		P = new int[N];
		max = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			test(i, 0);
		}
		
		System.out.println(max);

		br.close();
	}

	static void test(int idx, int sum) {
		// 다음날로 갈 수 없을 때.. 현재까지 이익 합과 최댓값 비교 후 종료
		if (idx + T[idx] > N) {
			max = max > sum ? max : sum;
			return;
		}
		
		// 다음날 갈 수 있을 때..
		sum += P[idx];
		idx += T[idx];
		
		// 바로 다음날, 그 다음날, 그 다다음날 가는 경우 다 고려
		for (int i = idx; i < N; i++) {
			test(i, sum);
		}
		
		// 끝까지 다 왔을 때
		if (idx == N) {
			max = max > sum ? max : sum;
			return;
		}
	}
}
