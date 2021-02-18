package bj.gold;

import java.io.*;
import java.util.*;

public class bj_3109_빵집 {
	static int R, C, ans = 0;
	static char[][] map;
	static int[] di = { -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

//		for (int i = 0; i < R; i++)
//			System.out.println(Arrays.toString(map[i]));

		for (int i = 0; i < R; i++) {
			dfs(i, 0);
		}
		System.out.println(ans);

		br.close();
	}

	static boolean dfs(int x, int y) {
		// 끝에 도착
		if (y == C - 1) {
			ans++;
			return true;
		}

		// 탐색
		for (int i = 0; i < 3; i++) {
			int nx = x + di[i];
			int ny = y + 1;

			// 맵 밖으로 나갔을 때
			if (!(nx < R && nx >= 0 && ny < C && ny >= 0)) {
				continue;
			}

			// 건물이나 파이프 있을 때
			else if (map[nx][ny] == 'x') {
				continue;
			}

			// 이동 가능
			map[nx][ny] = 'x';
			if (dfs(nx, ny)) return true;
		}
		
		// 이동 불가능
		return false;
	}
}

// 이동 불가능한 경우 : 맵 밖으로 나갔을 때, 건물 파이프 있을 때..
// 이동 가능한 경우 true를 리턴, 불가능하면 false
// dfs 재귀를 사용할 때 아예 if(dfs)를 하여 더 이동이 불가능하면 아예 가지 않게 한다.
