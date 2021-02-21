package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1942_디지털시계 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 3; tc++) {
			String input = br.readLine();
			int startH = Integer.parseInt(input.substring(0, 2));
			int startM = Integer.parseInt(input.substring(3, 5));
			int startS = Integer.parseInt(input.substring(6, 8));
			int endH = Integer.parseInt(input.substring(9, 11));
			int endM = Integer.parseInt(input.substring(12, 14));
			int endS = Integer.parseInt(input.substring(15, 17));

			int result = 0;
			while (startH != endH || startM != endM || startS != endS) {
				// 3의 배수인지 확인하기
				if ((startH * 10000 + startM * 100 + startS) % 3 == 0) {
					result++;
				}
				// 1초 증가시키기
				startS++;
				if (startS == 60) {
					startM++;
					startS = 0;
				}
				if (startM == 60) {
					startH++;
					startM = 0;
				}
				if (startH == 24) {
					startH = 0;
				}
			}
			if ((startH * 10000 + startM * 100 + startS) % 3 == 0) {
				result++;
			}
			System.out.println(result);
			
			
//			st = new StringTokenizer(br.readLine(), " ");
//			String[] tmp1 = st.nextToken().split(":");
//			String[] tmp2 = st.nextToken().split(":");
//
//			int t1 = Integer.parseInt(tmp1[0] + tmp1[1] + tmp1[2]);
//			int t2 = Integer.parseInt(tmp2[0] + tmp2[1] + tmp2[2]);
//			int cnt = 0;
//			// 시0 분1 초2
//
//			while (t1 != t2) {
//				// 시 단위 증가
//				while (Integer.parseInt(tmp1[0]) < 24 && t1 != t2) {
//					// 분 단위 증가
//					while (Integer.parseInt(tmp1[1]) < 60 && t1 != t2) {
//						// 초 단위 증가
//						while (Integer.parseInt(tmp1[2]) < 60 && t1 != t2) {
//							t1 = Integer.parseInt(tmp1[0] + tmp1[1] + tmp1[2]);
//							if (t1 % 3 == 0)
//								cnt++;
//							// System.out.println(Arrays.toString(tmp1));
//							tmp1[2] = String.format("%02d", Integer.parseInt(tmp1[2]) + 1); // 초 증가 (두 자릿 수 유지)
//						}
//						tmp1[2] = "00"; // 60초 도달하면 0초로 초기화
//						tmp1[1] = String.format("%02d", Integer.parseInt(tmp1[1]) + 1); // 분 증가
//					}
//					tmp1[1] = "00"; // 60분 도달하면 0분으로 초기화
//					tmp1[0] = String.format("%02d", Integer.parseInt(tmp1[0]) + 1); // 시 증가
//				}
//				tmp1[0] = "00"; // 24시 도달하면 0시로 초기화
//			}
//			System.out.println(cnt);
			
		}

		br.close();
	}
}

// hh:mm:ss
// 0 <= hh <= 23
// 0 <= mm, ss <= 59