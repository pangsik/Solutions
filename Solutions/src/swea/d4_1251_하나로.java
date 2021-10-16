package swea;

import java.io.*;
import java.util.*;

public class d4_1251_하나로 {
	static int N;
	static double[][] adjMatrix;

	public static void main(String[] args) throws Exception {
//		long beforeTime = System.currentTimeMillis();

		System.setIn(new FileInputStream("res/input_d4_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			long[][] islands = new long[N][2];

			// 섬 좌표정보 입력
			for (int j = 0; j < 2; j++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int i = 0; i < N; i++) {
					islands[i][j] = Long.parseLong(st.nextToken());
				}
			}
			// 세율
			double E = Double.parseDouble(br.readLine());

			adjMatrix = new double[N][N];

			// 그래프 구성 (가중치 계산 및 대입)
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double L = Math.pow(islands[i][0] - islands[j][0], 2) + Math.pow(islands[i][1] - islands[j][1], 2);
					adjMatrix[i][j] = E * L;
					adjMatrix[j][i] = E * L;
				}
			}

			sb.append('#').append(tc).append(' ').append(prim()).append('\n');
			// sb.append('#').append(tc).append(' ').append(primPQ()).append('\n');
		}

		System.out.println(sb);

		br.close();

//		long afterTime = System.currentTimeMillis();
//		System.out.println("실행시간(ms) : " + (afterTime - beforeTime));
	}

	static long prim() {
		boolean[] visited = new boolean[N];
		double[] minEdge = new double[N];
		Arrays.fill(minEdge, Long.MAX_VALUE);

		minEdge[0] = 0;
		double result = 0;

		for (int c = 0; c < N; c++) {
			double min = Long.MAX_VALUE;
			int minVertex = 0;
			// 신장트리에 연결되지 않은 정점 중 minEdge비용이 최소인 정점
			for (int i = 0; i < N; i++) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			result += min; // 해당 정점의 간선비용 누적
			visited[minVertex] = true;

			for (int i = 0; i < N; i++) {
				if (!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}

		return Math.round(result);
	}

	/************************************************************************************/

	static long primPQ() {
		// 인덱스 0 : 노드 번호, 1 : 가중치
		PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> {
			return Double.compare(o1[1], o2[1]);
		});
		boolean[] visited = new boolean[N];
		double[] minEdge = new double[N];
		Arrays.fill(minEdge, Long.MAX_VALUE);

		minEdge[0] = 0;
		double result = 0;

		pq.offer(new double[] { 0, 0 });

		while (!pq.isEmpty()) {
			double[] cur = pq.poll();
			if (visited[(int) cur[0]])
				continue;

			visited[(int) cur[0]] = true;
			result += cur[1];

			for (int i = 0; i < N; i++) {
				if (i == (int) cur[0])
					continue;

				if (!visited[i] && minEdge[i] > adjMatrix[(int) cur[0]][i]) {
					minEdge[i] = adjMatrix[(int) cur[0]][i];
					pq.offer(new double[] { i, adjMatrix[(int) cur[0]][i] });
				}
			}
		}

		return Math.round(result);
	}
}

// 모든 노드들이 연결되어야 함.. 최소신장트리
// 양방향 그래프
// 환경 부담금 : E * L^2
// 그래프, 가중치
