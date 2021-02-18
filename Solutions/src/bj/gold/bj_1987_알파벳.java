package bj.gold;
import java.io.*;
import java.util.*;

public class bj_1987_알파벳 {
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static char[][] map;
	static int R, C, maxScore = 0;
	static boolean[] visited = new boolean[26]; // 0 ~ 25

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

		visited[map[0][0] - 'A'] = true;
		move(1, 0, 0);

		System.out.println(maxScore);

		br.close();
	}

	static boolean move(int cnt, int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nx = x + di[i];
			int ny = y + dj[i];

			// 맵 밖으로 나가는 경우
			if (!(nx >= 0 && nx < R && ny >= 0 && ny < C))
				continue;

			// 알파벳 중복
			if (visited[map[nx][ny] - 'A'])
				continue;

			visited[map[nx][ny] - 'A'] = true;
			if (move(cnt + 1, nx, ny))
				return true;
		}

		visited[map[x][y] - 'A'] = false;
		maxScore = cnt > maxScore ? cnt : maxScore;
		return false;
	}
}

// 상하좌우 이동
// 여태 나온 알파벳과 다른 알파벳이 있는 칸으로 이동 가능..
