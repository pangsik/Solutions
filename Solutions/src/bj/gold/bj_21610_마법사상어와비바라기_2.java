package bj.gold;

import java.io.*;
import java.util.*;

public class bj_21610_마법사상어와비바라기_2 {
	static int N, M, d, s;
	static int[] di = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[][] map;
	static ArrayDeque<int[]> clouds;
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
		
		clouds = new ArrayDeque<int[]>();
		clouds.add(new int[] { N-1, 0 });
		clouds.add(new int[] { N-1, 1 });
		clouds.add(new int[] { N-2, 0 });
		clouds.add(new int[] { N-2, 1 });
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			d = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			Solution();
		}
		
		System.out.println(getAnswer());
		
		br.close();
	}
	
	private static int getAnswer() {
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		
		return sum;
	}

	private static void Solution() {
		// 1.이동 ㄱㄱ (모듈러 잘 하기!)
		move();

		// 2.구름 칸 +1 해주는데, 큐에서 없애지 말고 다시 큐에 넣어놓자
		rain();
		
		// 3.구름 사라졌다 치고 일단 큐는 놔둠

		// 4.다시 큐에서 하나씩 빼면서 물복사 시전.. 이때 어레리 하나 만들어서 위치들 저장해두기
		ArrayList<int[]> prevList = waterbug();

		// 5.전부 돌면서 2 이상인 칸 전부 구름 만들어주기.. 단, 4에서 만든 어레리와 비교하며 이전에 사라진 칸인지 아닌지 비교하여 구름 만들기
		makeCloud(prevList);
	}

	// 1.이동 ㄱㄱ (모듈러 잘 하기!)
	private static void move() {
		int size = clouds.size();
		for (int i = 0; i < size; i++) {
			int[] cur = clouds.poll();
			
			int nx = (cur[0] + di[d] * s) % N;
			int ny = (cur[1] + dj[d] * s) % N;
			
			if (nx < 0) {
				nx += N;
			}
			if (ny < 0) {
				ny += N;
			}
			
			clouds.add(new int[] { nx, ny });
		}
	}

	// 2.구름 칸 +1 해주는데, 큐에서 없애지 말고 다시 큐에 넣어놓자
	private static void rain() {
		int size = clouds.size();
		for (int i = 0; i < size; i++) {
			int[] cur = clouds.poll();
			
			map[cur[0]][cur[1]]++;
			
			clouds.add(cur);
		}
	}

	private static ArrayList<int[]> waterbug() {
		ArrayList<int[]> prevList = new ArrayList<int[]>();
		
		while (!clouds.isEmpty()) {
			int[] cur = clouds.poll();
			prevList.add(cur);
			
			// 1 3 5 7
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur[0] + di[1 + dir * 2];
				int ny = cur[1] + dj[1 + dir * 2];
				
				if (!check(nx, ny))
					continue;
				
				if (map[nx][ny] == 0)
					continue;
				
				map[cur[0]][cur[1]]++;
			}
			
		}
		
		return prevList;
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N) 
			return false;
		
		return true;
	}

	private static void makeCloud(ArrayList<int[]> prevList) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] < 2) 
					continue;
				
				boolean flag = true;
				for (int[] cur : prevList) {
					if (cur[0] == i && cur[1] == j) {
						flag = false;
						break;
					}
				}
				
				if (!flag) 
					continue;
				
				map[i][j] -= 2;
				clouds.add(new int[] { i, j });
			}
		}
	}
}

// N*N
// 각 칸에는 바구니 존재, 물 담을 수 있음 (제한 X)
// (1,1)~(N,N) 
// 행 끝까지 가면 처음으로 돌아옴.. 반대로도 마찬가지 + 열도 마찬가지
// 초기 구름 위치는 제일 왼쪽아래 4칸 (N-1, 0) (N-1, 1) (N-2, 0) (N-2, 1)
// 구름 M번 이동
// 방향  d, 거리 s
// 방향은 인덱스 1부터 시작, 좌 좌상 상 우상 우 우하 하 좌하

// 1.모든 구름이 d방향으로 s칸 이동
// 2.각 구름에서 비가 내려 구름이 있는 칸에 물 양 +1
// 3.구름이 모두 사라짐
// 4.2에서 물이 증가한 칸에 대해 물복사버그 마법 시전, 대각선 방향 칸들 중 물이 있는 바구니의 수 만큼 현재 칸 물 증가
//  - 단, 이때 대각선으로 맵 범위를 넘어가는 칸은 무시
// 5.바구니에 저장된 물 양이 2 이상인 모든 칸에 구름 생성, 물 양 2 줄어듦.. 이때 구름이 생기는 칸은 3에서 사라진 칸이 아니어야 함
// M번 이동 모두 끝난 후 바구니에 들어있는 물의 양의 합 구하기


// 1.이동 ㄱㄱ (모듈러 잘 하기!)
// 2.구름 칸 +1 해주는데, 큐에서 없애지 말고 다시 큐에 넣어놓자
// 3.구름 사라졌다 치고 일단 큐는 놔둠
// 4.다시 큐에서 하나씩 빼면서 물복사 시전.. 이때 어레리 하나 만들어서 위치들 저장해두기
// 5.전부 돌면서 2 이상인 칸 전부 구름 만들어주기.. 단, 4에서 만든 어레리와 비교하며 이전에 사라진 칸인지 아닌지 비교하여 구름 만들기














