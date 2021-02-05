package ssw;
import java.io.*;
import java.util.*;

public class d4_1861_정사각형방 {
	// 상0 하1 좌2 우3
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int move(int[][] map, int x, int y, int cnt) { // 현재 좌표와 이동횟수
		int result = cnt;
		
		// 상하좌우 확인 
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 이동 가능하면 (map[nx,ny]가 1만큼 크면) 재귀 또 들어감
			if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length && map[nx][ny] == map[x][y] + 1) {
				result = move(map, nx, ny, ++cnt);
			}
		}

		// 탐색 다 해봤는데 갈 곳이 없다.. cnt 리턴
		return result;
	}

	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input_d4_1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];

			// 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int maxMove = 0;
			int val = 9999;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int tmp = move(map, i, j, 1);
					if (maxMove < tmp) {
						maxMove = tmp;
						val = map[i][j];
					}
					// 이동 값이 같은 경우 사각형 안의 숫자가 작은걸로 저장
					else if (maxMove == tmp) {
						val = val < map[i][j] ? val : map[i][j];
					}
				}
			}

			pw.println("#" + tc + " " + val + " " + maxMove);
		}
		pw.close();
		br.close();
	}
}

// N*N map
// 각 방에는 1 ~ N^2 수가 적혀있음
// 어떤 방에서 상하좌우 이동 가능.. 단, 현재 방의 수보다 이동하려는 방의 수가 1만큼 커야됨
// 어떤 수가 적힌 방에 있어야 가장 많은 방을 이동할 수 있는가..?