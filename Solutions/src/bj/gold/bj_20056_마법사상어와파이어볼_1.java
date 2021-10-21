package bj.gold;

import java.io.*;
import java.util.*;

public class bj_20056_마법사상어와파이어볼_1 {
	static int[] di = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 상 우상 우 우하 하 좌하 좌 좌상
	static int[] dj = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N, M, K;
	static LinkedList<Ball> map[][];
	static LinkedList<Ball> moved[][];
	static class Ball {
		int m, s, d;
		public Ball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new LinkedList[N][N];
		moved = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<>();
				moved[i][j] = new LinkedList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Ball(m, s, d));
		}
		
		System.out.println(solution());
		
		br.close();
	}
	private static int solution() {
		for (int i = 0; i < K; i++) {
			// 모든 파이어볼 이동, 저장
			map = move();
			
			// 같은 칸 중복 파이어볼 분리
			divide();
		}
		
		return getSum();
	}
	
	private static LinkedList<Ball>[][] move() {
		// 이동한 파이어볼 위치 저장용
		LinkedList<Ball> moved[][] = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				moved[i][j].clear();
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() < 1)
					continue;
				for (Ball cur : map[i][j]) {
//					int nx = (i + di[cur.d] * cur.s) % N;
//					int ny = (j + dj[cur.d] * cur.s) % N;
					
					int nx = i + di[cur.d] * (cur.s % N);
					int ny = j + dj[cur.d] * (cur.s % N);
					
					if (nx >= N)
						nx -= N;
					else if (nx < 0)
						nx += N;
					if (ny >= N)
						ny -= N;
					else if (ny < 0)
						ny += N;
					
					moved[nx][ny].add(new Ball(cur.m, cur.s, cur.d));
				}
			}
		}
		
		return moved;
	}
	
	private static void divide() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() < 2)
					continue;
				
				int mSum = 0;
				int sSum = 0;
				boolean flag1 = true;
				boolean flag2 = true;
				
				for (Ball cur : map[i][j]) {
					mSum += cur.m;
					sSum += cur.s;
					
					// 방향 모두 홀수 or 모두 짝수 판별
					if (cur.d % 2 == 0) {
						flag1 = false;
					}
					else {
						flag2 = false;
					}
				}
				
				int nm = mSum / 5;
				int ns = sSum / map[i][j].size();
				
				map[i][j].clear();
				
				// 질량 0이면 그만
				if (nm == 0)
					continue;
				
				// 모두 짝수 or 홀수 만족하면 새로운 방향 0,2,4,6 아니면 1,3,5,7
				int nd = 1;
				if (flag1 || flag2) {
					nd = 0;
				}
				
				for (int n = 0; n < 4; n++) {
					map[i][j].add(new Ball(nm, ns, n * 2 + nd));
				}
			}
		}
	}
	
	private static int getSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() < 1)
					continue;
//				sum += map[i][j].getFirst().m;
				for (Ball cur : map[i][j]) {
					sum += cur.m;
				}
			}
		}
		return sum;
	}
}

// N : 격자 크기 (N*N)
// M : 파이어볼 수
// K : 명령 이동 횟수

// 격자 밖으로 나가면 0으로 감 ( % N )

// r : 행
// c : 열
// m : 질량
// s : 속력
// d : 방향

// 1.모든 파이어볼이 자신의 방향 d로 속력 s칸 만큼 이동
//  - 이동 중 같은 칸에 여러 개의 파이어볼 존재 가능
// 2.이동 후 2개 이상의 파이어볼이 있는 칸
//  1) 같은 칸의 파이어볼은 모두 합쳐짐
//  2) 파이어볼은 4개의 파이어볼로 나누어짐
//  3) 나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
//   - 질량 = 합쳐진 파이어볼 질량 합 / 5
//   - 속력 = 합쳐진 파이어볼 속력 합 / 합쳐진 파이어볼 개수
//   - 합쳐지는 파이어볼 방향이 모두 홀수이거나 모두 짝수이면 방향은 0,2,4,6이 되고, 그렇지 않으면 1,3,5,7이 됨
//   - 질량이 0인 파이어볼은소멸되어 사라짐





