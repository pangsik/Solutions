package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2501_약수구하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int cnt = 0;
		int answer = 0;

		for (int i = 1; i <= N; i++) {
			if (N % i == 0)
				cnt++;
			if (cnt == K) {
				answer = i;
				break;
			}
		}

		System.out.println(answer);

		br.close();
	}
}

// 