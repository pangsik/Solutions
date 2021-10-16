package swea;

import java.io.*;
import java.util.*;

public class d3_5215_햄버거다이어트 {
	static int[] T;
	static int[] K;
	static boolean[] isSelected;
	static int N, L, maxT;

	private static void subSet(int cnt, int sumT, int sumK) {
		if (sumK > L) 
			return;
		
		maxT = maxT < sumT ? sumT : maxT;
		
		if (cnt == N)
			return;
		
		//isSelected[cnt] = true;
		subSet(cnt + 1, sumT + T[cnt], sumK + K[cnt]);
		//isSelected[cnt] = false;
		subSet(cnt + 1, sumT, sumK);
	}

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_5215.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			

			T = new int[N];
			K = new int[N];
			isSelected = new boolean[N];
			maxT = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				T[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}
			subSet(0, 0, 0);
			System.out.println("#" + tc + " " + maxT);
		}

		br.close();
	}
}