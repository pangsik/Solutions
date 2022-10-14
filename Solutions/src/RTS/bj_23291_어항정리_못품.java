package RTS;

import java.io.*;
import java.util.*;

public class bj_23291_어항정리_못품 {
	static final int SIZE = 101;
	static int N, K;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[SIZE][SIZE];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) 
			map[0][i] = Integer.parseInt(st.nextToken());
		
		System.out.println(Solution());
		
		br.close();
	}
	private static int Solution() {
		int cnt = 0;
		while (isDone()) {
			// 0.물고기 수 최대 어항, 최소 어항 차이 비교
			cnt++;
			
			// 1.물고기 수가 가장 적은 어항에 물고기 +1 (그게 여러 개라면 걔네 전부 +1)
			addFish();
			
			// 2.어항 쌓기
			stackFishbowl1();
			
			// 3.물고기 조절
			moveFishes();
			
			// 4.어항 내려놓기
			setFishbowl();
			
			// 5.어항 반으로 두 번 접기
			stackFishbowl2();
			
			// 6.물고기 조절
			moveFishes();
			
			// 7.어항 내려놓기
			setFishbowl();
		}
		
		return cnt;
	}
	
	private static boolean isDone() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for (int j = 0; j < N; j++) {
			max = Math.max(max, map[0][j]);
			min = Math.min(min, map[0][j]);
		}
		
		return max - min <= K;
	}
	
	private static void addFish() {
		ArrayList<Integer> list = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		
		for (int j = 0; j < N; j++) {
			if (map[0][j] == 0)
				break;
			
			if (min < map[0][j]) {
				list.clear();
				list.add(j);
			}
			
			if (min == map[0][j]) {
				list.add(j);
			}
		}
		
		for (int idx : list) {
			map[0][idx]++;
		}
	}
	
	private static void stackFishbowl1() {
		
	}
	
	private static void stackFishbowl2() {
		
	}
	
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	private static void moveFishes() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				
			}
		}
	}
	
	private static void setFishbowl() {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (map[i][j] == 0)
					break;
				dq.add(map[i][j]);
				map[i][j] = 0;
			}
		}
		
		for (int j = 0; j < N; j++) 
			map[0][j] = dq.poll();
	}
}

// 각 어항에는 물고기가 1마리 이상 있음
// 0.물고기 수 최대 어항, 최소 어항 차이 비교 (하면서 최소 어항 어느어느건지 체크해두기)
// 1.물고기 수가 가장 적은 어항에 물고기 +1 (그게 여러 개라면 걔네 전부 +1)
// 2.어항 쌓기
// 3.물고기 조절
// 4.어항 내려놓기
// 5.어항 반으로 두 번 접기
// 6.물고기 조절
// 7.어항 내려놓기























