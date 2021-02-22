package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2061_좋은암호 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		String P = st.nextToken(); // P 범위가 10^100 까지..
		int K = Integer.parseInt(st.nextToken());

		// 소수 false, 나머지 true
		boolean[] prime = eratos(K);

		for (int i = 0; i < prime.length; i++) {
			if (prime[i])
				continue;
			if (divCheck(P, i)) {
				sb.append("BAD ").append(i);
				break;
			}
		}

		if (sb.length() == 0)
			sb.append("GOOD");

		System.out.println(sb);
		br.close();
	}

	// 에라토스테네스의 체
	// https://ko.wikipedia.org/wiki/%EC%97%90%EB%9D%BC%ED%86%A0%EC%8A%A4%ED%85%8C%EB%84%A4%EC%8A%A4%EC%9D%98_%EC%B2%B4
	static boolean[] eratos(int K) {
		boolean[] prime = new boolean[K]; // 소수 false
		prime[0] = prime[1] = true;

		// 2부터 숫자를 키워가며 배수들을 제외 (true 할당)
		for (int i = 2; i * i <= K; i++) {
			if (!prime[i]) {
				for (int j = i * i; j < K; j += i) {
					prime[j] = true;
				}
			}
		}

		return prime;
	}

	// https://www.acmicpc.net/board/view/54146
	static boolean divCheck(String P, int n) {
		char[] p = P.toCharArray();
		int remain = 0;

		for (int i = 0; i < p.length; i++)
			remain = (remain * 10 + (p[i] - '0')) % n;
		return remain == 0;
	}

}
