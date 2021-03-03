package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2476_주사위게임 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int maxSum = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int sum = 0;
			
			if (a == b && b == c) {
				sum += 10000 + (a * 1000);
			}
			
			else if (a == b || a == c) {
				sum += 1000 + (a * 100);
			}
			
			else if (b == c) {
				sum += 1000 + (b * 100);
			}
			
			else {
				int max = a;
				max = max > b ? max : b;
				max = max > c ? max : c;
				sum += max * 100;
			}
			
			maxSum = maxSum > sum ? maxSum : sum;
		}
		
		System.out.println(maxSum);
		
		br.close();
	}
}

// 