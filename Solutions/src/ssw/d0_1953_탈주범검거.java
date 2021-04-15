package ssw;

import java.io.*;
import java.util.*;

public class d0_1953_탈주범검거 {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, R, C, L, cnt;
	static int[][] map;
	
	static final int[] di = { -1, 0 , 1, 0 }; // 상우하좌
	static final int[] dj = { 0, 1 , 0, -1 };
	static final int[][] pipe = {
			{},
			{ 0, 1, 2, 3 }, // 상하좌우
			{ 0, 2 }, // 상하
			{ 3, 1 }, // 좌우
			{ 0, 1 }, // 상우
			{ 2, 1 }, // 하우
			{ 2, 3 }, // 하좌
			{ 0, 3 } // 상좌
			};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N*M 맵
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken()); // 탈주범이 들어간 맨홀 (R,C)
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken()); // 탈주 후 지난 시간
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			cnt = 0;
			bfs();
			
			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	static void bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.offer(new Pos(R, C));
		visited[R][C] = true;
		int time = 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Pos cur = q.poll();
				cnt++;
				
				int tmp = map[cur.x][cur.y];
				for (int d : pipe[tmp]) {
					int nx = cur.x + di[d];
					int ny = cur.y + dj[d];
					
					if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] > 0 && !visited[nx][ny]) {
						boolean check = false;
						
						// 0311 A형특강교재 78p 참고
						for (int n : pipe[map[nx][ny]]) {
							if ((d + 2) % 4 == n)
								check = true;
						}
						
						if (check) { // 현재 배수관과 nx, ny 배수관이 연결되는지 확인 必
							q.offer(new Pos(nx, ny));
							visited[nx][ny] = true;
						}
					}
				}
			}
					
			if (++time == L) break;
		}
	}
}


// 탈주범은 1시간 뒤 맨홀로 들어감
// 탈주한지 1시간 뒤는 방금 들어간 위치만 도달 가능
// 탈주범이 2시간 후 도달할 수 있는 지점은 들어간 맨홀로부터 1만큼 떨어진 곳

// 1 : 상하좌우
// 2 : 상하
// 3 : 좌우
// 4 : 상우
// 5 : 하우
// 6 : 하좌
// 7 : 상좌