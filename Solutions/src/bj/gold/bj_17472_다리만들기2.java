package bj.gold;

import java.util.*;
import java.io.*;

public class bj_17472_다리만들기2 {
	static int N, M;
	static int[][] map;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static boolean[][] visited1;

	static int[][] adjMatrix;
	static int[] minEdge;
	static boolean[] visited2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(solution());

		br.close();
	}

	static int solution() {
		// 섬마다 번호 붙여주기
		visited1 = new boolean[N][M];
		int num = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited1[i][j]) {
					num++;
					dfs(i, j, num);
				}
			}
		}

		// 모든 섬 좌표 확인해서 4방향으로 뻗어보기 (맵 수정해줄 필요는 없음.. 겹칠 수 있기 때문)
		adjMatrix = new int[num][num];
		for (int i = 0; i < num; i++)
			Arrays.fill(adjMatrix[i], Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					setBridge(i, j);
				}
			}
		}
		
		visited2 = new boolean[num];
		minEdge = new int[num];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		return prim(num);

	}

	// 섬 번호 붙여주기
	static void dfs(int x, int y, int num) {
		visited1[x][y] = true;
		map[x][y] = num;
		for (int d = 0; d < 4; d++) {
			int nx = x + di[d];
			int ny = y + dj[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 1 && !visited1[nx][ny]) {
				dfs(nx, ny, num);
			}
		}
	}

	// 다리 놓을 수 있는 최소 거리로 adjMatrix 세팅
	static void setBridge(int x, int y) {
		for (int d = 0; d < 4; d++) {

			int[] tmp = getDist(x, y, d);
			int from = map[x][y] - 1; // 출발 섬 번호
			int to = tmp[1] - 1; // 도착 섬 번호
			// 다리 길이가 2 이상이어야 하고, 다른 섬에 도착한 경우만.. (ex..1번섬 출발 1번섬 도착은 패스)
			if (tmp[0] >= 2 && from != to) {
				// 양방향 그래프.. 더 짧은걸로 넣어주기
				if (adjMatrix[from][to] > tmp[0]) {
					adjMatrix[from][to] = tmp[0];
					adjMatrix[to][from] = tmp[0];
				}
			}
		}
	}

	// 주어진 섬의 특정 좌표에서 4방향으로 다리 쭉쭉 놔보기.. 
	static int[] getDist(int x, int y, int d) {
		int dist = 0;

		while (true) {
			int nx = x + di[d];
			int ny = y + dj[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				x = nx;
				y = ny;
				// 섬에 도착하면 반복문 종료 
				if (map[x][y] != 0) {
					break;
				}
				dist++;
			}
			
			// 맵 밖에 나갈 경우(섬 만나지 못하는 경우) dist 0으로 리턴
			else {
				dist = 0;
				break;
			}
		}
		
		// 거리와 도착한 섬의 번호 리턴
		return new int[] { dist, map[x][y] };
	}

	static int prim(int size) {
		int result = 0;
		minEdge[0] = 0;

		for (int c = 0; c < size; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = 0;

			for (int i = 0; i < size; i++) {
				if (!visited2[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			if (min == Integer.MAX_VALUE) return -1;
			
			result += min;
			visited2[minVertex] = true;

			for (int i = 0; i < size; i++) {
				if (!visited2[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		return result;
	}
}

// 섬으로 이루어진 나라.. 모든 섬을 다리로 연결
// 맵 N * M
// 상하좌우 붙어있는 한 덩어리 섬
// 다리는 바다에만 건설 가능
// 다리의 길이는 최소 2
// 다리는 무조건 일직선으로만 연결 가능
// 가로 다리는 양 끝 가로방향이 섬에 닿아야 연결된 것으로 인정 (세로도 마찬가지)
// 다리 교차 가능.. 단, 교차하는 경우도 다리 길이 똑같이 더해줘야함 (4 길이짜리 다리 두 개가 교차하면 총 길이는 4 + 4 = 8)
// 모든 섬을 연결하는 다리 길이 최소값 출력.. 불가능하면 -1 출력

// 일단 모든 섬들 섬 번호로 바꿔주기
// 그냥 좌표 쭉 돌면서 섬 만나면 그 좌표에서 4방향으로 다리 뻗어보기 (가다가 다른 섬 만나면 그 길이와 어느 섬끼리 이어지는지 저장.. 아무것도 안만나면 dist 0으로 리턴)
// 만약 섬을 만나게 되면 현재까지 두 섬 사이의 최소 다리길이 비교해서 더 작은값으로 인접행렬에 저장
