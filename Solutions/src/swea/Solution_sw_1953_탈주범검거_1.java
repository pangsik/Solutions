package swea;

import java.io.*;
import java.util.*;

// @date : 21.10.13
// 풀이 시간 : 60분
// bfs 깊이 체크하는거, 반대 방향 올 수 있는지 (터널 연결 여부 판별) 체크하는거 어려웠음

public class Solution_sw_1953_탈주범검거_1 {
	static int N, M, R, C, L, cnt;
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	static int[][] direction = {
			{},   // 0 : 빈 칸
			{ 0, 1, 2, 3 }, // 1 : 상하좌우
			{ 0, 2 }, // 2 : 상하 
			{ 1, 3 }, // 3 : 좌우		
			{ 0, 1 }, // 4 : 상우	
			{ 1, 2 }, // 5 : 하우			
			{ 2, 3 }, // 6 : 하좌			
			{ 0, 3 }, // 7 : 상좌		
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Solution();
			
			System.out.println("#" + tc + " " + cnt);
		}
		
		br.close();
	}
	
	private static void Solution() {
		cnt = 0;
		visited = new boolean[N][M];
		
		ArrayDeque<int[]> tunnel = new ArrayDeque<int[]>();
		tunnel.offer(new int[] { R, C });
		visited[R][C] = true;
		cnt++;
		
		int lv = 0;
		while (!tunnel.isEmpty()) {
			if (++lv == L) {
				break;
			}
			int size = tunnel.size();
			for (int i = 0; i < size; i++) {
				int[] cur = tunnel.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				int[] dir = direction[map[curX][curY]];
				
				for (int d : dir) {
					int nx = curX + di[d];
					int ny = curY + dj[d];
					
					if (!check(nx, ny, curX, curY)) {
						continue;
					}
					
					tunnel.offer(new int[] { nx, ny });
					visited[nx][ny] = true;
					cnt++;
				}
			}
		}
	}

	private static boolean check(int nx, int ny, int x, int y) {
		// 맵 범위 체크, 방문 여부 체크
		if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0 || visited[nx][ny]) {
			return false;
		}
		
		// 이동 가능 여부 체크 (터널끼리 이어져 있는지)
		// (nx,ny) -> (x,y) 이동이 가능한가?
		int[] dir = direction[map[nx][ny]];
		for (int d : dir) {
			int nnx = nx + di[d];
			int nny = ny + dj[d];
			
			if (nnx == x && nny == y) {
				return true;
			}
		}
		
		return false;
	}
}

// 터널 종류
// 1 : 상하좌우
// 2 : 상하
// 3 : 좌우
// 4 : 상우
// 5 : 하우
// 6 : 하좌
// 7 : 상좌

// 탈주범은 한 시간 뒤 지하로 들어감 (시작시간 1)
// 시간당 1의 거리 움직일 수 있음

// 지도,시작 위치,경과 시간이 주어질 때 탈주범이 위치할 수 있는 장소의 개수 계산
// N*M
// 터널 종류에 따른 이동 방향 하드코딩으로 미리 저장
// 배열로 저장해서 해당 칸마다 어느 방향으로 이동이 가능한지 알 수 있도록 하기
// 방문 여부, 이동 가능 여부 체크 (터널이 이어져 있어야 함)
// 탈주범 이동 가능 위치 큐에 저장해놓고 하나씩 뽑아서 보자 (이미 한 곳은 빼기)

