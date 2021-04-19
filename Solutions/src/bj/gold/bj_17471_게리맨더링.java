package bj.gold;

import java.io.*;
import java.util.*;

public class bj_17471_게리맨더링 {
	static int N, min;
	static int[] pop;
	static boolean[][] adjMatrix;
	static ArrayList<Integer> areaA;
	static ArrayList<Integer> areaB;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(br.readLine());
		pop = new int[N + 1];
		adjMatrix = new boolean[N + 1][N + 1];
		
		st = new  StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				int idx = Integer.parseInt(st.nextToken());
				adjMatrix[i][idx] = true;
				adjMatrix[idx][i] = true;
			}
		}
		
		min = Integer.MAX_VALUE;
		areaA = new ArrayList<>();
		subset(1);
		
		if (min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
		
		br.close();
	}
	
	static void subset(int cnt) {
		if (cnt == N + 1) {
			// 전부 다 선택하거나 하나도 선택하지 않은 경우
			if (areaA.size() == N || areaA.size() == 0)
				return;
			
			int sumA = 0;
			int sumB = 0;
			
			if (bfs()) {
				// 둘 다 연결 잘 돼있으면 인구 구하기
				for (int n : areaA) {
					sumA += pop[n];
				}
				
				for (int n : areaB) {
					sumB += pop[n];
				}
				
			} else {
				return;
			}
			
			// 인구 차이 최소값 갱신
			min = Integer.min(min, Math.abs(sumA - sumB));
			
			return;
		}
		
		areaA.add(cnt);
		subset(cnt + 1);
			
		areaA.remove(areaA.size() - 1);
		subset(cnt + 1);
	}
	
	static boolean bfs() {
		boolean[] visited = new boolean[N + 1];
		setAreaB();
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(areaA.get(0));
		visited[areaA.get(0)] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < areaA.size(); i++) {
				if (!visited[areaA.get(i)] && adjMatrix[cur][areaA.get(i)]) {
					q.offer(areaA.get(i));
					visited[areaA.get(i)] = true;
				}
			}
		}
		
		q = new LinkedList<>();
		q.offer(areaB.get(0));
		visited[areaB.get(0)] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < areaB.size(); i++) {
				if (!visited[areaB.get(i)] && adjMatrix[cur][areaB.get(i)]) {
					q.offer(areaB.get(i));
					visited[areaB.get(i)] = true;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			if (!visited[i])
				return false;
		}
		
		return true;
	}
	
	static void setAreaB(){
		areaB = new ArrayList<>();
		boolean tmp[] = new boolean[N + 1];
		for (int n : areaA) {
			tmp[n] = true;
		}
		for (int i = 1; i <= N; i++) {
			if (!tmp[i]) {
				areaB.add(i);
			}
		}
	}
}

// 부분집합
// 같은 선거구끼리는 무조건 이어져 있어야함..
// 부분집합으로 두 선거구 구해서 각 선거구가 구역끼리 이어져 있는지 확인
// -> 둘 다 잘 이어져 있다면 인구 차이 구해서 min값 갱신