package bj.gold;

import java.util.*;
import java.io.*;

public class bj_1759_암호만들기 {
	static int L, C;
	static char[] items, pw;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		items = new char[C];
		pw = new char[L];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			items[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(items);
		
		comb(0, 0);
		
		br.close();
	}
	
	static void comb(int start, int cnt) {
		if (cnt == L) {
			StringBuilder sb = new StringBuilder();
			int m = 0, j = 0;
			for (int i = 0; i < L; i++) {
				if (pw[i] == 'a' || pw[i] == 'e' || pw[i] == 'i' || pw[i] == 'o' || pw[i] == 'u')
					m++;
				else
					j++;
				sb.append(pw[i]);
			}
			if (m >= 1 && j >= 2)
				System.out.println(sb);
			return;
		}
		
		for (int i = start; i < C; i++) {
			pw[cnt] = items[i];
			comb(i + 1, cnt + 1);
		}
	}
}

// 서로 다른 L개의 알파벳 소문자로 구성
// 최소 한 개의 모음 (a, e, i, o, u)
// 최소 두 개의 자음
// 암호는 정렬되어 있음 (bac 땡! abc 오케)
// C가지의 문자가 주어졌을 때 이것들로 가능한 모든 문자열 구하라