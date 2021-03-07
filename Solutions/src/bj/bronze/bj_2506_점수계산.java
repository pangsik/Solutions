package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2506_점수계산 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");

		int score = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if (Integer.parseInt(st.nextToken()) == 1)
				sum += ++score;
			else
				score = 0;
		}

		System.out.println(sum);

		br.close();
	}
}

// 