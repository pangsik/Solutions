package bj.silver;

import java.io.*;
import java.util.*;

public class bj_5904_Moo게임 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt((br.readLine()));
		
		System.out.println(get(N));
		
		br.close();
	}
	
	static StringBuilder get(int cnt) {
		StringBuilder sb = new StringBuilder();
		
		if (cnt == -1) {
			return sb;
		}
		
		sb.append(get(cnt-1));
		sb.append("m ");
		for (int i = 0; i < cnt; i++) {
			sb.append("o ");
		}
		sb.append("o ").append("o ");
		sb.append(get(cnt-1));
		
		return sb;
	}
}

// S(1) = S(0) + (m + o * k+2) + S(0)
// S(0) = "m o o"
// S(1) = "m o o m o o o m o o"
// S(2) = "m o o m o o o m o o m o o o o m o o m o o o m o o"
// S(3) = "m o o m o o o m o o m o o o o m o o m o o o m o o m o o o o o m o o m o o o m o o m o o o o m o o m o o o m o o"
// 0 3 7 10 15 18 22 25 31 34 38 41 46...
//  3 4 3  5  3  4  3  6  3  4  3  5