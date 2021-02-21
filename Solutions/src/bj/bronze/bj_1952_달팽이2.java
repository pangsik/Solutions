package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1952_달팽이2 {
	static int[] di = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dj = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[M][N];
		map[0][0] = true;
		int i = 0;
		int j = 0;
		int d = 0;
		int cnt = 1;
		int answer = 0;

		while (cnt < N * M) {
			int ni = i + di[d];
			int nj = j + dj[d];

			// 맵 밖으로 나가는 경우 || 이미 방문한 지역인 경우 -> 방향 바꿔줌
			if (!(ni >= 0 && ni < M && nj >= 0 && nj < N) || map[ni][nj]) {
				answer++;
				d++;
				if (d > 3)
					d = 0;
				continue;
			}
			
			i = ni;
			j = nj;
			map[i][j] = true;
			cnt++;
		}
		
		System.out.println(answer);

		br.close();
	}
}

// 