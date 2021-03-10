package bj.gold;

import java.util.*;
import java.io.*;

public class bj_14499_주사위굴리기 {
	static int[] di = { 0, 0, -1, 1 };
	static int[] dj = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		int[] dice = new int[6]; // 바닥 상 우 좌 하 하하

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			int d = Integer.parseInt(st.nextToken()) - 1;
			int nx = x + di[d];
			int ny = y + dj[d];

			// 범위 밖으로 나가려하면 스킵
			if (!(nx >= 0 && nx < N && ny >= 0 && ny < M)) {
				continue;
			}

			int[] ndice = new int[6];
			switch (d) {
			// 우
			case 0:
				ndice[0] = dice[2]; // 바닥
				ndice[1] = dice[1]; // 상
				ndice[2] = dice[5]; // 우
				ndice[3] = dice[0]; // 좌
				ndice[4] = dice[4]; // 하
				ndice[5] = dice[3]; // 하하
				break;
			// 좌
			case 1:
				ndice[0] = dice[3]; // 바닥
				ndice[1] = dice[1]; // 상
				ndice[2] = dice[0]; // 우
				ndice[3] = dice[5]; // 좌
				ndice[4] = dice[4]; // 하
				ndice[5] = dice[2]; // 하하
				break;
			// 상
			case 2:
				ndice[0] = dice[1]; // 바닥
				ndice[1] = dice[5]; // 상
				ndice[2] = dice[2]; // 우
				ndice[3] = dice[3]; // 좌
				ndice[4] = dice[0]; // 하
				ndice[5] = dice[4]; // 하하
				break;
			// 하
			case 3:
				ndice[0] = dice[4]; // 바닥
				ndice[1] = dice[0]; // 상
				ndice[2] = dice[2]; // 우
				ndice[3] = dice[3]; // 좌
				ndice[4] = dice[5]; // 하
				ndice[5] = dice[1]; // 하하
				break;
			}

			if (map[nx][ny] == 0)
				map[nx][ny] = ndice[0];

			else {
				ndice[0] = map[nx][ny];
				map[nx][ny] = 0;
			}

			for (int j = 0; j < 6; j++) {
				dice[j] = ndice[j];
			}

			System.out.println(dice[5]);

			x = nx;
			y = ny;
		}

		br.close();
	}
}

// 동 1 서 2 북 3 남 4