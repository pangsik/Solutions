package swea;

import java.util.*;
import java.io.*;

public class d0_4335_무인도탈출 {
	static class Box {
		int width, depth, height;
		public Box(int width, int depth, int height) {
			this.width = width;
			this.depth = depth;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int w = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int h = Integer.parseInt(st.nextToken());
				Box b = new Box(w, d, h);
			}
			
			sb.append('#').append(tc).append(' ').append('A').append('\n');
		}
		
		br.close();
	}
}

// 1. 각각의 상자는 가로, 세로, 높이 축을 기준으로 90 도씩 회전시켜서 쌓을 수 있다.
// 2. N 개의 상자를 쌓는 순서에는 별다른 제약이 없으며, 모든 상자를 다 사용하지 않아도 된다.
// 3. 모든 상자의 밑면은, 바로 아래 쌓여진 상자의 윗면을 벗어나선 안된다.