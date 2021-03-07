package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2566_최댓값 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int[] max = { 0, 0, 0 }; // 최댓값, x좌표, y좌표

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (max[0] < tmp) {
					max[0] = tmp;
					max[1] = i + 1;
					max[2] = j + 1;
				}
			}
		}
		
		System.out.println(max[0]);
		System.out.println(max[1] + " " + max[2]);

		br.close();
	}
}