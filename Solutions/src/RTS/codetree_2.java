package RTS;

import java.io.*;
import java.util.*;

public class codetree_2 {
	static int N, maxScore, H, W;
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 좌상 좌 좌하 하 우하 우 우상
	static int[] dj = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[][] movingPattern;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		H = N + 1;
		W = 4;
		
		movingPattern = new int[8][8];
		for (int i = 0; i < 8; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 8; j++) 
				movingPattern[i][j] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		ArrayList<int[]> op = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int blockNum = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			
			op.add(new int[] { blockNum, idx });
		}
		
		int[][] map = new int[H][W];
		
		maxScore = 0;
		strangeBucket(map, op, 0, 0);
		
		System.out.println(maxScore);
		
		br.close();
	}
	
	private static void strangeBucket(int[][] map, ArrayList<int[]> op, int opIdx, int scoreSum) {
		for (int i = opIdx; i < op.size(); i++) {
			int curBlockNum = op.get(i)[0];
			int curBlockIdx = op.get(i)[1];
			
			if (curBlockIdx == 0) {
				for (int j = 1; j <= 4; j++) {
					ArrayList<int[]> newOp = new ArrayList<>();
					
					for (int n = 0; n < op.size(); n++) {
						if (n == i) {
							newOp.add(new int[] { curBlockNum, j });
							continue;
						}
						newOp.add(op.get(n));
					}
					
					int[][] copyMap = new int[H][W];
					deepCopy(copyMap, map);
					
					strangeBucket(copyMap, newOp, i, scoreSum);
				}
				
				break;
			}
			
			curBlockIdx--;
			
			// 1.블럭 낙하
			setBlock(map, curBlockNum, curBlockIdx);
			
			// 2.중력
			gravity(map);
			
			// 3.한 줄 채워진거 있으면 줄 지우고 점수++
			int score = tetris(map);
			
			//  3-1.지워진 줄 있으면 다시 중력
			if (score > 0) {
				scoreSum += score;
				gravity(map);
			}
			
			// 4.규칙대로 블럭 이동
			deepCopy(map, moveBlock(map));
			
			// 5.중력
			gravity(map);
			
			// 6.한 줄 채워진거 있으면 줄 지우고 점수++
			score = tetris(map);
			
			//  6-1.지워진 줄 있으면 다시 중력
			if (score > 0) {
				scoreSum += score;
				gravity(map);
			}
		}
		
		maxScore = Math.max(scoreSum, maxScore);
	}
	
	private static void deepCopy(int[][] map1, int[][] map2) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map1[i][j] = map2[i][j];
			}
		}
	}
	
	private static int[][] moveBlock(int[][] map) {
		int[][] newMap = new int[H][W];
		
		for (int i = H - 1; i >= 0; i--) {
			for (int j = 0; j < W; j++) {
				int cur = map[i][j];
				if (cur == 0)
					continue;
				
				for (int d : movingPattern[cur - 1]) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					if (nx < 0 || nx >= H || ny < 0 || ny >= W)
						continue;
					
					if (newMap[nx][ny] == 0)
						newMap[nx][ny] = cur;
					
					else
						newMap[nx][ny] = Math.min(newMap[nx][ny], cur);
					
					break;
				}
			}
		}
		
		return newMap;
	}
	
	private static void setBlock(int[][] map, int blockNum, int idx) {
		for (int i = H - 1; i >= 0; i--) {
			if (map[i][idx] == 0) {
				map[i][idx] = blockNum;
				break;
			}
		}
	}
	
	private static int tetris(int[][] map) {
		int score = 0;
		
		for (int i = H - 1; i >= 0; i--) {
			boolean flag = true;
			for (int j = 0; j < W; j++) {
				if (map[i][j] == 0) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				score++;
				for (int j = 0; j < W; j++) {
					map[i][j] = 0;
				}
			}
		}
		
		return score;
	}

	private static void gravity(int[][] map) {
		for (int j = 0; j < W; j++) {
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][j] > 0) {
					dq.offer(map[i][j]);
					map[i][j] = 0;
				}
			}
			
			int idx = H - 1;
			while (!dq.isEmpty())
				map[idx--][j] = dq.poll();
		}
	}
}

// 가로로 4, 위로는 무한 (N 최대 100이니까 101? 주면 될듯?)
// n개 블럭이 순서대로 떨어지는데 1~8 숫자 가짐
// 블럭 위치 정해지는 경우도 있고 아닌 경우도 있음
// 아닌 경우는 알아서 정해야됨
// 블럭 떨어지고 한줄 다 채우면 점수 1점 얻음
// 블럭 떨어진 후에는 모든 블럭이 특정 조건따라 움직임
// 블록별로 이동방향 우선순위 주어지고 통 밖으로 안나가는 선에서 이동하게 됨
// 블럭들은 동시에 이동, 겹치게 되면 번호가 작은 한놈만 남음
// 이동 후 다시 가장 아래로 쭉 떨어지고 4개 채워지는 경우 해당 줄 사라지고 점수 얻음

// 인풋을 미리 받아두고 재귀를 타야하나...?
// 인풋 받아놓고 놓는 자리 0 들어오면 4가

// 1.블럭 낙하
// 2.중력
// 3.한 줄 채워진거 있으면 줄 지우고 점수++
// 4.규칙대로 블럭 이동
// 5.중력
// 6.한 줄 채워진거 있으면 줄 지우고 점수++