package bj.gold;

import java.util.*;
import java.io.*;

public class bj_15685_드래곤커브 {
	static int[] di = { 0, -1, 0, 1 }; // 우 상 좌 하
	static int[] dj = { 1, 0, -1, 0 };
	static int[][] map = new int[101][101];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
				
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			setDragonCurve(y, x, d, g);
//			System.out.println("---------");
//			for (int n = 0; n < 6; n++) {
//				for (int m = 0; m < 6; m++) {
//					System.out.print(map[n][m] + " ");
//				}
//				System.out.println();
//			}
		}
		
		System.out.println(getSquareCount());

		br.close();
	}

	private static void setDragonCurve(int x, int y, int d, int g) {
		map[x][y] = 1;
		
		int endX = x + di[d];
		int endY = y + dj[d];
		
		map[endX][endY] = 1;
		
		ArrayList<Integer> history = new ArrayList<Integer>();
		history.add(d);
		
		nextDragonCurve(endX, endY, history, 1, g);
	}

	private static void nextDragonCurve(int x, int y, ArrayList<Integer> history, int cur, int g) {
		// 목표 세대 도달시 리턴
		if (cur > g)
			return;

//		System.out.println(history);
		
		ArrayList<Integer> newHistory = new ArrayList<Integer>();
		// 배열에 1씩 더한 값을 역순으로 뽑아서 방향 이동
		for (int i = history.size() - 1; i >= 0; i--) {
			int d = history.get(i) + 1;
			d = d > 3 ? 0 : d;
			
			x += di[d];
			y += dj[d];
			
			map[x][y] = 1;
			newHistory.add(d);
		}
		
		if (cur == 1) {
			nextDragonCurve(x, y, newHistory, cur + 1, g);
			return;
		}
		
		// 배열 순서대로 방향 이동
		for (int i = 0; i < history.size(); i++) {
			int d = history.get(i);
			
			x += di[d];
			y += dj[d];
			
			map[x][y] = 1;
			newHistory.add(d);
		}
		
		nextDragonCurve(x, y, newHistory, cur + 1, g);
	}

	private static int getSquareCount() {
		int cnt = 0;
		
		for (int i = 0; i < map.length - 1; i++) {
			for (int j = 0; j < map[0].length - 1; j++) {
				if (map[i][j] == 1 && map[i+1][j] == 1 && map[i][j+1] == 1 && map[i+1][j+1] == 1) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
}

// 2.드래곤 커브 그리기
// 이전 세대의 이동 방향을 방위 인덱스로 기록하여 넘겨주기
// 0세대는 직접 그어주고 1세대부터 시작

// 배열에 1씩 더한 값을 역순으로 뽑아서 방향 이동
// 배열 순서대로 방향 이동
// 이동할 때 

// 마지막에 끝점을 가지고 다음 세대 시작점으로 넘겨주기
// 0세대 : 3

// 1세대 : 0
// 2세대 : 1 | 0
// 3세대 : 1 2 | 1 0
// 4세대 : 1 2 3 2 | 1 2 1 0
// 2 7 3 4

// 3.정사각형 개수 구하기
// 그냥 네 칸 붙어있는거 다 더하기

// 이전세대 드래곤 커브를 끝 점을 기준으로 시계 방향 90도 회전 후 이전 세대 커브의 끝 점에 붙이기
// 드래곤 커브를 모두 구한 후 1x1 크기 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구하라

// N : 드래곤 커브의 개수
// x, y : 드래곤 커브 시작 점
// d : 시작 방향 (우0 상1 좌2 하3)
// g : 세대







