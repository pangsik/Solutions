package bj.gold;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.21
 * 풀이 시간 : 60분
 * 코멘트
 * 어레리 2차원 배열로 만드는거!!!!!!! 꼭 다시 보고 가기
 */

public class bj_20056_마법사상어와파이어볼_2 {
	static class FireBall {
		int r, c, m, s, d;
		public FireBall(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	static int N, M, K;
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 우상 우 우하 하 좌하 좌 좌상
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayDeque<FireBall> fireballs;
	static ArrayDeque<FireBall>[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		fireballs = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fireballs.add(new FireBall(r, c, m, s, d));
		}
		
		Solution();
		
		System.out.println(getAnswer());
		
		br.close();
	}
	
	private static int getAnswer() {
		int sum = 0;
		while (!fireballs.isEmpty()) {
			FireBall cur = fireballs.poll();
			sum += cur.m;
		}
		return sum;
	}

	private static void Solution() {
		map = new ArrayDeque[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayDeque<>();
			}
		}
		while (K-- > 0) {
			// 1.파볼 이동
			move();
			
			// 2.2개 이상 파볼들 분리
			div();
		}
	}

	private static void div() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].isEmpty())
					continue;
				
				if (map[i][j].size() == 1) {
					fireballs.add(map[i][j].poll());
					continue;
				}
				
				int size = map[i][j].size();
				int M = 0;
				int S = 0;
				boolean hol = true;
				boolean zac = true;
				
				while (!map[i][j].isEmpty()) {
					FireBall cur = map[i][j].poll();
					M += cur.m;
					S += cur.s;
					if (cur.d % 2 == 0) {
						hol = false;
					}
					else {
						zac = false;
					}
				}
				
				M /= 5;
				S /= size;
				if (M == 0)
					continue;
				
				int tmp = 1;
				if (hol || zac) {
					tmp = 0;
				}
				
				for (int n = 0; n < 4; n++) {
					fireballs.add(new FireBall(i, j, M, S, tmp + n * 2));
				}
			}
		}
	}

	private static void move() {
		while (!fireballs.isEmpty()) {
			FireBall cur = fireballs.poll();
			
			int nx = (cur.r + di[cur.d] * cur.s) % N;
			int ny = (cur.c + dj[cur.d] * cur.s) % N;
			
			if (nx < 0) {
				nx += N;
			}
			if (ny < 0) {
				ny += N;
			}
			
			cur.r = nx;
			cur.c = ny;
			
			map[nx][ny].add(cur);
		}
	}
}

// N*N
// 파이어볼들은 각자 위치에서 대기
// 1번 행은 N번과 연결, 열도 마찬가지 (밖으로 나가면 반대편에서 나옴)
// 8방 (상 ~ 시계방향)
// 1.모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동 
//  - 이동 중에는 같은 칸에 여러 개의 파이어볼 존재 가능 (충돌 신경 안써도 됨)
// 2.이동이 끝난 뒤 2개 이상의 파이어볼이 있는 칸에서는 다음 실행
//  1)같은 칸의 파볼은 하나로 합쳐짐
//  2)파볼은 4개로 나눠짐
//  3)나눠진 파볼들의 질량, 속력, 방향은 다음과 같음
//   - 질량은 (합쳐진 파볼 질량 합 / 5)이다. => 소수점 이하 버림
//   - 속력은 (합쳐진 파볼 속력 합 / 합쳐진 파볼 개수) => 소수점 이하 버림
//   - 합쳐지는 파볼 방향이 "모두" 홀수 or 짝수이면 방향은 0,2,4,6 / 그렇지 않으면 1,3,5,7
//  4)질량이 0인 파볼은 소멸되어 사라짐

// N : 맵 크기
// M : 파볼 개수
// K : 마법 시전 횟수
// r : 좌표
// c : 좌표
// m : 질량
// s : 속력
// d : 방향

// 이동 후 파볼들 저장용으로 2차원 어레리 만들기
// 파볼 꺼내면서 이동, 이동 후 맵에 이동한 좌표에 넣어줌
// 맵 싹 돌면서 큐 크기가 2 이상이면 저거 실행




