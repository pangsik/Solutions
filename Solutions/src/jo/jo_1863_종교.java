package jo;

import java.io.*;
import java.util.*;

public class jo_1863_종교 {
	static int N;
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
		
		if(aRoot == bRoot) return;
		parents[bRoot] = aRoot;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		makeSet();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a, b);
		}
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (parents[i] == i) {
				answer++;
			}
		}
		
//		int answer = 0;
//		boolean[] check = new boolean[N];
//		for (int i = 0; i < N; i++) {
//			int tmp = findSet(i);
//			if (!check[tmp]) {
//				check[tmp] = true;
//				answer++;
//			}
//		}
		
		System.out.println(answer);
		
		br.close();
	}
}

// 