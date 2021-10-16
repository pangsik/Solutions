package swea;


import java.io.*;
import java.util.*;

public class d2_1954_달팽이숫자 {
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] snail = new int[N][N];
			int cnt = N;
			int val = 1;
			int x = 0, y = -1;
			int d = 1;

			while (cnt >= 0) {
				for (int i = 0; i < cnt; i++) {
					y += d;
					snail[x][y] = val++;
				}
				cnt--;
				for (int i = 0; i < cnt; i++) {
					x += d;
					snail[x][y] = val++;
				}
				d *= -1;
			}
			System.out.println("#" + tc);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++)
					System.out.print(snail[i][j] + " ");
				System.out.println();
			}
		}
	}
}
