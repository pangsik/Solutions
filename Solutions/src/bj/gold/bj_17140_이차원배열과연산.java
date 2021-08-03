package bj.gold;

import java.util.*;
import java.io.*;

public class bj_17140_이차원배열과연산 {
	static int r, c, k;
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		
		arr = new int[100][100];
		
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(Solution());
		
		
		br.close();
	}
	
	static private int Solution() {
		int cnt = 0;
		int rMax = 3;
		int cMax = 3;
		while (cnt <= 100) {
//			for (int i = 0; i < 30; i++) {
//				for (int j = 0; j < 30; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			if (check(rMax, cMax)) 
				return cnt;
			
			// 행의 개수 >= 열의 개수
			if (rMax >= cMax) {
				cMax = R(rMax, cMax);
			}
			
			// 행의 개수 < 열의 개수
			else {
				rMax = C(rMax, cMax);
			}
			
			cnt++;
		}
		
		return -1;
	}
	
	static private int R(int rMax, int cMax) {
		int max = 0;
		for (int i = 0; i < rMax; i++) {
			int[] cnt = new int[101];
			for (int j = 0; j < cMax; j++) {
				// 숫자 카운트..
				cnt[arr[i][j]]++;
			}
			
			// pq 정렬조건 세팅
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] > o2[1]) 
						return 1;
					else if (o1[1] == o2[1]) {
						if (o1[0] > o2[0]) 
							return 1;
						else 
							return -1;
					}
					else 
						return -1;
				}
			});
			
			// pq에 정렬해서 저장.. new int[] { i, cnt[i] }
			for (int t = 1; t <= 100; t++) {
				if (cnt[t] > 0) {
					pq.add(new int[] { t, cnt[t] });
				}
			}
			
			// max 갱신해주고
			max = Math.max(max, pq.size() * 2);
			if (max > 100) max = 100;

			// 배열에 다시 넣기
			int t = 0;
			while (!pq.isEmpty()) {
				int[] tmp = pq.poll();
				arr[i][t] = tmp[0];
				arr[i][t + 1] = tmp[1];
				t += 2;
				if (t == 100) break;
			}
			
			for (int a = t; a < 100; a++) {
				arr[i][a] = 0;
			}
		}
		
		return max;
	}

	static private int C(int rMax, int cMax) {
		int max = 0;
		for (int j = 0; j < cMax; j++) {
			int[] cnt = new int[101];
			for (int i = 0; i < rMax; i++) {
				// 숫자 카운트..
				cnt[arr[i][j]]++;
			}
			
			// pq 정렬조건 세팅
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[1] > o2[1]) 
						return 1;
					else if (o1[1] == o2[1]) {
						if (o1[0] > o2[0]) 
							return 1;
						else 
							return -1;
					}
					else 
						return -1;
				}
			});
			
			// pq에 정렬해서 저장.. new int[] { i, cnt[i] }
			for (int t = 1; t <= 100; t++) {
				if (cnt[t] > 0) {
					pq.add(new int[] { t, cnt[t] });
				}
			}
			
			// max 갱신해주고
			max = Math.max(max, pq.size() * 2);
			if (max > 100) max = 100;

			// 배열에 다시 넣기
			int t = 0;
			while (!pq.isEmpty()) {
				int[] tmp = pq.poll();
				arr[t][j] = tmp[0];
				arr[t + 1][j] = tmp[1];
				t += 2;
				if (t == 100) break;
			}

			for (int a = t; a < 100; a++) {
				arr[a][j] = 0;
			}
		}
		
		return max;
	}
	
	private static boolean check(int rMax, int cMax) {
		if (r >= rMax || c >= cMax) return false;
		if (arr[r][c] == k) return true;
		return false;
	}
}

// 정렬
// 각각의 수가 몇 번 나왔는지 알아야 함
// 수의 등장 횟수가 커지는 순으로, 등장 횟수가 같다면 수가 커지는 순으로 정렬
// (수 등장 횟수 오름차순, 수 크기 오름차순)
// Comparable 사용해볼까?

// 배열을 어떻게 저장할 것인가?
// 그냥 배열로 진행.. 배열 크기 저장하는 변수 필요..

















