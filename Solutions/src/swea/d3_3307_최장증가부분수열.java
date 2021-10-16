package swea;

import java.io.*;
import java.util.*;

public class d3_3307_최장증가부분수열 {
	static int N;
	static int[] arr, LIS;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			sb.append('#').append(tc).append(' ').append(LIS()).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static int LIS() {
		int max = 0;
		LIS = new int[N];
		
		for (int i = 0; i < N; i++) {
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			if (max < LIS[i]) max = LIS[i];
		}
		
		return max;
	}
}
