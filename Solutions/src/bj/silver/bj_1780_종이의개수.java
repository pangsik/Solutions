package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1780_종이의개수 {
	static int[] cnt = new int[3];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(map, 0, 0, N);
		System.out.println(cnt[0]);
		System.out.println(cnt[1]);
		System.out.println(cnt[2]);
		
		br.close();
	}
	
	static void dfs(int[][] map, int x, int y, int size) {
		if (isAvailable(map, x, y, size)) {
			return;
		}
		
		size /= 3;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				dfs(map, x + size * i, y + size * j, size);
			}
		}
	}
	
	static boolean isAvailable(int[][] map, int x, int y, int size) {
		int val = map[x][y];
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[x][y] != map[i][j])
					return false;
			}
		}
		val = val == -1 ? cnt[0]++ : val == 0 ? cnt[1]++ : cnt[2]++;
		return true;
	}
}

// N * N 종이
// 각 칸에는 -1, 0, 1
// 종이가 모두 같으면 그대로 사용
// 아니면 9등분해서 다시 검사