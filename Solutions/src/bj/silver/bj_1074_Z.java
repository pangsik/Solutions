package bj.silver;

import java.io.*;
import java.util.*;

public class bj_1074_Z {
	static int r, c, answer = 0, cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N);

		// 0부터 시작해서 방문하는 곳 마다 방문순서 적고 가기
		// 값만 기억하고 꼭 맵을 만들 필요는 없음

		Z(size, 0, 0);
		
		System.out.println(answer);

		br.close();
	}

	static void Z(int size, int istart, int jstart) {
		if (size == 2) {
			// 가장 작은 사각형에 도달하면 Z 순서대로 찍고 나간다
			for (int i = istart; i < istart + 2; i++) {
				for (int j = jstart; j < jstart + 2; j++) {
					if (i == r && c == j) {
						answer = cnt;
					}
					cnt++;
				}
			}
			return;
		}
		
		size /= 2;
		// r, c에 따라 어디로 들어갈지 결정.. cnt도 그만큼 더해줌
		
		// 1번 블록
		if ((r < istart + size) && (c < jstart + size)) {
			cnt += size * size * 0;
		}
		
		// 2번 블록
		if ((r < istart + size) && (c >= jstart + size)) {
			jstart += size;
			cnt += size * size * 1;
		}
		
		// 3번 블록
		if ((r >= istart + size) && (c < jstart + size)) {
			istart += size;
			cnt += size * size * 2;
		}
		
		// 4번 블록
		if ((r >= istart + size) && (c >= jstart + size)) {
			istart += size;
			jstart += size;
			cnt += size * size * 3;
		}
		
		Z(size, istart, jstart);
	}
}

