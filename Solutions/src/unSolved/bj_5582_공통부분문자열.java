package unSolved;

import java.io.*;
import java.util.*;

public class bj_5582_공통부분문자열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String str1 = br.readLine(); // short one
		String str2 = br.readLine(); // long one
		if (str1.length() > str2.length()) {
			String tmp = str1;
			str1 = str2;
			str2 = tmp;
		}
		
		System.out.println(calc(str1, str2));
	}

	private static int calc(String str1, String str2) {
		int i, j, max = 0;
		for (i = j = 0; i < str1.length() && j < str1.length();) {
			String tmp = str1.substring(i, j + 1);

			if (str2.contains(tmp)) {
				j++;
				if (tmp.length() > max)
					max = tmp.length();
			} else if (i == j)
				j++;
			else
				i++;
		}
		
		return max;
	}
}

//public class bj_5582_공통부분문자열 {
//	public static void main(String[] args) throws Exception {
//		Scanner sc = new Scanner(System.in);
//		
//		char[] A = sc.next().toCharArray();
//		char[] B = sc.next().toCharArray();
//		int[][] LCS = new int[A.length + 1][B.length + 1];
//		
//		int answer = Integer.MIN_VALUE;
//		for (int i = 1; i <= A.length; i++) {
//			for (int j = 1; j <= B.length; j++) {
//				if(A[i-1] == B[j-1])
//					LSC[i][j] = LSC[i-1][j-1] + 1;
//			}
//			answer = Math.max(answer, LCS[i][j]);
//		}
//		
//		System.out.println(answer);
//	}
//}