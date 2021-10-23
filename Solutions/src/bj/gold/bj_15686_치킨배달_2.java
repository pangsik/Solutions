package bj.gold;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.23
 * 풀이 시간 : 45분
 * 어렵진 않은 문제였는데 처음에 M 크기가 최대 13이라 조합을 써도 될지 고민이 많았음
 * 순열같은 경우는 M!이라서 안됐겠지만 이건 xCm이라 13정도까진 커버 된다고 함...
 */

public class bj_15686_치킨배달_2 {
	static class Pos {
		int r, c;
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M, answer;
	static int[] numbers;
	static ArrayList<Pos> homes;
	static ArrayList<Pos> stores;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		homes = new ArrayList<>();
		stores = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				// 집
				if (cur == 1) {
					homes.add(new Pos(i, j));
				}
				// 치킨집
				else if (cur == 2) {
					stores.add(new Pos(i, j));
				}
			}
		}
		
		answer = Integer.MAX_VALUE;
		Solution();
		
		System.out.println(answer);
		
		br.close();
	}
	
	private static void Solution() {
		// stores.size()개의 치킨집들 중 M개를 뽑는 경우의 수?
		numbers = new int[M];
		comb(0, 0);
	}

	private static void comb(int cnt, int start) {
		if (cnt == M) {
//			System.out.println(Arrays.toString(numbers));
			int dist = getCityDist();
			answer = Integer.min(answer, dist);
			return;
		}
		
		for (int i = start; i < stores.size(); i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	private static int getCityDist() {
		int sum = 0;
		// 모든 집들에 대해 가장 가까운 치킨집과의 거리 구하기
		for (Pos home : homes) {
			sum += getMinDist(home);
		}
		
		return sum;
	}

	private static int getMinDist(Pos home) {
		int min = Integer.MAX_VALUE;
		
		for (int n : numbers) {
			int dist = getDist(home, stores.get(n));
			min = Integer.min(min, dist);
		}
		
		return min;
	}

	private static int getDist(Pos home, Pos store) {
		return Math.abs(home.r - store.r) + Math.abs(home.c - store.c);
	}
}

// N*N
// 빈 칸 or 치킨집 or 집 중 하나
// r,c는 1부터 시작
// 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리
//          각 집은 치킨 거리를 가지고 있음
// 도시의 치킨 거리는 모든 집의 치킨 거리의 합
// (r1,c1) (r2,c2) 거리 : |r1-r2| + |c1-c2|
// 치킨집 중 최대 M개를 고르고 나머지는 모두 폐업시켜야 할 때, 도시의 치킨 거리가 가장 작게 되는 경우의 도시치킨거리를 출력

// 치킨집들 중 M개를 고르는 경우의 수를 모두 해볼까? 조합.. 근데 13인데 괜찮나..

