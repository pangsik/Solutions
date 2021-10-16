package bj.gold;

import java.io.*;
import java.util.*;

/*
 * 난이도 : g4
 * 풀이시간 : 1h 20m
 * rotate : 4중 스왑으로 구현, 그림 그려가면서 규칙 찾아서 인덱스 조정
 * 어떻게 풀긴 했는데 배열 돌리기 연습 더 필요할듯
 */

public class bj_20058_마법사상어와파이어스톰 {
	static int N, Q, SIZE;
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
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
			fireStorm(Integer.parseInt(st.nextToken()));
		}
		
		System.out.println(getTotalIce());
		System.out.println(getBiggestIce());
		
		br.close();
	}

	private static void fireStorm(int L) {
		int sectionSize = (int) Math.pow(2, L);
		
		// 부분 격자 회전
		for (int i = 0; i < SIZE; i += sectionSize) {
			for (int j = 0; j < SIZE; j+= sectionSize) {
				rotate(i, j, sectionSize);
			}
		}
		
		// 얼음 녹이기
		meltIce();
	}
	
	private static void rotate(int x, int y, int size) {
		while (size > 0) {
			int n = size - 1;			
			
			for (int i = 0; i < n; i++) {
				int tmp = map[x][y + i];
				map[x][y + i] = map[x + n - i][y];
				map[x + n - i][y] = map[x + n][y + n - i];
				map[x + n][y + n - i] = map[x + i][y + n];
				map[x + i][y + n] = tmp;
			}
			
			x++;
			y++;
			size -= 2;
		}
	}
	
	private static void meltIce() {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				// 얼음이 없는 자리면 패스
				if (map[i][j] == 0)
					continue;
				
				// 인접 칸 중 얼음 없는 자리 카운트
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					// 얼음 없는 칸 cnt++, 두 칸 이상이면 큐에 넣고 반복문 종료
					if (check(nx, ny) || map[nx][ny] == 0) {
						cnt++;
						if (cnt == 2) {
							dq.offer(new int[] { i , j });
							break;
						}
					}
				}
			}
		}
		
		// 녹이기
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			map[cur[0]][cur[1]]--;
		}
	}
	
	private static int getTotalIce() {
		int sum = 0;
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	static boolean[][] visited;
	private static int getBiggestIce() {
		int maxIce = 0;
		visited = new boolean[SIZE][SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (!visited[i][j] && map[i][j] > 0)
					maxIce = Math.max(maxIce, bfs(i, j));
			}
		}
		
		return maxIce;
	}

	private static int bfs(int i, int j) {
		int res = 1;
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		
		dq.offer(new int[] { i, j });
		visited[i][j] = true;
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (check(nx, ny))
					continue;
				
				if (visited[nx][ny] || map[nx][ny] == 0)
					continue;
				
				dq.offer(new int[] { nx, ny });
				visited[nx][ny] = true;
				res++;
			}
		}
		
		return res;
	}
	
	private static boolean check(int nx, int ny) {
		return nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE;
	}
}

// 2^N * 2^N

// 단계 L
// 2^L * 2^L 크기의 부분 격자로 나누고 모든 부분 격자를 90도 회전
// 얼음이 있는 칸 3개 이상과 인접해있지 않으면 1 줄어듦
// 파이어스톰 Q번 시전
// 1.남아있는 얼음 A[r][c]의 합
// 2.남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수
//  - 얼음이 있는 칸끼리 연결돼있으면 덩어리
