package bj.gold;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.23
 * 풀이 시간 : 80분
 * 코멘트
 * 조건들 나름 잘 정리했다고 했지만, 평소 행 열 우선순위는 항상 젤 왼쪽위로 모는 경우가 많아서 당연히 그렇게 생각하고 풀었다가 틀림
 * 문제 조건에서는 행이 가장 큰거, 열이 가장 큰거라고 했으니까 젤 오른쪽 아래로 몰아야 하는데 착각해서 틀림
 * 중력도 제대로 안읽고 내맘대로 했다가 처음에 틀렸음
 * 문제에서 시키는대로 하자...........
 */

public class bj_21609_상어중학교 {
	static class Block implements Comparable<Block> {
		int r, c, rainbowCnt, groupSize;
		
		public Block(int r, int c, int rainbowCnt, int groupSize) {
			this.r = r;
			this.c = c;
			this.rainbowCnt = rainbowCnt;
			this.groupSize = groupSize;
		}

		// 그룹 사이즈, 레인보우 수, 행, 열 순으로 정렬
		@Override
		public int compareTo(Block o) {
			if (this.groupSize != o.groupSize) 
				return Integer.compare(o.groupSize, this.groupSize);
			if (this.rainbowCnt != o.rainbowCnt) 
				return Integer.compare(o.rainbowCnt, this.rainbowCnt);
			if (this.r != o.r)
				return Integer.compare(o.r, this.r);
			return Integer.compare(o.c, this.c);
		}
	}
	
	static int N, M, score;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
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
		
		score = 0;
		Solution();
		
		System.out.println(score);
		
		br.close();
	}
	
	private static void Solution() {
		while (true) {
			// 1.모든 칸 bfs 돌면서 그룹 크기 + 무지개 블록 수 + 기준 블록 좌표 저장 (클래스로 만들고 컴페어러블 적용하자)
			Block maxGroupBlock = getMaxGroupBlock();
			
			// 더 부술 그룹이 없다면 리턴
			if (maxGroupBlock == null)
				return;
			
			// 2.1에서 찾은거 다시 bfs 돌면서 파괴하기 (빈 칸은 -2로 표시)
			destroyGroup(maxGroupBlock);
			
			// 3.중력.....
			gravity();
			
			// 4.회전
			rotate();
			
			// 5.중력
			gravity();
		}
	}

	private static Block getMaxGroupBlock() {
		//  시작 칸은 무조건 일반 블록인 경우만 시작 (일반 블록만 기준 블록이 되니까)
		visited = new boolean[N][N];
		PriorityQueue<Block> pq = new PriorityQueue<>();
		
		for (int i = 0 ; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이미 방문했다면 패스
				if (visited[i][j])
					continue;
				// 일반 블록이 아니라면 패스
				if (map[i][j] <= 0)
					continue;
				
				// bfs 돌고 기준 블록 좌표, 레인보우 수, 그룹 사이즈로 Block 객체 만들어서 리턴, pq에 삽입
				// 단, 그룹 사이즈가 2보다 작으면 넣지 않음
				Block block = bfs(i, j);
				if (block.groupSize < 2)
					continue;
				pq.add(block);
			}
		}
		
		if (pq.isEmpty()) {
			return null;
		}
		
		return pq.poll();
	}

	private static Block bfs(int r, int c) {
		ArrayDeque<int[]> rainbow = new ArrayDeque<>();
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { r, c });
		visited[r][c] = true;
		
		int color = map[r][c];
		int groupSize = 0;
		int rainbowCnt = 0;
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			groupSize++;
			if (map[cur[0]][cur[1]] == 0)
				rainbowCnt++;
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				// 범위 벗어남
				if (!check(nx, ny))
					continue;
				
				// 이미 방문함
				if (visited[nx][ny])
					continue;
				
				// 같은 블록 or 무지개 블록이 아님
				if (map[nx][ny] != color && map[nx][ny] != 0) 
					continue;
				
				dq.add(new int[] { nx, ny });
				visited[nx][ny] = true;
				
				if (map[nx][ny] == 0)
					rainbow.add(new int[] { nx, ny });
			}
		}
		
		while (!rainbow.isEmpty()) {
			int[] cur = rainbow.poll();
			visited[cur[0]][cur[1]] = false;
		}
		
		return new Block(r, c, rainbowCnt, groupSize);
	}

	private static void destroyGroup(Block maxGroupBlock) {
		score += Math.pow(maxGroupBlock.groupSize, 2);
		
		int r = maxGroupBlock.r;
		int c = maxGroupBlock.c;
		int color = map[r][c];
		
		// bfs 돌면서 블록 파괴
		visited = new boolean[N][N];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] { r, c });
		visited[r][c] = true;
		map[r][c] = -2;
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				// 범위 벗어남
				if (!check(nx, ny))
					continue;
				
				// 이미 방문함
				if (visited[nx][ny])
					continue;
				
				// 같은 블록 or 무지개 블록이 아님
				if (map[nx][ny] != color && map[nx][ny] != 0) 
					continue;
				
				dq.add(new int[] { nx, ny });
				visited[nx][ny] = true;
				map[nx][ny] = -2;
			}
		} 
	}

	private static void gravity() {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		// 모든 열에 대해 순서대로 진행
		for (int j = 0; j < N; j++) {
			int floor = N - 1; // 바닥 칸
			
			for (int i = N - 1; i >= 0; i--) {
				// 빈 칸은 패스
				if (map[i][j] == -2)
					continue;
				
				// 검은색 칸 만나면 여태 쌓은거 싹 처리하고 다시 시작
				if (map[i][j] == -1) {
					// 기준 칸 위에서부터 차례대로 쌓아줌
					while (!dq.isEmpty()) {
						map[floor][j] = dq.poll();
						floor--;
					}
					floor = i - 1;
					
					continue;
				}
				
				// 기준 칸 정해진 이후 그 위의 값들 수집
				dq.add(map[i][j]);
				map[i][j] = -2;
			}
			// 기준 칸 위에서부터 차례대로 쌓아줌
			while (!dq.isEmpty()) {
				map[floor][j] = dq.poll();
				floor--;
			}
		}
		
		// 검은색 블록만 움직이지 않음..!!!!!!
		// 
		
		// 제일 아래 칸 부터 올라가며 탐색
		// -2보다 큰 칸(무슨 색이든 블록이 있는 칸)을 찾으면, 그 칸을 기준으로 삼음
		// 이제 그 위 인덱스 보면서 -2보다 큰 칸(블록)들 큐에 담으며 원래 자리는 -2로 만들어줌
		// 끝까지 다 모았으면 아까 정한 기준 칸 위에서부터 순차적으로 큐에서 뽑아가며 위로 채워줌
	}

	private static void rotate() {
		// 반시계 방향 90도 회전
		int[][] copyMap = deepCopy();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[N - 1 - j][i] = copyMap[i][j];
			}
		}
	}

	private static int[][] deepCopy() {
		int[][] copyMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		return copyMap;
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)

			return false;
		return true;
	}
}

// N*N
// 모든 칸에 블록 있는채로 시작
// 검은 블록 : -1
// 무지개 블록 : 0
// 일반 블록 : M가지 색상 가짐 (M 이하의 자연수로 표현)
// |r1-r2| + |r1-r2| = 1을 만족하면 두 칸은 인접한 칸 (= 상하좌우 인접)
// 블록은 그룹으로 묶을 수 있음
// 한 그룹 내의 일반 볼륵온 색이 모두 같고, 무지개 블록 포함 가능, 검은 블록은 포함 불가
// 한 그룹 블록 수는 2 이상, 모두 인접해있어야함
// 블록 그룹의 기준 블록은 행이 가장 작고, 열이 가장 작은 블록 (젤 왼쪽 위.. 단, 무지개 블록은 기준이 될 수 없음)

// 1.크기가 가장 큰 그룹을 찾음. 여러 개라면 무지가 블록이 가장 많은 그룹, 그것도 여러개면 기준 블록 행이 가장 위, 그것도 여러개면 열이 가장 왼쪽
// 2.1에서 찾은 그룹의 모든 블록 제거, 블록 수를 B라고 했을 때, B^2점 획득
// 3.격자에 중력 작용 (젤 밑으로 내린다는 말인듯)
// 4.격자가 90도 반시계 방향으로 회전
// 5.다시 중력 작용
// 중력 : 있는 애들 중 가장 아래에 있는 애는 내려가지 않고 그 위에 있는 애들만..



// 1.모든 칸 bfs 돌면서 그룹 크기 + 무지개 블록 수 + 기준 블록 좌표 저장 (클래스로 만들고 컴페어러블 적용하자)
//  - 시작 칸은 무조건 일반 블록인 경우만 시작 (일반 블록만 기준 블록이 되니까)
//  - 다 구했으면 그룹 크기 내림차순, 무지개 블록 수 내림차순, 블록 좌표 행 오름차순, 블록 좌표 열 오름차순 정렬
// 2.1에서 찾은거 제일 첫번째꺼 가져와서 다시 bfs 돌면서 파괴하기

// 3.중력..... 은 천천히 생각해볼까
// 4.회전
// 5.중력













