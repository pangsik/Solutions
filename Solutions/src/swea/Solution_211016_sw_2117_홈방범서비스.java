package swea;

import java.io.*;
import java.util.*;

/*
 * 풀이 시간 : 50분
 * 코멘트 : 처음에 문제를 잘못 이해해서 K도 입력으로 주는줄 알았음.. 문제 꼼꼼히 읽고 의도 잘 파악하기
 *        어쨌든 최적화 갖다 버리고 시키는대로만 하니 풀리긴 함.. 복잡해도 집중력 잃지 말고 조건 하나하나 잘 체크할 것
 */

public class Solution_211016_sw_2117_홈방범서비스 {
	static int N, M, homeCnt, answer;
	static int[] di = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			homeCnt = 0;
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						homeCnt++;
				}
			}
			
			Solution();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution() {
		answer = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				setK(i, j);
			}
		}
	}

	private static void setK(int x, int y) {
		int K = 1;
		
		while (true) {
			// 얻을 수 있는 최대 수익이 음수면 스톱
			int profit = (homeCnt * M) - (K * K + (K-1) * (K-1));
			if (profit < 0) {
				break;
			}
			
			int cnt = bfs(x, y, K);
			int res = (cnt * M) - (K * K + (K-1) * (K-1));
			if (res >= 0) {
				answer = Math.max(answer, cnt);
			}
			
			K++;
		}
	}

	private static int bfs(int x, int y, int K) {
		ArrayDeque<int[]> dq = new ArrayDeque<int[]>();
		visited = new boolean[N][N];
		
		dq.offer(new int[] { x, y });
		visited[x][y] = true;
		
		int cnt = 0;
		
		int lv = 0;
		while (!dq.isEmpty()) {
			if (lv++ == K)
				break;
			
			int size = dq.size();
			
			for (int i = 0; i < size; i++) {
				int[] cur = dq.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				if (map[curX][curY] == 1) {
					cnt++;
				}
				
				for (int d = 0; d < 4; d++) {
					int nx = curX + di[d];
					int ny = curY + dj[d];
					
					if (!check(nx, ny))
						continue;
					
					visited[nx][ny] = true;
					dq.offer(new int[] { nx, ny });
				}
			}
		}
		
		return cnt;
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
			return false;
		
		return true;
	}
}


// N*N
// 방범 서비스는 마름모 모양으로 제공됨
// 운영 비용 = K * K + (K-1) * (K-1)
// 고객은 M 만큼의 비용을 지불
// 맵 주어졌을 때 가장 많은 집을 포함시키는 경우 찾고, 수익 = M * (포함된 가구 수)
// (수익 - 운영 비용)이 가장 큰 경우를 찾고, 그 때의 가구 수를 출력


// 모든 위치에서 K를 증가시켜가며 깊이 K만큼 bfs 돌리기
// K를 몇까지 증가시켜야하나..?
// (모든 가구수 * M) - (K일때 운영비용)가 음수이면 스톱
// 답은 최소 1은 보장되니 answer은 1로 초기화

