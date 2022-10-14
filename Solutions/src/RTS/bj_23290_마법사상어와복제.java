package RTS;

import java.io.*;
import java.util.*;

public class bj_23290_마법사상어와복제 {
	static class Fish {
		int x, y, d;
		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static class Smell {
		int hp;
		public Smell() {
			this.hp = 3;
		}
	}
	
	static final int SIZE = 4;
	static int M, S, sharkX, sharkY;
	static int[] di = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 좌 좌상 상 우상 우 우하 하 좌하
	static int[] dj = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static ArrayDeque<Fish>[][] fishMap;
	static ArrayDeque<Smell>[][] smellMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		fishMap = new ArrayDeque[SIZE][SIZE];
		smellMap = new ArrayDeque[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				fishMap[i][j] = new ArrayDeque<>();
				smellMap[i][j] = new ArrayDeque<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			fishMap[x][y].offer(new Fish(x, y, d));
		}

		st = new StringTokenizer(br.readLine(), " ");
		sharkX = Integer.parseInt(st.nextToken()) - 1;
		sharkY = Integer.parseInt(st.nextToken()) - 1;
		
		Solution();
		
		System.out.println(getAnswer());
		
		br.close();
	}

	static int maxFishCnt;
	static int[] selected, maxSelected;
	static ArrayDeque<Fish> dupFishes;
	private static void Solution() {
		while (S --> 0) {
			// 물고기 복제 마법 시전
			duplicateMagic();
			
			// 물고기 이동
			moveFishes();
			
			// 상어 이동 방향 정하기
			selected = new int[3];
			maxSelected = new int[] { -1, -1, -1 };
			maxFishCnt = 0;
			findSharkMove(0);
			
			// 상어 이동
			moveShark();
			
			// 물고기 냄새 수명 깎기
			updateSmells();
			
			// 복제 마법 적용
			setDuplicatedFishes();
		}
	}
	
	private static void duplicateMagic() {
		dupFishes = new ArrayDeque<>();
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				int size = fishMap[i][j].size();
				while (size --> 0) {
					Fish cur = fishMap[i][j].poll();
					dupFishes.offer(new Fish(cur.x, cur.y, cur.d));
					fishMap[i][j].offer(cur);
				}
			}
		}
	}
	
	private static void moveFishes() {
		ArrayDeque<Fish> dq = new ArrayDeque<>();
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				while (!fishMap[i][j].isEmpty()) {
					Fish cur = fishMap[i][j].poll();
					
					boolean flag = false;
					for (int n = 0; n < 8; n++) {
						int nd = (cur.d - n + 8) % 8;
						int nx = cur.x + di[nd];
						int ny = cur.y + dj[nd];
						
						if (isLineOut(nx, ny))
							continue;
						
						if (nx == sharkX && ny == sharkY)
							continue;
						
						if (!smellMap[nx][ny].isEmpty())
							continue;
						
						flag = true;
						dq.offer(new Fish(nx, ny, nd));
						break;
					}
					
					if (!flag) {
						dq.offer(cur);
					}
				}
			}
		}
		
		while (!dq.isEmpty()) {
			Fish cur = dq.poll();
			fishMap[cur.x][cur.y].offer(cur);
		}
	}
	
	static int[] sdi = { -1, 0, 1, 0 }; // 상 좌 하 우
	static int[] sdj = { 0, -1, 0, 1 };
	private static void findSharkMove(int cnt) {
		if (cnt == 3) {
			int nx = sharkX;
			int ny = sharkY;
			int sum = 0;
			
			boolean[][] visited = new boolean[SIZE][SIZE];
			
			for (int i = 0; i < 3; i++) {
				int d = selected[i];
				
				nx = nx + sdi[d];
				ny = ny + sdj[d];
				
				if (isLineOut(nx, ny))
					return;
				
				if (!visited[nx][ny]) {
					sum += fishMap[nx][ny].size();
					visited[nx][ny] = true;
				}
			}
			
			if (maxFishCnt < sum) {
				maxFishCnt = sum;
				for (int i = 0; i < 3; i++) 
					maxSelected[i] = selected[i];
			}
			
			else if (maxFishCnt == sum) {
				if (maxSelected[0] == -1) {
					for (int i = 0; i < 3; i++) 
						maxSelected[i] = selected[i];
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			selected[cnt] = i;
			findSharkMove(cnt + 1);
		}
	}

	private static void moveShark() {
		int nx = sharkX;
		int ny = sharkY;
		for (int i = 0; i < 3; i++) {
			int d = maxSelected[i];
			nx = nx + sdi[d];
			ny = ny + sdj[d];
			
			if (!fishMap[nx][ny].isEmpty()) {
				fishMap[nx][ny] = new ArrayDeque<>();
				smellMap[nx][ny].offer(new Smell());
			}
		}
		sharkX = nx;
		sharkY = ny;
	}

	private static void updateSmells() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				int size = smellMap[i][j].size();
				for (int n = 0; n < size; n++) {
					Smell cur = smellMap[i][j].poll();
					
					if (--cur.hp == 0)
						continue;
					
					smellMap[i][j].offer(cur);
				}
			}
		}
	}

	private static void setDuplicatedFishes() {
		while (!dupFishes.isEmpty()) {
			Fish cur = dupFishes.poll();
			fishMap[cur.x][cur.y].offer(cur);
		}
	}
	
	private static boolean isLineOut(int nx, int ny) {
		return nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE;
	}

	private static int getAnswer() {
		int answer = 0;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				answer += fishMap[i][j].size();
			}
		}
		
		return answer;
	}
}

// 4*4 (1 base)
// 물고기 M마리
// 8방 이동
// 고기들은 각 칸에 있고 한 칸에 여러마리 가능
// 마법사 상어도 격자에 있고 물고기랑 같은 칸에 위치 가능 (첫 시작에 같은 칸 있는애들은 안죽이는듯)
// 1.상어가 모든 물고기들 복제마법 시전 (단, 바로 복제되는건 아니고 5번에서 복제되어 칸에 나타남)
// 2.모든 물고기 한 칸 이동 (상어, 물고기 냄새 있는 칸 or 맵 밖은 못감)
//   각 물고기는 자신이 가진 이동 방향이 이동 가능한 칸을 향할 때까지 방향을 45도 반시계 회전
//   만약 이동 가능한 칸이 없으면 이동 안함
// 3.상어 연속 3칸 이동, 상어는 상하좌우 이동 가능하고 격자 벗어나지 않음
//   이동 중 물고기랑 같은 칸 가면 그 칸의 물고기는 사라지고 해당 칸에 냄새를 남김
//   가능한 이동 방법 중 제외되는 물고기 수가 가장 많은 방법으로 이동, 그 방법이 여러개라면 사전순으로 가장 앞서는 방법 선택
// 4.두 턴 전에 생긴 물고기 냄새가 격자에서 사라짐 (수명관리 必)
// 5.1에서 사용한 복제마법 완료, 모든 복제된 물고기는 1에서의 위치와 방향 그대로 가짐


// 상어 위치는,, 따로 변수로 저장 (상 좌 하 우)
// 상어 이동경로 찾기 (중복순열? 순서대로 그냥 쭉 뽑기.. 뽑다가 더! 많이 먹는 경우에만 갱신하기 => 어차피 사전순이니!)
// 무조건 모든 경우 다 보긴 해야할듯

// 복제하면 임시로 복제된애들 저장해 둘 2차원 배열..
// 물고기 저장할 클래스(위치, 방향) 2차원 배열 필요
// 물고기 냄새 저장할 클래스(수명) 2차원 배열도 필요함
// 






















