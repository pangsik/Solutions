package RTS;

import java.io.*;
import java.util.*;

public class codetree_예술성 {
	static class Group {
		int x, y, cnt, num, groupNum;
		public Group(int x, int y, int cnt, int num, int groupNum) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.num = num;
			this.groupNum = groupNum;
		}
	}
	static int N, scoreSum;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map, groupMap;
	static ArrayList<Group> groups;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Solution();
		System.out.println(scoreSum);
		
		br.close();
	}
	
	private static void Solution() {
		thisTimeScore();
		
		for (int i = 0; i < 3; i++) {
			rotate();
			thisTimeScore();
		}
	}

	private static void thisTimeScore() {
		// 그룹 구하기.. 저장 어떻게?
		// 칸 수, 숫자 값, 시작지점 저장하기
		setGroups();
		
		scoreSum += getScore();
		
		// 그룹들로 2개씩 고르는 조합 구하기
//		for (int i = 0; i < groups.size() - 1; i++) {
//			for (int j = i + 1; j < groups.size(); j++) {
//				scoreSum += getScore(groups.get(i), groups.get(j));
//			}
//		}
		// 2개 고르면 두 그룹 맞닿은 변의 수 구하고 공식대로 점수 구해서 더해주기
		// 맞닿은 변의 수 어떻게 구할래?
		// 그룹 번호로 이루어진 2차원 배열 하나 더 만들어서 저장해두기
		// A 그룹 bfs 돌면서 옆 칸이 B 그룹이라면 +1 해주기 
	}
		
//	private static int getScore(Group A, Group B) {
//		int besideCnt = getBesideCnt(A, B);
//		int score = (A.cnt + B.cnt) * A.num * B.num * besideCnt;
//		
//		return score;
//	}

	private static int getScore() {
		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int d = 0; d < 4; d++) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[i][j] == map[nx][ny])
						continue;
					
					Group A = groups.get(groupMap[i][j] - 1);
					Group B = groups.get(groupMap[nx][ny] - 1);
					
					score += (A.cnt + B.cnt) * A.num * B.num;
				}
			}
		}
		
		return score / 2;
	}

	private static int getBesideCnt(Group A, Group B) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		dq.add(new int[] { A.x, A.y });
		visited[A.x][A.y] = true;
		
		int besideCnt = 0;
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) 
					continue;
				
				if (groupMap[nx][ny] == B.groupNum)
					besideCnt++;
				
				if (groupMap[nx][ny] != A.groupNum)
					continue;
				
				dq.add(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}
		
		return besideCnt;
	}

	private static void setGroups() {
		groups = new ArrayList<>();
		groupMap = new int[N][N];
		
		int groupNum = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (groupMap[i][j] > 0)
					continue;
				bfs(i, j, groupNum++);
			}
		}
	}

	private static void bfs(int x, int y, int groupNum) {
		int num = map[x][y];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		dq.add(new int[] { x, y });
		visited[x][y] = true;
		groupMap[x][y] = groupNum;
		
		int cnt = 0;
		
		while (!dq.isEmpty()) {
			cnt++;
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != num || visited[nx][ny])
					continue;
				
				dq.add(new int[] { nx, ny });
				visited[nx][ny] = true;
				groupMap[nx][ny] = groupNum;
			}
		}
		
		groups.add(new Group(x, y, cnt, num, groupNum));
	}

	private static void rotate() {
		int mid = N / 2;
		
		// 십자가 반시계 90도
		rotateCross(mid);
		
		// 그 외 각각 시계 90도 회전
		rotateSquare(0, 0);
		rotateSquare(0, mid + 1);
		rotateSquare(mid + 1, 0);
		rotateSquare(mid + 1, mid + 1);
	}

	private static void rotateCross(int mid) {
		for (int i = 0; i < mid; i++) {
			int tmp = map[i][mid];
			map[i][mid] = map[mid][N-1-i];
			map[mid][N-1-i] = map[N-1-i][mid];
			map[N-1-i][mid] = map[mid][i];
			map[mid][i] = tmp;
		}
	}

	private static void rotateSquare(int startX, int startY) {
		int mid = N / 2;
		int endX = startX + mid;
		int endY = startY + mid;
		
		int[][] tmpMap = new int[mid][mid];
		
		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				tmpMap[j - startY][mid - 1 - (i - startX)] = map[i][j];
			}
		}

		for (int i = startX; i < endX; i++) {
			for (int j = startY; j < endY; j++) {
				map[i][j] = tmpMap[i - startX][j - startY];
			}
		}
	}
}

// 동일한 숫자가 상하좌우로 인접한 경우를 동일한 그룹으로 봄
// 그룹 A, B의 조화로움 = (A 칸 수 + B 칸 수) * A 숫자 값 * B 숫자 값 * AB 맞닿은 변의 수
// 
// 회전
// 중앙을 기준으로 십자가 모양과 그 외 부분으로 나눔
// 십자가는 통쨰로 반시계 90도 회전
// 그 외 정사각형은 개별적으로 시계방향 90도 회전

// 초기, 1회전, 2회전, 3회전 예술 점수 합 출력




















