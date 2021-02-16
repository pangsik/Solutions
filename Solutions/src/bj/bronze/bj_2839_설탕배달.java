package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2839_설탕배달 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int answer = 0;
		
		// 3kg, 5kg 봉지에 나눠 담음
		// 정확히 Nkg 맞춰야 함
		
		// 5의 배수가 될 때까지 N을 3씩 뺀다! 빼면서 봉지수 1씩 추가!! 5의 배수가 되면 바로 나눈 몫만큼 봉지수 추가
		while (N > 0) {
			if (N % 5 == 0) {
				answer += N / 5;
				N = 0;
				break;
			}
			N -= 3;
			answer++;
		}
		
		// 딱 안떨어지면 -1
		if (N != 0) {
			System.out.println(-1);
		}
		else
			System.out.println(answer);
		
		br.close();
	}
}
