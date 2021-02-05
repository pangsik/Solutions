package ssw;

import java.io.*;
import java.util.*;

public class d3_1289_원재의메모리복구하기 {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_1289.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			//char 배열로 받아서 하나하나 탐색
			//이전 수, 현재 수 비교해서 다르면 cnt++
			char[] a = in.readLine().toCharArray();
			int cnt = 0;
			char prev = '0';
			for(int i = 0; i < a.length; i++) {
				if (a[i] != prev) {
					cnt++;
					prev = prev =='0' ? '1' : '0';
				}
			}
			System.out.println("#" + tc + " " + cnt);
		}
		in.close();
	}
}
