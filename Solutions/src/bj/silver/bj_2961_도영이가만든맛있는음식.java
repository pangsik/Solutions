package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2961_도영이가만든맛있는음식 {
	static int[][] taste;
	static boolean[] isSelected;
	static int N, min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		taste = new int[N][2];
		isSelected = new boolean[N];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 2; j++) {
				taste[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		subset(0);

		System.out.println(min);

		br.close();
	}

	static void subset(int cnt) {
		if (cnt == N) {
			
			// 공집합 무시
			boolean check = false;
			for (int i = 0; i < N; i++) {
				if (isSelected[i])
					check = true;
			}
			if (!check) return;
			
			// 신맛 쓴맛 계산
			int sin = 1;
			int ssn = 0;
			
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					sin *= taste[i][0];
					ssn += taste[i][1];
				}
			}
			if (min > Math.abs(sin - ssn))
				min = Math.abs(sin - ssn);
			return;
		}

		isSelected[cnt] = true;
		subset(cnt + 1);

		isSelected[cnt] = false;
		subset(cnt + 1);
	}
}
