package bj.gold;

import java.io.*;
import java.util.*;

public class bj_8972_미친아두이노 {
	static int[] di = { 0,  1, 1, 1,  0, 0, 0, -1, -1, -1 };
	static int[] dj = { 0, -1, 0, 1, -1, 0, 1, -1,  0,  1 };
	static int R, C, targetX, targetY;
	static char[][] map;
	static int[] move;
	static ArrayDeque<int[]> crazys = new ArrayDeque<int[]>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'I') {
					targetX = i;
					targetY = j;
				}
				else if (map[i][j] == 'R') {
					crazys.offer(new int[] { i, j });
				}
			}
		}
		
		String input = br.readLine();
		move = new int[input.length()];
		for (int i = 0; i < input.length(); i++) {
			move[i] = input.charAt(i) - '0';
		}
		
		int result = Solution();
		
		// 종수아두 생존
		if (result == -1) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
		}
		
		// 종수아두 생존 실패
		else {
			System.out.println("kraj " + result);
		}
		
		br.close();
	}

	private static int Solution() {
		for (int d = 0; d < move.length; d++) {
			// 1. 종수아두 8방향 이동 or 가만히 (보드 벗어나는 입력은 주어지지 않음)
			// 2. 종수아두가 미친아두가 있는 칸으로 이동하면 게임오버
			map[targetX][targetY] = '.';
			targetX += di[move[d]];
			targetY += dj[move[d]];
			if (map[targetX][targetY] == 'R') {
				return d + 1;
			}
			map[targetX][targetY] = 'I';
			
			int[][] cnt = new int[R][C];
			int size = crazys.size();
			for (int i = 0; i < size; i++) {
				int[] cur = crazys.poll();
				map[cur[0]][cur[1]] = '.';
				
				// 3. 미친아두는 8방향 중 종수아두와 가장 가까워지는 방향으로 한 칸 이동 : (r1, s1), (r2, s2) = |r1-r2| + |s1-s2|
				int tmp = getMinDir(cur[0], cur[1]);
				int nx = cur[0] + di[tmp];
				int ny = cur[1] + dj[tmp];
				
				crazys.offer(new int[] { nx, ny });
				cnt[nx][ny]++;
				
				// 4. 미친아두가 종수아두가 있는 칸으로 이동하면 게임오버
				if (map[nx][ny] == 'I') {
					return d + 1;
				}
			}
			// 5. 2개 이상의 미친아두가 같은 칸에서 만나면 모두 사라짐
			removeDup(cnt);
		}
		return -1;
	}
	
	private static void removeDup(int[][] cnt) {
		int size = crazys.size();
		for (int i = 0; i < size; i++) {
			int[] cur = crazys.poll();
			if (cnt[cur[0]][cur[1]] == 1) {
				map[cur[0]][cur[1]] = 'R';
				crazys.offer(cur);
			}
			else {
				map[cur[0]][cur[1]] = '.';
			}
		}
	}

	private static int getMinDir(int x, int y) {
		int minDist = Integer.MAX_VALUE;
		int res = 0;
		for (int d = 1; d < 10; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
//			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
//				continue;
			
			int dist = getDist(nx, ny);
			if (minDist > dist) {
				minDist = dist;
				res = d;
			}
		}
		return res;
	}
	
	private static int getDist(int x, int y) {
		return Math.abs(targetX - x) + Math.abs(targetY - y);
	}
}


// R * C
// 1. 종수아두 8방향 이동 or 가만히
// 2. 종수아두가 미친아두가 있는 칸으로 이동하면 게임오버
// 3. 미친아두는 8방향 중 종수아두와 가장 가까워지는 방향으로 한 칸 이동 : (r1, s1), (r2, s2) = |r1-r2| + |s1-s2|
// 4. 미친아두가 종수아두가 있는 칸으로 이동하면 게임오버
// 5. 2개 이상의 미친아두가 같은 칸에서 만나면 모두 사라짐

// 종수아두 위치, 미친아두 위치, 종수아두 이동 방향
// 입력대로 이동했을 때 보드의 상태 출력, 중간에 패배하면 몇 번째 움직임에서 죽는지 출력

// . : 빈 칸
// I : 종수아두
// R : 미친아두

// 보드 벗어나는 입력은 주어지지 않음









