package bj.silver;

import java.io.*;
import java.util.*;

public class bj_7576_토마토 {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[][] map;
	static int N, M, day, cnt;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static Queue<Pos> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		map = new int[N][M];
		q = new LinkedList<>();
		cnt = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					q.offer(new Pos(i, j));
				}
				else if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		bfs();
		
		if (cnt == 0) System.out.println(--day);
		else System.out.println(-1);
		
		br.close();
	}
	
	static void bfs() {
		while (!q.isEmpty()) {
			int qsize = q.size();
			
			for (int i = 0; i < qsize; i++) {
				Pos cur = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + di[d];
					int ny = cur.y + dj[d];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
						q.add(new Pos(nx, ny));
						map[nx][ny] = 1;
						cnt--;
					}
				}
			}
			day++;
		}
	}
}

// 하루 지나면 안익은 토마토는 옆(상하좌우)의 익은 토마토 영향으로 익게됨
// 며칠이 지나야 토마토가 다 익게 되는지 최소 일수를 구하라
// 단, 일부 칸에는 토마토가 들어있지 않을 수도 있다.
// 1:익은토마토
// 0:그냥토마토
// -1:아무것도없음
