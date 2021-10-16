package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.16
 * 풀이 시간 : 70분
 * 코멘트 : 대각선 4방 탐색이라 뭔가 낯선 느낌이라 쓸데 없이 헤맨 느낌..
 * 		   확실한거 아니면 이상하게 최적화 하려고 하지 말고 시키는 대로나 하자
 * 		  visited 없어도 됨! 어차피 방문한 곳은 먹은 디저트 체크에서 걸림
 */

public class Solution_sw_2105_디저트카페_1 {
	static int N, startX, startY, answer;
	static int[] di = { 1, 1, -1, -1 }; // 우하, 좌하, 좌상, 우상
	static int[] dj = { 1, -1, -1, 1 };
	static int[][] map;
//	static boolean[][] visited;
	static ArrayDeque<Integer> dessertList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = -1;
			Solution();			
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// (0,0), (0,N-1), (N-1,0), (N-1,N-1)은 패스
				if (i == 0) {
					if (j == 0 || j == N - 1)
						continue;
				}
				if (i == N - 1) {
					if (j == 0 || j == N - 1) 
						continue;
				}
				
				startX = i;
				startY = j;
				dessertList = new ArrayDeque<Integer>();
				dessertList.add(map[i][j]);
//				visited = new boolean[N][N];
//				visited[i][j] = true;
				dfs(i, j, 1, 0);
			}
		}
	}

	private static void dfs(int x, int y, int len, int dir) {		
		// 그냥 전진하는 경우
		int nx = x + di[dir];
		int ny = y + dj[dir];
		
		// 갈 수 있으면 전진
		if (check(nx, ny)) {
			dessertList.add(map[nx][ny]);
//			visited[nx][ny] = true;
			
			dfs(nx, ny, len + 1, dir);
			
			dessertList.remove(map[nx][ny]);
//			visited[nx][ny] = false;
		}
		
		// 만약 더 이상 방향 전환이 안되면 스톱. 단,마지막으로 가려는 곳이 출발점이라면 answer 갱신
		if (++dir == 4) {
			if (nx == startX && ny == startY)
				answer = Math.max(answer, len);
			return;
		}
		
		// 방향 전환해서 전진
		nx = x + di[dir];
		ny = y + dj[dir];
		
		if (check(nx, ny)) {
			dessertList.add(map[nx][ny]);
//			visited[nx][ny] = true;
			
			dfs(nx, ny, len + 1, dir);
			
			dessertList.remove(map[nx][ny]);
//			visited[nx][ny] = false;
		}
		
		// 방향 전환하고도 전진 안되면 스탑.. 단, dir이 3으로 마지막 방향이고 가려는 곳이 출발점이라면 answer 갱신
//		if (dir == 3 && nx == startX && ny == startY) {
//			answer = Math.max(answer, len);
//			return;
//		}
		
		// 어차피 원래 지점으로 돌아오려면 dir이 3일 수 밖에 없기 때문에 dir == 3 조건은 빼도 되더라..
		if (nx == startX && ny == startY) {
			answer = Math.max(answer, len);
			return;
		}
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || dessertList.contains(map[nx][ny])) {
			return false;
		}
//		if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || dessertList.contains(map[nx][ny])) {
//			return false;
//		}
		
		return true;
	}
}

// N*N
// 길은 대각선으로 이어져있음 (대각선 4방)
// 어느 카페에서 시작하여 대각선 방향으로 움직이고 다시 출발했던 카페로 돌아와야 함
// 맵 밖 벗어나는 것도 안됨
// 같은 숫자의 디저트를 파는 카페가 있으면 안됨
// 방문한 곳 다시 가면 안됨
// 디저트를 가장 많이 먹을 수 있는 경로를 찾고 그 때 디저트 수 출력 (없는 경우 -1 출력)
// => 가장 긴 경로 길이를 출력하라

// 모든 지점에서 출발
// dfs (좌표, 현재까지len, 방향)
// 현재 방향, 여태 진행한 방향 정보 (무조건 0부터 시작해서 1씩 더해가며 3일 때 원래 자리로 돌아오면 스탑?)
// 먹은 디저트 저장해서 이미 먹은거 있는지 체크
// visited 배열로 방문 관리










