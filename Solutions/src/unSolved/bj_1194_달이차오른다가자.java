package unSolved;

import java.util.*;
import java.io.*;

public class bj_1194_달이차오른다가자 {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static char[][] map;
	static boolean[] keys;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		keys = new boolean[6];
		
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == '0') {
					
				}
			}
		}
		
		System.out.println(Arrays.toString(keys));
		
		br.close();
	}
}

// A : 65
// F : 70
// a : 97
// f : 102

// 

// . : 빈 칸 (언제든 이동 가능)
// # : 벽 (이동 불가능)
// a-f : 열쇠 (언제든 이동 가능, 처음 들어갈 때 열쇠를 집음)
// A-F : 문 (대응하는 열쇠가 있을 때 이동 가능)
// 0 : 현재 위치 (벗어나면 여긴 빈 칸)
// 1 : 출구 (목적지) (적어도 1개 있다.. 여러 개 있을 수 있음)

// 한 번 움직일 때 상하좌우로 한 칸 이동
// 미로 탈출할 때 걸리는 이동 횟수 최솟값