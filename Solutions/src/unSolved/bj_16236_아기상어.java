package unSolved;

import java.util.*;
import java.io.*;

class shark {
	int lv = 2, point = 0, x, y;
	shark(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class bj_16236_아기상어 {

	static int N;
	static shark s;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					s = new shark(i, j);
				}
			}
		}
		
		System.out.println(babyShark());
		
		br.close();
	}

	static int babyShark() {
		int sum = 0;
		while (check()) {
			sum += bfs();
		}
		
		return sum;
	}

	// 먹을거 남았나?
	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0 && map[i][j] < s.lv)
					return true;
			}
		}
		return false;
	}

	// 제일 가까운 거리 고기 찾기 (찾아서 이동시킨 후 이동 거리(시간) 반환)
	static int bfs() {
		int min = Integer.MAX_VALUE;
		
		
		return min;
	}
}
// N * N
// 아기상어 크기 2.. 1초에 상하좌우로 한 칸씩 이동
// 본인보다 큰 물고기 칸은 지나갈 수 없음
// 본인과 같은 물고기는 지나갈 수 있지만 먹을 수 없음
// 본인보다 작은 물고기만 먹을 수 있음

// 아기상어 이동경로 결정 방법
// 1.먹을게 없으면 엄마 부름
// 2.먹을 수 있는 물고기 중 가장 가까운 물고기한테 감
// 2-1.거리는 아기상어 칸에서 물고기한테 이동할 때 지나야하는 칸의 개수의 최소값
// 2-2.거리가 가까운 고기가 많으면 가장 위의 물고기, 그러한 고기가 여러 마리면 가장 왼쪽 고기

// 1칸 이동 -> 1초.. 먹는 시간은 따로 없음 (도착과 동시에 먹었다고 봄)
// 본인 크기와 같은 수만큼 먹으면 크기 1 증가
// 엄마 안부르고 몇초동안 먹고있는지 구해라

// 0: 빈 칸
// 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
// 9: 아기 상어의 위치