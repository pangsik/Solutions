package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2460_지능형기차2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int sum = 0;
		int max = 0;
		
		for (int i = 0; i < 10; i++) {
			st = new StringTokenizer(br.readLine());
			sum -= Integer.parseInt(st.nextToken());
			max = max > sum ? max : sum;
			sum += Integer.parseInt(st.nextToken());
			max = max > sum ? max : sum;
		}
		
		System.out.println(max);
		
		br.close();
	}
}

// 