package bj.silver;

import java.io.*;
import java.util.*;

public class bj_11727_2xn타일링2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		
		dp[1] = 1;
		if (n > 1) dp[2] = 3;
		
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
		}
		
		System.out.println(dp[n]);
		
		br.close();
	}
}

