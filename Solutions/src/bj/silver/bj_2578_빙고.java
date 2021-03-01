package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2578_빙고 {
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_d0_1767.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int[][] map = new int[5][5];

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		int answer = 0;

		for (int i = 1; i <= 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 5; j++) {
				int call = Integer.parseInt(st.nextToken());
				if (check(map, erase(map, call))) {
					for (int l = 0; l < 5; l++) {
						System.out.println(Arrays.toString(map[l]));
					}
					System.out.println();
					cnt++;
				}
				if (cnt == 3) {
					answer = i * j;
				}
			}
		}

		System.out.println(answer);

		br.close();
	}

	static int[] erase(int[][] map, int num) {
		int[] xy = { -1, -1 };
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == num) {
					map[i][j] = 0;
					xy[0] = i;
					xy[1] = j;
					return xy;
				}
			}
		}
		return xy;
	}

	static boolean check(int[][] map, int[] xy) {
		boolean flag1 = true;
		boolean flag2 = true;
		boolean flag3 = true;
		for (int i = 0; i < 5; i++) {
			if (map[i][xy[1]] != 0)
				flag1 = false;
		}

		for (int j = 0; j < 5; j++) {
			if (map[xy[0]][j] != 0)
				flag2 = false;
		}
		
		

		return flag1 || flag2 || flag3;
	}
}