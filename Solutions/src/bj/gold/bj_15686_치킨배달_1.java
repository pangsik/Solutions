package bj.gold;

import java.io.*;
import java.util.*;

public class bj_15686_치킨배달_1 {
	static int N, M, minChickenDist;
	static int[][] city, chosen;
	static ArrayList<int[]> house, chicken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		minChickenDist = Integer.MAX_VALUE;
		chosen = new int[M][2];
		house = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 1) {
					house.add(new int[] { i, j });
				} else if (city[i][j] == 2) {
					chicken.add(new int[] { i, j });
				}
			}
		}

		// 모든 치킨집, 모든 가정집의 좌표 저장할 필요 있음

		comb(0, 0);
		
		System.out.println(minChickenDist);

		br.close();
	}

	// 치킨집들 살아남는 경우의 수 조합 구해서 경우마다 도시 치킨 거리 구함.. 그 중 가장 작은 값이 정답
	static void comb(int cnt, int start) {
		if (cnt == M) {
			int tmp = getChickenDist();
			if (minChickenDist > tmp)
				minChickenDist = tmp;
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			chosen[cnt] = chicken.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	// 도시 치킨 거리를 구해서 리턴
	static int getChickenDist() {
		int min = 0;
		int sum = 0;
		for (int i = 0; i < house.size(); i++) {
			min = Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				int tmp = Math.abs(house.get(i)[0] - chosen[j][0]) + Math.abs(house.get(i)[1] - chosen[j][1]);
				if (min > tmp)
					min = tmp;
			}
			sum += min;
		}

		return sum;
	}
}

// 도시 크기 N*N
// 각 칸은 빈 칸, 치킨집, 가정집
// (r, c) 좌표 주어짐.. 1부터 시작이므로 인덱스로는 -1 해주자
// 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리
// 치킨 거리는 집을 기준으로 정해짐
// 모든 가정집은 치킨 거리를 가지고 있음
// 모든 집의 치킨 거리를 합하면 도시의 치킨 거리
// 임의의 두 칸 (r1, c1)과 (r2, c2) 사이의 거리는 |r1-r2| + |c1-c2|
// M개 빼고 장사안되는 순으로 치킨집 폐업