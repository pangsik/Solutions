package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_13458_시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		long cnt = N; // 감독관 수 (기본 모든 시험장에 총감독관 1명씩 들어감)
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// *************************************
		
		for (int i = 0; i < N; i++) {
			A[i] -= B;
			if (A[i] <= 0)
				continue;
			if (A[i] % C > 0) {
				cnt += A[i] / C + 1;
			}
			else {
				cnt += A[i] / C;
			}
		}
		
		System.out.println(cnt);
		
		br.close();
	}
}

// N개 응시장
// i번 시험장 응시자 수 A[i]
// 총감독관 감시 가능 B명, 부감독관 감시 가능 C명
// 총감독관 오직 한 명, 부감독관 여러명