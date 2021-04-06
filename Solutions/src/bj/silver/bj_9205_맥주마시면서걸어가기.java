package bj.silver;

import java.io.*;
import java.util.*;

public class bj_9205_맥주마시면서걸어가기 {
	static int[][] arr;
	static int N;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine()) + 2;
			arr = new int[N][2];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " "); // 상근 집, 편의점, 페스티벌 장소
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}			
			if (bfs()) System.out.println("happy");
			else System.out.println("sad");
		}

		br.close();
	}

	static boolean bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(arr[0]);
		visited[0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == arr[arr.length - 1][0] && cur[1] == arr[arr.length - 1][1]) {
				return true;
			}
			
			for (int i = 1; i < N; i++) {
				if (visited[i]) continue;
				
				if (Math.abs(cur[0] - arr[i][0]) + Math.abs(cur[1] - arr[i][1]) <= 1000) {
					q.offer(arr[i]);
					visited[i] = true;
				}
			}
		}
		
		return false;
	}
}

// 50m마다 한 병
// 상근집 - 페스티벌 장소
// 편의점에서 맥주 보충 가능 (맥주 최대 소지량 20병) (처음 시작할 때 20병 들고 시작함)
// 집에서 페스티벌로 직빵 확인
// 안되면 갈 수 있는 편의점 다 가보자
// 두 점 사이 거리 = |x1 - x2| + |y1 - y2|
