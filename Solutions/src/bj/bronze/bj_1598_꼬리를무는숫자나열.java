package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1598_꼬리를무는숫자나열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int a = Integer.parseInt(st.nextToken()) - 1;
		int[] axy = { a / 4, a % 4 };

		int b = Integer.parseInt(st.nextToken()) - 1;
		int[] bxy = { b / 4, b % 4 };

		int dist = Math.abs(axy[0] - bxy[0]) + Math.abs(axy[1] - bxy[1]);

		System.out.println(dist);

		br.close();
	}
}

// (a - 1) / 4 : x좌표
// (a - 1) % 4 : y좌표
// (x1, y1), (x2, y2) 사이 거리 : |x1 - x2| + |y1 - y2|