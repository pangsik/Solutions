package swea;

import java.io.*;
import java.util.*;

public class d4_3289_서로소집합 {
	static int N; // N : 정점 개수
	static int parents[];

	static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	static int findSet(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}

	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return;
		
		parents[bRoot] = aRoot;
	}
	
	static boolean check(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return true;
		
		return false;
	}

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_d0_1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(tc).append(' ');
			st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			parents = new int[N];
			
			makeSet();
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				// 합집합
				if (op == 0) {
					union(a, b);
				}
				
				// 같은 집합에 있는지 체크 후 확인 여부 출력 (참 1 거짓 0)
				else if (op == 1) {
					if (check(a, b))
						sb.append(1);
					else
						sb.append(0);
				}
			}
			System.out.println(sb);
		}
		
		br.close();
	}
}
// 