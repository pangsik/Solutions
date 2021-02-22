package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1946_신입사원 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] apply = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				apply[i][0] = Integer.parseInt(st.nextToken());
				apply[i][1] = Integer.parseInt(st.nextToken());
			}
			
			// 입력 아래처럼 받으면 입력과 동시에 서류 오름차순 정리가 된다.. 정렬시간 확 줄여줌
//			int[] app = new int[N + 1];
//
//			for (int i = 1; i <= N; ++i) {
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				int dr = Integer.parseInt(st.nextToken()); // 서류 심사 순위
//				int ir = Integer.parseInt(st.nextToken()); // 면접 순위
//				app[dr] = ir;
//			}

			Arrays.sort(apply, (o1, o2) -> Integer.compare(o1[0], o2[0]));
			int min = apply[0][1];
			int cnt = 1;

			for (int i = 1; i < N; i++) {
				// System.out.println(Arrays.toString(apply[i]));
				if (min > apply[i][1]) {
					cnt++;
					min = apply[i][1];
				}
			}
			System.out.println(cnt);
		}

		br.close();
	}
}
