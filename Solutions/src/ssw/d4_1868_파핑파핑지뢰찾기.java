package ssw;

import java.io.*;
import java.util.*;

public class d4_1868_파핑파핑지뢰찾기 {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int N, cnt, total;
	static char[][] map;
	static boolean[][] visited;

	// 상 우상 우 우하 하 좌하 좌 좌상 (시계방향 8방 탐색)
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j);
				}
			}
			
			cnt = 0;
			total = 0;
			solution();

			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	static void solution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '.') {
					setNum(new Pos(i, j));
					total++;
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '0') {
					bfs(new Pos(i, j));
				}
			}
		}
		
		// 그 외
		cnt += total;
	}
	
	static void bfs(Pos start) {
		if (visited[start.x][start.y]) return;
		
		cnt++;
		total--;
		Queue<Pos> q = new LinkedList<>();
		q.offer(start);
		visited[start.x][start.y] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (map[cur.x][cur.y] != '0') continue;
			
			for (int d = 0; d < 8; d++) {
				int nx = cur.x + di[d];
				int ny = cur.y + dj[d];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != '*' && !visited[nx][ny]) {
					visited[nx][ny] = true;
					total--;
					q.offer(new Pos(nx, ny));
				}
			}
		}
	}
	
	static void setNum(Pos cur) {
		int cnt = 0;
		for (int d = 0; d < 8; d++) {
			int nx = cur.x + di[d];
			int ny = cur.y + dj[d];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == '*')
				cnt++;
		}
		
		map[cur.x][cur.y] = (char) (cnt + '0');
	}
}

// N*N
// 지뢰칸 클릭 시 게임오버
// 지뢰없는칸 클릭 시 해당 칸 기준 8방향으로 지뢰가 몇 개 있는지 그 칸에 표시
// 만약 그 칸이 0이라면 주변 8개 칸도 지뢰가 아니기 때문에 클릭한 것 처럼 주변 지뢰 개수 표시 (또 0이면 또 똑같이 반복)
// * : 지뢰
// . : 지뢰 x
// c : 클릭한 지뢰 없는 칸

// 최소 몇 번의 클릭을 해야 모든 칸들의 숫자들이 표시되는가?
// 0인 칸 먼저..

// 일단 모든 칸 숫자 다 구해놓고 0인 칸들 따로 저장 후 0칸들 먼저 돌린 후 남은 애들 갯수 더해주면 그게 최소?
