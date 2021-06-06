package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2846_오르막길 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(br.readLine());
		int prev = 1000;
		int max = 0;
		int sum = 0;
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			// 오르막길
			if (prev < num) {
				sum += num - prev;
			} 
			
			// 
			else {
				max = Integer.max(max, sum);
				sum = 0;
			}
				
			prev = num;
		}
		
		System.out.println(Integer.max(max, sum));
		
		br.close();
	}
}

// 
//8
//12 3 5 7 10 6 1 11