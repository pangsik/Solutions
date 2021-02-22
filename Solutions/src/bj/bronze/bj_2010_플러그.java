package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2010_플러그 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int sum = 1;
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			sum += Integer.parseInt(br.readLine());
		}
		
		System.out.println(sum - N);
		
		br.close();
	}
}

// 플러그 하나
// 멀티탭 N개