package RTS;

import java.io.*;
import java.util.*;

public class codetree_1 {
	static class Pos {
		int x, y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int N, minDist;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static Pos start, end;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				int val = 0;
				if (input.charAt(j) == '#')
					val = -1;
				
				else if (input.charAt(j) == '.')
					val = 0;
				
				else if (input.charAt(j) == 'S') {
					start = new Pos(i, j);
					val = 0;
				}
					
				else if (input.charAt(j) == 'E') {
					end = new Pos(i, j);
					val = 0;
				}
				else
					val = input.charAt(j) - '0';
				
				map[i][j] = val;
			}
		}
		
		minDist = Integer.MAX_VALUE;
		pickPenny(0, 0, 0, start);
		
		System.out.println(minDist == Integer.MAX_VALUE ? -1 : minDist);
		
		br.close();
	}
	
	private static void pickPenny(int cnt, int distSum, int prevPenny, Pos s) {
		// bfs
		ArrayDeque<Pos> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		dq.offer(s);
		visited[s.x][s.y] = true;
		
		int dist = 0;
		while (!dq.isEmpty()) {
			if (distSum + dist >= minDist)
				return;
			
			int size = dq.size();
			for (int i = 0; i < size; i++) {
				Pos cur = dq.poll();
				
				if (cnt < 3) {
					if (map[cur.x][cur.y] > prevPenny) {
						pickPenny(cnt + 1, distSum + dist, map[cur.x][cur.y], cur);
					}
				}
				
				else {
					if (cur.x == end.x && cur.y == end.y) {
						minDist = Math.min(minDist, distSum + dist);
						return;
					}
				}
				
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + di[d];
					int ny = cur.y + dj[d];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == -1 || visited[nx][ny])
						continue;
					
					dq.offer(new Pos(nx, ny));
					visited[nx][ny] = true;
				}
			}
			
			dist++;
		}
	}
}

// 동전 챙기기
// N*N 격자는 아래로 이루어져 있음
// # : 벽 => -1
// . : 빈 공간 => 0
// S : 시작점 => 따로 관리
// E : 도착점 => 따로 관리
// 1~9 : 동전 => 1~9

// 벽으로 못가고 숫자는 해당 위치에 동전이 있음
// 동전은 그 위치에 도달해야 얻을 수 있고 한 칸에 하나씩만 존재
// 시작점 -> 최소 3개 동전 수집 -> 도착점 도달
// 수집한 동전 수가 3개 초과해도 되지만 동전 수집은 꼭 동전 숫자가 증가하는 순서대로 수집해야함
// 해당 위치 지나가면서 동전 수집 안해도 되고 같은 위치 2번 이상 지나가는 것도 괜찮음
// 최소 이동 횟수 구하기

// 시작점, 도착점은 하나씩만 주어짐
// 같은 숫자 동전은 없음

// 아 직전에 주운 동전이 뭔지만 알아도 그전에 주운 동전은 줍지 않겠다..! 더 큰 동전만 주워야하니까
// bfs 메서드는,, (현재까지 숫자 몇 개 찾았는지 cnt, 현재까지 거리 dist, 직전에 주운 동전, 시작 위치)
// 시작점 => bfs (depth 체크하면서)
// 숫자 찾으면 직전 동전이랑 비교해서 더 크면 재귀타고 들어가기 (단, cnt가 3이라면 무시하고 도착점 찾으러 감) 
// (cnt+1, depth 가지고 거리 갱신, 주운 동전, 현재 위치 매개변수로)
// (들어갔다고 return이 아니라 걔는 드가고 나머진 계속)
// 백트래킹으로는,,, bfs depth + 1 될 때 마다 dist + depth가 minDist보다 크거나 같으면 스톱



