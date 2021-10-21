package swea;

import java.util.*;
import java.io.*;

/*
 * @data : 21.10.17
 * 풀이 시간 : 90분
 * 코멘트
 * 이정도 시뮬레이션이 너무 오랜만이라 많이 헤맨 듯
 * 특히 배열 크기를 W,H로 줘서 너무너무 헷갈렸다. R,C로 변수명 바꿔 사용하니 한결 나은 듯
 * 그리고 i,j 순서대로 순회 말고 j,i로 바꿔서 순회하는게 헷갈려서 삽질 많이 했다. 집중!
 */

public class Solution_sw_5656_벽돌깨기 {
	static int N, C, R, destroyed, answer;
	static int[] di = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dj = { 0, 1, 0, -1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[R][C];
			int total = 0;
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < C; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0)
						total++;
				}
			}
			
			answer = Integer.MAX_VALUE;
			Solution(0, total, map);
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution(int cnt, int total, int[][] map) {
		// 다 쏘면 스톱
		if (cnt == N) {
			answer = Math.min(answer, total);
			return;
		}
		
		// 다 부쉈어도 스톱
		if (total == 0) {
			answer = 0;
			return;
		}
		
		// 이미 최소값이 0이라면 스톱
		if (answer == 0) {
			return;
		}
		
		int[][] copyMap = new int[R][C];
		for (int j = 0; j < C; j++) {
			for (int i = 0; i < R; i++) {
				// 제일 윗칸 확인하면서 벽돌이 존재하면 발사해보기
				if (map[i][j] > 0) {
					deepCopy(map, copyMap);
					destroyed = 0; // 부순 벽돌 카운트
					destroy(copyMap, i, j);
					drop(copyMap);
					Solution(cnt + 1, total - destroyed, copyMap);
					break;
				}
			}
		}
	}
	
	private static void destroy(int[][] map, int x, int y) {
		int block = map[x][y];
		map[x][y] = 0;
		destroyed++;
		
		for (int d = 0; d < 4; d++) {
			int nx = x;
			int ny = y;
			for (int n = 0; n < block - 1; n++) {
				nx += di[d];
				ny += dj[d];
				
				if (!check(nx, ny, map))
					continue;
				
				destroy(map, nx, ny);
			}
		}
	}
	
	private static boolean check(int nx, int ny, int[][] map) {
		// 범위 밖으로 나가거나 0이면 굳이 재귀 안탐
		if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == 0) {
			return false;
		}
		
		return true;
	}

	private static void drop(int[][] map) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		// 큐에 싹 담아서 제일 아래부터 순서대로 다시 넣어주기
		for (int j = 0; j < C; j++) {
			for (int i = R - 1; i >= 0; i--) {
				if (map[i][j] > 0) {
					dq.add(map[i][j]);
					map[i][j] = 0;
				}
			}
			int i = R - 1;
			while (!dq.isEmpty()) {
				map[i][j] = dq.poll();
				i--;
			}
		}
	}

	private static void deepCopy(int[][] map, int[][] copyMap) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
}

// 구슬 N번 발사
// W*H
// 구슬은 좌, 우로만 움직일 수 있고 맨 위의 벽돌만 깰 수 있음
// 벽돌은 1~9, 구슬 명중 시 해당 벽돌 기준 (벽돌 숫자 -1)칸 만큼 상하좌우로 함께 제거됨
// 함께 제거된 벽돌들 또한 연쇄 폭발
// 제거 후 밑으로 정렬
// 구슬을 N번 발사하여 최대한 많은 벽돌을 제거했을 때, 남은 벽돌의 개수 출력

// 브루트포스
// 재귀로 모든 순서에 모든 위치에서 공 쏴보기
// 재귀 타고 들어갈 때 깊은 복사로 들어갈 것
// 종료 조건은 구슬 발사 횟수 == N, 남은 벽돌 수 == 0
// 깊은 복사, 벽돌 부수기(재귀), 벽돌 내리기 메서드 작성 必







