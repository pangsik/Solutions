package unSolved;

import java.util.*;
import java.io.*;

public class bj_1600_말이되고픈원숭이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] map = new int[W][H];
		
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		br.close();
	}
}

// bfs
// 