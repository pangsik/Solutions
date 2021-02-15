package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_3040_백설공주와일곱난쟁이 {
	static int N = 9;
	static int R = 7;
	static int[] input = new int[9];
	static int[] numbers = new int[7];
	static int[] answers = new int[7];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 9; i++) 
			input[i] = Integer.parseInt(br.readLine());
		
		comb(0, 0);
		
		for (int i = 0; i < R; i++) 
			sb.append(answers[i]).append("\n");
		
		System.out.println(sb);
		
		br.close();
	}
	
	static void comb(int cnt, int start) {
		if (cnt == R) {
			int sum = 0;
			for (int i = 0; i < R; i++) 
				sum += numbers[i];
			if (sum == 100) {
				for (int i = 0; i < R; i++)
					answers[i] = numbers[i];
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			numbers[cnt] = input[i];
			comb(cnt + 1, i + 1);
		}
	}
}

// 