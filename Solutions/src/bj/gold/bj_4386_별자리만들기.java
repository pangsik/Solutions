package bj.gold;

import java.util.*;
import java.io.*;

public class bj_4386_별자리만들기 {
	static int N;
	static double[][] adjMatrix;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		double[][] stars = new double[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}

		adjMatrix = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				double dist = getDist(stars[i], stars[j]);
				adjMatrix[i][j] = dist;
				adjMatrix[j][i] = dist;
			}
		}

		// 프림
		System.out.printf("%.2f", prim());

		br.close();
	}

	static double getDist(double[] a, double[] b) {
		return Math.sqrt(Math.pow((a[0] - b[0]), 2) + Math.pow((a[1] - b[1]), 2));
	}
	
	static double prim() {
		boolean[] visited = new boolean[N];
		double[] minEdge = new double[N];
		Arrays.fill(minEdge, Double.MAX_VALUE);
		
		double result = 0;
		minEdge[0] = 0;
		
		for (int c = 0; c < N; c++) {
			double min = Double.MAX_VALUE;
			int minVertex = 0;
			for (int i = 0; i < N; i++) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			result += min;
			visited[minVertex] = true;
			
			for (int i = 0; i < N; i++) {
				if (!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		
		return result;
	}
}

// 행성 좌표 입력
// 각 행성별 거리 구해서 인접행렬(or 리스트) 만들기
// prim 알고리즘으로 최소스패닝트리 구하기