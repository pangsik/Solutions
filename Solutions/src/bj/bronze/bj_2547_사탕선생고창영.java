package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2547_사탕선생고창영 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		br.readLine();

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			long nmg = 0;

			for (int i = 0; i < N; i++) {
				nmg += Long.parseLong(br.readLine()) % N;
			}

			if (nmg % N == 0)
				System.out.println("YES");
			else
				System.out.println("NO");

			br.readLine();

		}

		br.close();
	}
}

// 학생마다 사탕 엄청 많이 가져올 수 있음
// 다 더해서 학생 수로 나누어떨어지면 나눠주기 가능
// 근데 다 더하면 아마 범위 나갈듯
// 매 경우마다 

// (300 + 400 + 2304 + 36) % 5
// == ((300 % 5) + (400 % 5) + (2304 % 5) + (36 % 5)) % 5