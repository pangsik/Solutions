package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1149_RGB거리 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] rgb = new int[3];
		int r = 0, g = 0, b = 0;
		
		for (int i = 1; i <= N; i++) {
			int sumR = rgb[0];
			int sumG = rgb[1];
			int sumB = rgb[2];
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken()); // 빨강
			g = Integer.parseInt(st.nextToken()); // 초록
			b = Integer.parseInt(st.nextToken()); // 파랑
			rgb[0] = r + Math.min(sumG, sumB);
			rgb[1] = g + Math.min(sumR, sumB);
			rgb[2] = b + Math.min(sumR, sumG);
		}
		
		System.out.println(Math.min(rgb[0], Math.min(rgb[1], rgb[2])));
		br.close();
	}
}

// 1번 집의 색은 2번 집의 색과 달라야 한다.
// N번 집의 색은 N-1번 집의 색과 달라야 한다.
// i(2 <= i <= N-1)번 집의 색은 i-1번, i+1번 집의 색과 달라야 한다.