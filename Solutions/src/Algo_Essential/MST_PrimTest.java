package Algo_Essential;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MST_PrimTest {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[N][N];
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N]; // 신장트리에 연결된 정점에서 '자신'(index)으로의 최소간선비용

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
			minEdge[i] = Integer.MAX_VALUE;
		}

		int result = 0;
		minEdge[0] = 0;

		for (int c = 0; c < N; c++) {
			int min = Integer.MAX_VALUE;
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

			// 선택된 정점들 -> 선택되지 않은 정점으로의 간선 체크해서 minEdge 갱신
			for (int i = 0; i < N; i++) {
				if (!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}

		System.out.println(result);

		br.close();
	}
}
