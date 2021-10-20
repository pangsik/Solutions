package bj.gold;

import java.util.*;
import java.io.*;

/*
 * @date : 21.10.20
 * 풀이 시간 : 90분 + 못품
 * 코멘트
 * 진짜 거의 다 풀었는데 rotate 재귀 타는 부분에서 size-2 가 아니라 size/2 를 줘서 틀림
 * 이미 풀어본 문제라 아는거라 생각해서 제대로 고민 안한게 큰 듯
 * 그리고 L=3으로 들어오는 테케만 잘 돌려봤어도 발견했을 문제.. 실전에서는 테케 다시 잘 확인하자
 */

public class bj_20058_마법사상어와파이어스톰_2 {
	static int N, Q, SIZE, iceSum, largest;
	static int[] di = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dj = { 1, 0, -1, 0 };
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		SIZE = (int) Math.pow(2, N);
		
		map = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			Solution(L);
		}
		
		getAnswer();
		System.out.println(iceSum);
		System.out.println(largest);
		
		br.close();
	}

	private static void Solution(int L) {
		int size = (int) Math.pow(2, L);
		
		for (int i = 0; i < SIZE; i += size) {
			for (int j = 0; j < SIZE; j += size) {
				rotate(i, j, size);
			}
		}
		
		melt();
	}

	private static void rotate(int startX, int startY, int size) {
		if (size < 2) {
			return;
		}
		
		int endX = startX + size - 1;
		int endY = startY + size - 1;
		
		for (int i = 0; i < size - 1; i++) {
			int tmp = map[startX][startY + i];
			
			map[startX][startY + i] = map[endX - i][startY];
			map[endX - i][startY] = map[endX][endY - i];
			map[endX][endY - i] = map[startX + i][endY];
			map[startX + i][endY] = tmp;
		}
		
		rotate(startX + 1, startY + 1, size - 2);
	}

	private static void melt() {
		ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (map[i][j] == 0)
					continue;
				
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					if (!check(nx, ny))
						continue;
					
					if (map[nx][ny] > 0)
						cnt++;
				}
				if (cnt < 3) 
					dq.add(new int[] { i, j });
			}
		}
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int x = cur[0];
			int y = cur[1];
			map[x][y]--;
		}
	}
	
	private static void getAnswer() {
		iceSum = 0;
		largest = 0;
		visited = new boolean[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
	}
	
	private static void bfs(int x, int y) {
		ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
		dq.add(new int[] { x, y });
		visited[x][y] = true;
		
		int cnt = 0;
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int curX = cur[0];
			int curY = cur[1];
			cnt++;
			
			iceSum += map[curX][curY];
			
			for (int d = 0; d < 4; d++) {
				int nx = curX + di[d];
				int ny = curY + dj[d];
				
				if (!check(nx, ny))
					continue;
				
				if (map[nx][ny] == 0 || visited[nx][ny])
					continue;
				
				dq.add(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}
		
		largest = Math.max(largest, cnt);
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE)
			return false;
		return true;
	}
}

// 2^N * 2^N
// 단계 L
// 격자를 2^L * 2^L 크기 부분 격자로 나눈 후 모든 부분 격자를 시계 방향으로 90도 회전
// 회전 후 얼음칸 3개 이상과 인접해있지 않은 칸은 양이 1 줄어듦 (꼭짓점 오는 애들은 항상 녹음) (이거 동시에 처리해야하나?)
// 출력
// 1.남아있는 얼음들의 합
// 2.남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
// 출력은 모든 칸 bfs 돌면서 가장 큰 덩이 구하는 김에 얼음 합도 구하면 될듯

// 1.rotate
//	시작점(startX, startY), 사이즈
//	다 돌리고 재귀 타보자
//  시작점 각각 ++, 사이즈 / 2
//	사이즈가 2보다 작으면 리턴
// 2.melt
//	모든 칸 돌면서 인접 칸이 얼음인 경우가 3 보다 작으면 녹임
//	이거 동시에 녹여줘야 할 듯
// 반복
// 3.정답 출력
// 	1)남아있는 얼음들의 합
// 	2)남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
// 	출력은 모든 칸 bfs 돌면서 가장 큰 덩이 구하는 김에 얼음 합도 구하면 될듯