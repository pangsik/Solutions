package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1267_핸드폰요금 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int young = 0;
		int min = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int useTime = Integer.parseInt(st.nextToken());
			young += ((useTime / 30) + 1) * 10;
			min += ((useTime / 60) + 1) * 15;
		}
		
		if (young > min) 
			System.out.println("M " + min);
		else if (young < min) 
			System.out.println("Y " + young);
		else 
			System.out.println("Y M " + min);
		
		
		br.close();
	}
}

// 