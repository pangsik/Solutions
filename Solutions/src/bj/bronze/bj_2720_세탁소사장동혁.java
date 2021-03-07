package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2720_세탁소사장동혁 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb = new StringBuilder();
			int C = Integer.parseInt(br.readLine());
			sb.append(C / 25).append(" ");
			C %= 25;
			sb.append(C / 10).append(" ");
			C %= 10;
			sb.append(C / 5).append(" ");
			C %= 5;
			sb.append(C / 1);
			
			System.out.println(sb);
		}
		
		br.close();
	}
}

// 쿼터 : 25
// 다임 : 10
// 니켈 : 5
// 페니 : 1