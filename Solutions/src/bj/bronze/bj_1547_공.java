package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1547_공 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());
		ArrayList<Integer> cups = new ArrayList<>();
		cups.add(1);
		cups.add(2);
		cups.add(3);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = cups.indexOf(Integer.parseInt(st.nextToken()));
			int b = cups.indexOf(Integer.parseInt(st.nextToken()));
			
			int tmp = cups.get(a);
			cups.set(a, cups.get(b));
			cups.set(b, tmp);
		}
		
		System.out.println(cups.get(0));

		br.close();
	}
}

// 공 위치는 1번으로 고정
// 컵을 들어서 바꾸는거.. 야바위 아님