package swea;

import java.util.*;
import java.io.*;

/*
 * @date : 21.10.16
 * 풀이 시간 : 35분
 * 코멘트
 * 처음 풀 때 보다 더 간단하게 만들었는데, 처음엔 복잡하게 하다보니 세세한 예외까지 다 처리가 됐었지만 이번엔 하나가 안됨 (길이 3보다 크도록 하는거)
 */

public class Solution_sw_2105_디저트카페_2 {
	static int N, startX, startY, answer;
	static int[] di = { 1, 1, -1, -1 }; // 우하 좌하 좌상 우상
	static int[] dj = { 1, -1, -1, 1 };
	static int[][] map;
	static ArrayDeque<Integer> dessertList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = -1;
			Solution();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution() {
		for (int i = 0; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				dessertList = new ArrayDeque<>();
				dessertList.add(map[i][j]);
				
				startX = i;
				startY = j;
				
				dfs(i, j, 1, 0);
			}
		}
	}

	private static void dfs(int x, int y, int len, int d) {
		int nx, ny;
		
		nx = x + di[d];
		ny = y + dj[d];
		
		if (check(nx, ny)) {
			dessertList.add(map[nx][ny]);
			dfs(nx, ny, len + 1, d);
			dessertList.remove(map[nx][ny]);
		}
		
		if (++d == 4) {
			if (nx == startX && ny == startY && len > 3)
				answer = Math.max(answer, len);
			return;
		}
		
		dfs(x, y, len, d);
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || dessertList.contains(map[nx][ny]))
			return false;
		
		return true;
	}
}


















