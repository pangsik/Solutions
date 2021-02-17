package bj.gold;

import java.io.*;
import java.util.*;

public class bj_17135_캐슬디펜스 {
	static int N, M, D, bestScore = 0;
	static int[][] original, map;
	static int[] archers = new int[3]; // 아처 3명 배치 가능한 인덱스들의 집합 (조합)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		original = new int[N][M];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				original[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);

		System.out.println(bestScore);

		br.close();
	}

	static void comb(int cnt, int start) {
		if (cnt == 3) {
			simulate();
			return;
		}

		for (int i = start; i < M; i++) {
			archers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

//***************************************** 아래는 시뮬레이션

	static void simulate() {
		init();
		int score = 0;

		// 매 턴마다 궁수들 한 번씩 활 쏘고 다음턴으로 넘어감
		// 궁수 위치 = (N, archer[i])
		while (!isEmpty()) {
			int[][] toKill = new int[3][2]; // 각 궁수가 어느칸의 적을 죽일지 저장
			boolean[] flag = new boolean[3]; // 세 궁수 모두 죽이는 일이 없는 경우 체크를 위한 변수
			// 궁수 세 명이 동시에 공격하기 때문에 바로 삭제 안하고 각각 누구 죽일지 따로 저장해둔 뒤 한 번에 지워줌
			for (int i = 0; i < 3; i++) {
				int[] tmp = nearMonster(i);
				if (tmp[0] != -1 && tmp[1] != -1) {
					toKill[i][0] = tmp[0];
					toKill[i][1] = tmp[1];
					flag[i] = true;
				}
			}
			
			for (int i = 0; i < 3; i++) {
				if (flag[i] && map[toKill[i][0]][toKill[i][1]] == 1) {
					map[toKill[i][0]][toKill[i][1]] = 0;
					score++;
				}
			}
			
			nextTurn();
		}
		if (bestScore < score)
			bestScore = score;
	}

	// 맵 초기화
	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				map[i][j] = original[i][j];
		}
	}

	// 맵에 적이 남아있는지 확인
	static boolean isEmpty() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					return false;
			}
		}
		return true;
	}

	// 다음 턴.. 적들 한 칸씩 내려가고, 만약 성벽 넘어가면 그냥 사라짐
	static void nextTurn() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					map[i][j] = 0;
					if (i < N - 1)
						map[i + 1][j] = 1;
				}
			}
		}
	}

	static int[] nearMonster(int n) {
		int min = Integer.MAX_VALUE;
		int dist = 0;
		int[] tmp = { -1, -1 };
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					dist = Math.abs(N - i) + Math.abs(archers[n] - j);
					if (dist <= D) {
						if (min > dist) {
							min = dist;
							tmp[0] = i;
							tmp[1] = j;
						}
						else if (min == dist && j < tmp[1]) {
							tmp[0] = i;
							tmp[1] = j;
						}
					}
				}
			}
		}
		return tmp;
	}
}

// map N * M
// 0 빈 칸, 1 적
// 적들은 위에서 아래로 내려감.. 맵 밖으로 나가면 땡인거
// (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|
// 궁수 배치..? MC3 해서 모든 조합 구하고 시뮬 다 돌려볼까?
// 궁수 위치 = (N, archer[i])
