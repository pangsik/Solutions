package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.18
 * 풀이 시간 : 못품
 * 코멘트
 * 맵에 키로 값 두 개를 넣는 방법을 몰라서 못품
 * 근데 괜히 어려워지는듯.. 정답 보자
 */

public class Solution_sw_5653_줄기세포배양_1 {
	static class Cell {
		int X, hp, x, y;
		boolean isActive;
		
		public Cell(int hp, int x, int y) {
			this.X = hp;
			this.hp = hp;
			this.x = x;
			this.y = y;
			this.isActive = false;
		}
	}
	
	static int N, M, K, startX, startY;
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static ArrayDeque<Cell> aliveCells;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N + K + 10][M + K + 10];
			startX = map.length / 2 - N/2;
			startY = map[0].length / 2 - N/2;
			aliveCells = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int cellHp = Integer.parseInt(st.nextToken());
					map[startX + i][startY + j] = cellHp;
					if (cellHp > 0) 
						aliveCells.add(new Cell(cellHp, startX + i, startY + j));
				}
			}
			
			Solution();
			
			System.out.println("#" + tc + " " + aliveCells.size());
		}
		
		br.close();
	}
	
	private static void Solution() {
		while (K-- > 0) {
			tmp();
		}
	}

	private static void tmp() {
		HashMap<Integer, Integer> tmp = new HashMap<>();
		
		int size = aliveCells.size();
		for (int i = 0; i < size; i++) {
			Cell cur = aliveCells.poll();
			
			// 비활성인 경우
			if (!cur.isActive) {
				// 활성 시간 체크
				if (--cur.hp == 0) {
					cur.isActive = true;
				}
				aliveCells.add(cur);
				continue;
			}
			
			// 활성인 경우 번식 가능 여부 체크
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d]; 
				
				// 빈자리 있으면 번식
				if (map[nx][ny] == 0) {
					int key = nx * N + ny;
					
					if (tmp.containsKey(key)) {
						if (cur.X > tmp.get(key)) {
							tmp.put(key, cur.X);
						}
					}
					else {
						tmp.put(key, cur.X);
					}

//					map[nx][ny] = cur.X;
//					aliveCells.add(new Cell(cur.X, nx, ny));
				}
			}
			
			// 아직 생존 시간이 남았으면 큐에 넣기
			if (++cur.hp < cur.X)
				aliveCells.add(cur);
		}
		
		for (int key : tmp.keySet()) {
			int x = key / N;
			int y = key % M;
			int val = tmp.get(key);
			System.out.println(x + " " + y);
			
			map[x][y] = val;
			aliveCells.add(new Cell(val, x, y));
		}
	}
}

// 줄기 세포는 1*1로 시작
// 줄기 세포는 초기에 비활성 상태
// 생명력 수치 X를 갖는데, X시간 동안 비활성 상태 -> X시간 지나는 순간 활성 상태
// 활성 상태가 되면 X시간동안 생존 가능, X시간이 지나는 순간 죽음
// 죽어도 소멸은 안되고 그리드 셀 차지
// 활성화 되면 상하좌우 네 방향으로 번식
// 번식된 줄기 세포는 비활성 상태
// 번식하려는 방향에 이미 뭐가 있으면 번식 X
// 두 개 이상의 세포가 동시에 번식하려는 경우 생명력 수치가 높은 줄기 세포가 번식
// 배양 용기 크기는 무한하다고 가정
// K시간 후 살아있는 줄기 세포 (비활성 + 활성)의 총 개수를 구해라

// 1.배열 크기 무한?
//  - N, M, K로 배열 크기와 시작 기준점 
//  - 적당히 큰 배열 주고 가운데서 시작
// 2.살아있는 세포(비활성화, 활성화)들을 큐에 담아 관리
//   세포 정보는 좌표, hp, 활성화 여부를 포함
// 3.배열로 전체 그리드를 관리, int로 관리하여 그 자리의 세포 유무(다른 세포가 번식해서 들어갈 수 있는지)만 체크

// 1.시간 체크
// 2.모든 살아있는 세포들 체크
//  활성여부 확인
//  - 비활성인 경우 : hp--, hp가 0이 되면 활성화
//  - 활성인 경우 : hp++, 네 방향 체크 후 번식, hp == X 가 되면 die (큐에 다시 넣지 않음)
// 3.번식
//  - 4방 체크 후 빈 칸이 있다면 번식
//  - 동시 번식 경우를 어떻게 체크할 것인가............














