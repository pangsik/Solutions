package RTS;

import java.io.*;
import java.util.*;

public class codetree_나무박멸 {
	
	static int N, M, K, C, totalKillTreeCnt;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map, killerMap;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		killerMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		totalKillTreeCnt = 0;
		Solution();
		
		System.out.println(totalKillTreeCnt);
		
		br.close();
	}
	
	private static void Solution() {
		while (M --> 0) {
			// 나무 성장
			growth();
			
			// 나무 번식
			breeding();
			
			// 제초제 수명 --
			updateKiller();
			
			// 제초제 살포
			setKiller();
		}
	}

	private static void growth() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= 0)
					continue;
				
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					if (isLineOut(nx, ny))
						continue;
					
					if (map[nx][ny] > 0)					
						cnt++;
				}
				
				map[i][j] += cnt;
			}
		}
	}

	private static void breeding() {
		int[][] tmp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= 0)
					continue;
				
				ArrayList<int[]> list = getCanBreedList(i, j);
				
				if (list.isEmpty())
					continue;
				
				int num = map[i][j] / list.size();
				
				for (int[] cur : list) 
					tmp[cur[0]][cur[1]] += num;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tmp[i][j] == 0)
					continue;
				
				map[i][j] = tmp[i][j];
			}
		}
	}

	private static ArrayList<int[]> getCanBreedList(int x, int y) {
		ArrayList<int[]> list = new ArrayList<>();
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (isLineOut(nx, ny))
				continue;
			
			if (map[nx][ny] != 0)
				continue;
			
			if (killerMap[nx][ny] > 0)
				continue;
			
			list.add(new int[] { nx, ny });
		}
		
		return list;
	}

	private static void updateKiller() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (killerMap[i][j] == 0)
					continue;
				
				killerMap[i][j]--;
			}
		}
	}
	
	private static void setKiller() {
		int maxCnt = Integer.MIN_VALUE;
		int[] maxPos = new int[2];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = getKillCnt(i, j);
				
				if (maxCnt < cnt) {
					maxCnt = cnt;
					maxPos[0] = i;
					maxPos[1] = j;
				}
			}
		}
		
		totalKillTreeCnt += maxCnt;
		killTrees(maxPos[0], maxPos[1]);
	}
	
	private static void killTrees(int x, int y) {
		if (map[x][y] > 0)
			map[x][y] = 0;
		killerMap[x][y] = C;
		
		// 좌상
		for (int n = 1; n <= K; n++) {
			int nx = x - n;
			int ny = y - n;
			
			if (isLineOut(nx, ny))
				break;
			
			killerMap[nx][ny] = C;
			
			if (map[nx][ny] <= 0) 
				break;
			
			map[nx][ny] = 0;
		}

		// 우상
		for (int n = 1; n <= K; n++) {
			int nx = x - n;
			int ny = y + n;
			
			if (isLineOut(nx, ny))
				break;
			
			killerMap[nx][ny] = C;
			
			if (map[nx][ny] <= 0) 
				break;
			
			map[nx][ny] = 0;
		}

		// 우하
		for (int n = 1; n <= K; n++) {
			int nx = x + n;
			int ny = y + n;
			
			if (isLineOut(nx, ny))
				break;
			
			killerMap[nx][ny] = C;
			
			if (map[nx][ny] <= 0) 
				break;
			
			map[nx][ny] = 0;
		}

		// 좌하
		for (int n = 1; n <= K; n++) {
			int nx = x + n;
			int ny = y - n;
			
			if (isLineOut(nx, ny))
				break;
			
			killerMap[nx][ny] = C;
			
			if (map[nx][ny] <= 0) 
				break;
			
			map[nx][ny] = 0;
		}
	}

	private static int getKillCnt(int x, int y) {
		if (map[x][y] <= 0)
			return 0;
		
		int cnt = map[x][y];
		
		// 좌상
		for (int n = 1; n <= K; n++) {
			int nx = x - n;
			int ny = y - n;
			
			if (isLineOut(nx, ny))
				break;
			
			if (map[nx][ny] <= 0)
				break;
			
			cnt += map[nx][ny];
		}
		
		// 우상
		for (int n = 1; n <= K; n++) {
			int nx = x - n;
			int ny = y + n;
			
			if (isLineOut(nx, ny))
				break;
			
			if (map[nx][ny] <= 0)
				break;
			
			cnt += map[nx][ny];
		}
		
		// 우하
		for (int n = 1; n <= K; n++) {
			int nx = x + n;
			int ny = y + n;
			
			if (isLineOut(nx, ny))
				break;
			
			if (map[nx][ny] <= 0)
				break;
			
			cnt += map[nx][ny];
		}
		
		// 좌하
		for (int n = 1; n <= K; n++) {
			int nx = x + n;
			int ny = y - n;
			
			if (isLineOut(nx, ny))
				break;
			
			if (map[nx][ny] <= 0)
				break;
			
			cnt += map[nx][ny];
		}
		
		return cnt;
	}

	private static boolean isLineOut(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= N;
	}
}

// n*n
// 제초제는 k 범위만큼 대각선으로 퍼지고 벽이 있으면 가로막힘
// 1.인접한 4개 칸 중 나무가 있는 칸의 수만큼 나무 성장 (모든 나무 동시에 성장)
// 2.기존에 있었던 나무들은 인접 칸 중 벽, 다른 나무, 제초제 없는 칸에 번식 진행
//   각 칸의 나무 그루 수에서 총 번식이 가능한 칸의 개수만큼 나누어진 그루 수만큼 번식이 됨 (나머지 버림, 동시에 일어남)
//   번식되는 나무 수 = 현재 칸 나무 수 / 현재 칸 나무 인접 4칸 중 번식 가능한 칸 개수
// 3.제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제 뿌림 (4개 대각선 방향으로 k칸만큼 전파)
//   제초제 전파되다가 벽, 나무가 아예 없는 칸이 있으면 그 칸까지만 뿌려지고 더 이상 전파되지 않음
//   제초제는 c년만큼 유지 (이미 뿌려진 곳에 새로 뿌려지면 새로운 c년만큼 유지됨)
//   칸이 여러 개라면 행, 열 순으로 선택 (같은 경우 업데이트 안하면 될듯)