package problems;

import java.util.*;
import java.io.*;

public class Main_21610_마법사상어와비바라기 {
	static int N, M;
	static int[] di = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[][] map;
	static boolean[][] cloudMap;
	static ArrayDeque<int[]> cloud;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " "); 
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		initCloud();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());

			moveOper(d, s);
		}
		
		System.out.println(getTotalWater());
		
		br.close();
	}

	private static void moveOper(int d, int s) {
		// 1.모든 구름이 d 방향으로 s칸 이동
		moveCloud(d, s);
		
		// 2.각 구름에서 비가 내려 구름이 있는 칸의 바구니 물 양 +1
		rain();
		
		// 3.구름 모두 사라짐 -> 어느 칸에서 사라졌는지 기억해야됨.. 그냥 기존 만들어둔 구름 가만 냅두기?
		//   없애지 말기!! cloud는 4번에서, cloudMap은 5번에서 또 쓸 예정
		
		// 4.2에서 물이 증가한 칸에 물복사버그.. 버그가 일어나는 칸 기준!!
		//   대각선 방향으로 거리 1인 칸에 "물이 있는 바구니의 수" 만큼 물 양 증가
		//   단, 이동할 때랑 다르게 이 때는 경계 넘어가는 칸은 취급 안함
		waterCopyBug();
		
		// 5.바구니에 저장된 물 양 2 이상인 모든 칸 구름 생성, 물 양 -2
		//   이때, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함
		createCloud();
	}
	
	private static void moveCloud(int d, int s) {

		cloudMap = new boolean[N][N];
		
		int size = cloud.size();
		
		for (int i = 0; i < size; i++) {
			int[] cur = cloud.poll();

			int nx = ((cur[0] + di[d] * s) % N + N) % N;
			int ny = ((cur[1] + dj[d] * s) % N + N) % N;
			
			cloud.offer(new int[] { nx, ny });
		}
	}

	private static void rain() {
		int size = cloud.size();
		
		for (int i = 0; i < size; i++) {
			int[] cur = cloud.poll();
			map[cur[0]][cur[1]]++;
			cloudMap[cur[0]][cur[1]] = true;
			
			cloud.offer(cur);
		}
	}

	private static void waterCopyBug() {
		while (!cloud.isEmpty()) {
			int[] cur = cloud.poll();
			int cnt = 0;
			
			// 대각선 확인, 물 있는 칸 수 세서 더해주기
			for (int d = 1; d < 8; d += 2) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 0)
					continue;
				
				cnt++;
			}
			
			map[cur[0]][cur[1]] += cnt;
		}
	}

	private static void createCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이전에 구름 생긴곳 아니면서 물 양 2 이상인곳
				if (!cloudMap[i][j] && map[i][j] > 1) {
					map[i][j] -= 2;
					cloud.offer(new int[] { i, j });
				}
			}
		}
	}

	private static void initCloud() {
		// (N-1,0), (N-1,1), (N-2,0), (N-2,1) 비구름 생성

		cloud = new ArrayDeque<>();
		cloud.offer(new int[] { N-1, 0 });
		cloud.offer(new int[] { N-1, 1 });
		cloud.offer(new int[] { N-2, 0 });
		cloud.offer(new int[] { N-2, 1 });
	}
	
	private static int getTotalWater() {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		
		return sum;
	}
}

// N*N
// 1,1시작.. -1 해주기
// N-1번행 아래에는 0번행, 여튼 상하 좌우로 연결되어 있음
// (N-1,0), (N-1,1), (N-2,0), (N-2,1) 비구름 생성
// 구름 M번 이동
// 8방.. 좌 좌상 상 ...... 시계방향... 1부터 시작이니 입력에서 -1 해주기

// 1.모든 구름이 d 방향으로 s칸 이동
// 2.각 구름에서 비가 내려 구름이 있는 칸의 바구니 물 양 +1
// 3.구름 모두 사라짐
// 4.2에서 물이 증가한 칸에 물복사버그.. 버그가 일어나는 칸 기준!!
//   대각선 방향으로 거리 1인 칸에 "물이 있는 바구니의 수" 만큼 물 양 증가
//   단, 이동할 때랑 다르게 이 때는 경계 넘어가는 칸은 취급 안함
// 5.바구니에 저장된 물 양 2 이상인 모든 칸 구름 생성, 물 양 -2
//   이때, 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함