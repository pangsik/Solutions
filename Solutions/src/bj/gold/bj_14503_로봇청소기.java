package bj.gold;

import java.util.*;
import java.io.*;

public class bj_14503_로봇청소기 {
	static int N, M, r, c, d;
	static final int[] di = { -1, 0, 1, 0 }; // 북 동 남 서
	static final int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solution());
		
		br.close();
	}
	
	private static int solution() {
		int cnt = 0;
		while (true) {
			// 1.현재 위치 청소
			if (!visited[r][c]) {
				visited[r][c] = true;
				cnt++;
			}
			boolean flag = false;
			// 2.현재 방향 기준 왼쪽 방향부터 차례대로 탐색
			for (int i = 0; i < 4; i++) {
				if (--d < 0) d = 3;
				
				int nx = r + di[d];
				int ny = c + dj[d];
				
				// 범위 벗어나면 패쓰~.. 없어도 될듯
//				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
//					continue;
				
				// 1) 청소하지 않은 공간이 있으면 회전 후 한 칸 전진, 1번으로 돌아감
				if (map[nx][ny] == 0 && !visited[nx][ny]) {
					r = nx;
					c = ny;
					break;
				}
				
				// 2) 청소할 공간이 없으면 그 방향으로 회전
				
				// 3,4) 네 방향 모두 청소가 되어있거나 벽인 경우...
				if (i == 3)
					flag = true;
			}

			// 3,4) 네 방향 모두 청소가 되어있거나 벽인 경우... 후진 가능 여부 체크
			if (flag) {
				int dir = (d + 2) % 4; 
				r += di[dir];
				c += dj[dir];
				
				// 뒤가 벽이면 그만
				if (map[r][c] == 1)
					break;
			}
		}
		
		return cnt;
	}
	
}

// N*M
// 1.현재 위치 청소
// 2.현재 위치에서 "현재 방향 기준" 왼쪽 방향부터 차례대로 칸 탐색
//  1) 왼쪽에 청소하지 않은 공간이 있으면 그 방향으로 회전 후 한 칸 전진, 1번으로 돌아감
//  2) 왼쪽에 청소할 공간 없으면 그 방향으로 회전, 2번으로 돌아감
//  3) 네 방향 모두 청소가 되어있거나 벽인 경우, 바라보는 방향을 유지한 채 한 칸 후진, 2번으로 돌아감
//  4) 네 방향 모두 청소가 되어있거나 벽이면서, 뒤쪽 방향이 벽인 경우 작동 정지
