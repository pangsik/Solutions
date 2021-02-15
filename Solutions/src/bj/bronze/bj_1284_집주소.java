package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1284_집주소 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		String N = br.readLine();
		while (!N.equals("0")) {
			int sum = 2 + N.length() - 1;
			for (int i = 0; i < N.length(); i++) {
				if (N.charAt(i) == '0')
					sum += 4;
				else if (N.charAt(i) == '1')
					sum += 2;
				else
					sum += 3;
			}
			System.out.println(sum);
			
			N = br.readLine();
		}
		
		br.close();
	}
}

// 0 : 4cm
// 1 : 2cm
// 나머지 : 3cm
// 숫자사이, 양끝 : 1cm