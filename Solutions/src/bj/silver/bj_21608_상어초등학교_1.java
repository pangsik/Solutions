package bj.silver;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.19
 * 풀이 시간 : 못품
 * 코멘트
 * 문제 이해 제대로 못함.. 2트에서 품
 */

public class bj_21608_상어초등학교_1 {
	static int N;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int student = Integer.parseInt(st.nextToken());
			int[] loveList = new int[4];
			for (int j = 0; j < 4; j++) {
				loveList[j] = Integer.parseInt(st.nextToken());
			}
			setSeat(student, loveList);
		}
		
		br.close();
	}
	
	private static void setSeat(int student, int[] loveList) {
		// 1.빈 칸 중 좋아하는 학생이 인접한 칸이 가장 많은 칸으로
		ArrayList<int[]> list1 = step1(loveList);
		
		// 만약 리턴 값이 비어있거나 사이즈가 1보다 크다면 2번으로 이동 (단 하나의 값이 리턴됐을 때만 저장)
		if (list1.size() == 1) {
			int[] cur = list1.get(0);
			map[cur[0]][cur[1]] = student;
			return;
		}
		
		// 2.1을 만족하는 칸이 여러 개면, 인접한 칸 중 빈 칸이 가장 많은 칸으로
		ArrayList<int[]> list2 = step2();
		
		// 만약 리턴 값 사이즈가 1보다 크면 3번으로
		if (list2.size() == 1) {
			int[] cur = list2.get(0);
			map[cur[0]][cur[1]] = student;
			return;
		}
		
		// 3.2를 만족하는 칸도 여러 개면, 행 번호가 가장 작은 칸으로, 그것도 여러 개면 열 번호가 가장 작은 칸으로
		ArrayList<int[]> list3 = new ArrayList<>();
		int minRow = Integer.MAX_VALUE;
		for (int[] cur : list2) {
			if (cur[0] < minRow) {
				minRow = cur[0];
				list3.clear();
				list3.add(cur);
			}
			else if (cur[0] == minRow) {
				list3.add(cur);
			}
		}
		
		if (list3.size() == 1) {
			int[] cur = list3.get(0);
			map[cur[0]][cur[1]] = student;
			return;
		}
		
		int minCol = Integer.MAX_VALUE;
		int minX = -1;
		int minY = -1;
		// 열 번호..
		for (int[] cur : list3) {
			if (cur[1] < minCol) {
				minCol = cur[1];
				minX = cur[0];
				minY = cur[1];
			}
		}
		
		map[minX][minY] = student;
	}

	private static ArrayList<int[]> step2() {
		ArrayList<int[]> list = new ArrayList<>();

		// 마찬가지로 모든 빈 칸을 돌며 인접한 빈 칸 수를 세어 좌표와 함께 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0)
					continue;
				
				int cnt = getEmptyCnt(i, j);
				
				// 비어있으면 그냥 삽입
				if (list.isEmpty()) {
					list.add(new int[] { i, j, cnt });
					continue;
				}
				
				// 단, 인접한 좋아하는 학생 수 MAX값 갱신
				if (list.get(0)[2] < cnt) {
					list.clear();
					list.add(new int[] { i, j, cnt });
				} 
				else if (list.get(0)[2] == cnt) {
					list.add(new int[] { i, j, cnt });
				}
			}
		}

		// 이것도 마찬가지로 어레리에 담아 리턴
		return list;
	}

	private static ArrayList<int[]> step1(int[] loveList) {
		ArrayList<int[]> list = new ArrayList<>();
		
		// 모든 빈 칸 돌아보며 좋아하는 학생이 인접했다면, 칸의 좌표와 인접 학생 수를 저장
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) 
					continue;
				
				int cnt = getLoveCnt(i, j, loveList);
				
				// 비어있으면 그냥 삽입
				if (list.isEmpty()) {
					list.add(new int[] { i, j, cnt });
					continue;
				}
				
				// 단, 인접한 좋아하는 학생 수 MAX값 갱신
				if (list.get(0)[2] < cnt) {
					list.clear();
					list.add(new int[] { i, j, cnt });
				} 
				else if (list.get(0)[2] == cnt) {
					list.add(new int[] { i, j, cnt });
				}
			}
		}
		
		// 어레리에 담아 리턴
		return list;
	}
	
	private static int getEmptyCnt(int x, int y) {
		int cnt = 0;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (!check(nx, ny))
				continue;
			
			if (map[nx][ny] == 0)
				cnt++;
		}
		
		return cnt;
	}

	private static int getLoveCnt(int x, int y, int[] loveList) {
		int cnt = 0;
		
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];
			
			if (!check(nx, ny))
				continue;
			
			if (isContain(map[nx][ny], loveList)) {
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static boolean isContain(int n, int[] list) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == n)
				return true;
		}
		
		return false;
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			return false;
		return true;
	}
}

// 교실 N * N
// 학생수 N^2
// 학생 번호 1 ~ N^2
// 교실 칸 시작은 1,1 끝은 N,N (-1처리 해주기)
// |r1-r2| + |c1-c2| = 1 을 만족할 때 (r1,c1)과 (r2,c2)는 인접하다. => 4방향 인접칸 취급
// 1.빈 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정함
// 2.1을 만족하는 칸이 여러 개면, 인접한 칸 중 빈 칸이 가장 많은 칸으로 자리를 정함
// 3.2를 만족하는 칸도 여러 개인 경우, 행 번호가 가장 작은 칸으로, 그것도 여러 개면 열 번호가 가장 작은 칸으로
