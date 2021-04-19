package ssw;

import java.util.*;
import java.io.*;

public class d3_5607_조합 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		final int div = 1234567891;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); 
			int R = Integer.parseInt(st.nextToken()); 
			
			long answer = nCr(N, R, div);
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static long nCr(int n, int r, int p) {
		if (r == 0) {
			return 1L;
		}
		
		long[] fac = new long[n + 1];
		fac[0] = 1;
		
		for (int i = 1; i <= n; i++) {
			fac[i] = (fac[i - 1] * i) % p; 
		}
		
		return (fac[n] * power(fac[r], p - 2, p) % p * power(fac[n - r], p - 2, p) % p) % p;
	}
	
	static long power(long x, long y, long p) {
		long res = 1L;
		x = x % p;
		
		while (y > 0) {
			if (y % 2 == 1) {
				res = (res * x) % p;
			}
			y = y >> 1;
			x = (x * x) % p;
		}
		
		return res;
	}
}
