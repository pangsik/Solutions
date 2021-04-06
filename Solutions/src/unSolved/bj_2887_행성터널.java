package unSolved;

import java.util.*;
import java.io.*;

public class bj_2887_행성터널 {
	static int[][] adjMatrix;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		int[][] stars = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			stars[i][0] = Integer.parseInt(st.nextToken());
			stars[i][1] = Integer.parseInt(st.nextToken());
			stars[i][2] = Integer.parseInt(st.nextToken());
		}
		
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					adjMatrix[i][j] = -1;
					continue;
				}
				adjMatrix[i][j] = Math.min(Math.min(Math.abs(stars[i][0] - stars[j][0]), Math.abs(stars[i][1] - stars[j][1])), Math.abs(stars[i][2] - stars[j][2]));
			}
		}
		
		System.out.println(prim());
		
		br.close();
	}
	
	static int prim() {
		int result = 0;
		boolean[] visited = new boolean[N];
		int[] minEdge = new int[N];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
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
				if (!visited[i] && adjMatrix[minVertex][i] != -1 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
		return result;
	}
}

// A(xA, yA, zA), B(xB, yB, zB) 연결 비용 = min(|xA-xB|, |yA-yB|, |zA-zB|)
// 