package bj.bronze;

import java.util.*;
import java.io.*;

public class bj_13458_시험감독_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long answer = N;
		
		for (int i = 0; i < N; i++) {
			int tmp = A[i] - B;
			if (tmp < 0)
				tmp = 0;
			
			int cnt = tmp / C;
			if (tmp % C != 0) {
				cnt++;
			}
			answer += cnt;
		}
		
		System.out.println(answer);
		
		br.close();
	}
}

// 시험장 수 N
// i번 시험장의 응시자 수 Ai
// 총감독관이 감시 : B명
// 부감독관이 감시 : C명
// 총감독 1명, 부감독 여러명
