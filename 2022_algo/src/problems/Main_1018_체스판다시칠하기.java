package problems;

import java.io.*;
import java.util.*;

public class Main_1018_체스판다시칠하기 {
	static int N, M, min;
	static boolean[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				if (input.charAt(j) == 'W')
					map[i][j] = true;
			}
		}
		
		min = Integer.MAX_VALUE;
		Solution();
		
		System.out.println(min);
		
		br.close();
	}
	
	private static void Solution() {
		for (int i = 0; i < N - 7; i++) {
			for (int j = 0; j < M - 7; j++) {
				selectBoard(i, j);
			}
		}
	}

	private static void selectBoard(int startX, int startY) {
		int endX = startX + 8;
		int endY = startY + 8;
		
		boolean TF = !map[startX][startY];
		int cnt1 = 0;
		int cnt2 = 0;
		
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				if (map[i][j] != TF) {
					cnt2++;
					TF = !TF;
				}
				
				else {
					cnt1++;
					TF = !TF;
				}
			}
			TF = !TF;
		}
		
		min = Math.min(min, cnt1 < cnt2 ? cnt1 : cnt2);
	}
}
