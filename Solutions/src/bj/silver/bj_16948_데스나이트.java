package bj.silver;

import java.util.*;
import java.io.*;

public class bj_16948_데스나이트 {
	static int N, r1, c1, r2, c2;
	static int[] di = { -2, -2, 0, 0, 2, 2 };
	static int[] dj = { -1, 1, -2, 2, -1, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		System.out.println(bfs());

		br.close();
	}

	static int bfs() {
		visited = new boolean[N][N];

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { r1, c1 });
		visited[r1][c1] = true;

		int depth = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			
			while (size-- > 0) {
				int[] cur = q.poll();
				
				if (cur[0] == r2 && cur[1] == c2) {
					return depth;
				}
				
				for (int d = 0; d < 6; d++) {
					int nx = cur[0] + di[d];
					int ny = cur[1] + dj[d];

					if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
						q.offer(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			}
			depth++;
		}

		return -1;
	}
}

// (r,c)
// (r-2,c-1)
// (r-2,c+1)
// (r,c-2)
// (r,c+2)
// (r+2,c-1)
// (r+2,c+1)