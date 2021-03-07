package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2576_홀수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < 7; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num % 2 == 1) {
				sum += num;
				if (min > num) {
					min = num;
				}
			}
		}
		
		if (sum == 0) {
			System.out.println(-1);
		}
		
		else {
			System.out.println(sum);
			System.out.println(min);
		}
		
		br.close();
	}
}