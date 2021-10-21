package bj.gold;

import java.io.*;
import java.util.*;

public class bj_21611_마법사상어와블리자드 {
	static int N, M, X, Y, ball1, ball2, ball3;
	static int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dj = { 0, 0, -1, 1 };
	static int[] dii = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] djj = { -1, 0, 1, 0 };
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		X = Y = N / 2;
		ball1 = ball2 = ball3 = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			Solution(d, s);
		}
		
		int answer = ball1 + (ball2 * 2) + (ball3 * 3);
		System.out.println(answer);
		
		br.close();
	}
	
	private static void Solution(int d, int s) {
		// 블리자드 마법 시전
		blizzard(d, s);
		
		// 시전 후 남은 애들 큐에 담기
		// 이때, 4개 연속 오는 애들 터뜨리기
		// 터뜨리면서 자리 채우고 4개 연속 또 터뜨리고 쭉 수행
		ArrayDeque<Integer> dq = homeScape();
		// 구슬 변화
		ArrayDeque<Integer> result = ballChange(dq);
		
		// 맵 그리기
		saveMap(result);
	}

	private static void blizzard(int d, int s) {
		int nx = X;
		int ny = Y;
		
		for (int i = 0; i < s; i++) {
			nx += di[d];
			ny += dj[d];
			
			map[nx][ny] = 0;
		}
	}

	private static ArrayDeque<Integer> homeScape() {
		// 2.구슬 폭발
		//  4개 이상 연속하는 구슬이 있다면 폭발 (꿈의집..)
		//  폭발 후 다시 쭉 채워지고, 또 폭발할게 있으면 또 폭발, 없으면 진짜 끝
		
		// - 마법 시전 후 남은 애들 큐에 담기
		// - 자리 채우기 및 4개 이상 폭발
		//  일단 전체 큐에 담아두고.. 최종 큐 상태를 나타내는 스택 하나 만들자
		//  다른 큐에 옮겨 넣으면서.. 지금 넣는게 몇 번 구슬 몇 개 쌓이고 있는지 체크
		//  다음 구슬이 다른 숫자가 나올 때 까지 쭉쭉 하면서 4개 이상이면 다 빼버리기
		
		// 큐에서 하나씩 빼면서 같은 구슬이면 카운트 세기
		// 1.만약 카운트가 4를 넘지 않고 다른 구슬이 나오면 해당 카운트만큼 최종 큐에 구슬번호로 넣기
		// 2.카운트가 4 이상이 되면 다른 구슬 나올 때 까지 그냥 계속 뽑으면서 다른 작업 하지 않기 (폭발)
		// !! 폭발 후 다음 들어올 구슬이랑 현재 최종 큐에 마지막으로 들어간 구슬 번호가 같으면 최종 큐에서 해당 구슬들 다시 빼면서 카운트 세기
		// 1,2번 다시 반복
		
		ArrayDeque<Integer> dq = getBalls();
		ArrayDeque<Integer> result = new ArrayDeque<>();
		
		int prev = 0;
		while (prev == 0) {
			prev = dq.poll();
		}
		int cnt = 1;
		while (true) {
			if (dq.isEmpty()) {
				if (cnt < 4) {
					for (int i = 0; i < cnt; i++) {
						result.offer(prev);
					}
				}
				else {
					if (prev == 1) {
						ball1 += cnt;
					} else if (prev == 2) {
						ball2 += cnt;
					} else if (prev == 3) {
						ball3 += cnt;
					}
				}
				break;
			}
			
			// 다음 올 녀석 확인
			int cur = dq.peek();
			
			if (cur == 0) {
				dq.poll();
				continue;
			}
			
			// 이전과 같다면 빼주고 cnt++, 넘어감
			if (cur == prev) {
				dq.poll();
				cnt++;
				continue;
			}
			
			// 이전과 같지 않다면 여태 cnt 세던 애들 처리
			
			// 폭발하지 않는 경우
			if (cnt < 4) {
				for (int i = 0; i < cnt; i++) {
					result.offer(prev);
				}
				prev = dq.poll();
				cnt = 1;
				continue;
			}
			
			// 폭발하는 경우
			if (prev == 1) {
				ball1 += cnt;
			} else if (prev == 2) {
				ball2 += cnt;
			} else if (prev == 3) {
				ball3 += cnt;
			}
			
			// 결과 큐 젤 위에 있는놈이 다음 놈과 같은 경우
			if (!result.isEmpty() && result.peekLast() == cur) {
				cnt = 0;
				while (!result.isEmpty() && result.peekLast() == cur) {
					result.pollLast();
					cnt++;
				}
				prev = cur;
				continue;
			}
			
			// 결과 큐 젤 위에 있는 놈이 다음 놈과 다른 경우
			prev = dq.poll();
			cnt = 1;
		}
		
		return result;
	}
	
	private static ArrayDeque<Integer> getBalls() {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		// 일단 소용돌이 돌면서 큐에 담기
		// 좌 하 우 상
		int d = 0;
		int val = 1;
		int x = X;
		int y = Y;
		while (true) {
			for (int i = 0; i < val; i++) {
				x += dii[d];
				y += djj[d];
				dq.add(map[x][y]);
				if (x == 0 && y == 0) {
					break;
				}
			}
			
			if (x == 0 && y == 0) {
				break;
			}
			
			d = (d + 1) % 4;
			for (int i = 0; i < val; i++) {
				x += dii[d];
				y += djj[d];
				dq.add(map[x][y]);
			}
			
			d = (d + 1) % 4;
			val++;
		}
		
		return dq;
	}

	private static ArrayDeque<Integer> ballChange(ArrayDeque<Integer> dq) {
		// 3.구슬 변화
		//  연속하는 구슬들을 하나의 그룹이라 함
		//  하나의 그룹은 두 개의 구슬 A,B로 변화. A는 그룹의 구슬 개수, B는 그룹을 이루는 구슬의 번호
		//  구슬이 하나든 두개든 세개든 무조건 A,B 두 개의 구슬로 변함
		
		// - 구슬 변화
		//  전체 큐 뽑아 보면서 A,B 체크, 최종 큐에 다시 적립
		
		ArrayDeque<Integer> result = new ArrayDeque<>();
		if (dq.isEmpty())
			return result;
		
		int prev = dq.poll();
		int cnt = 1;
		while (true) {
			if (dq.isEmpty()) {
				if (cnt > 0) {
					result.offer(cnt);
					result.offer(prev);
				}
				break;
			}
			
			// 다음 올 녀석 확인
			int cur = dq.peek();
			
			if (cur == 0) {
				dq.poll();
				continue;
			}
			
			// 이전과 같다면 빼주고 cnt++, 넘어감
			if (cur == prev) {
				dq.poll();
				cnt++;
				continue;
			}
			
			// 이전과 같지 않다면 여태 cnt 세어둔거 기준으로 A,B 만들어서 result 큐에 삽입
			int A = cnt;
			int B = prev;
			result.offer(A);
			result.offer(B);
			
			prev = dq.poll();
			cnt = 1;
		}
		
		return result;
	}

	private static void saveMap(ArrayDeque<Integer> dq) {
		map = new int[N][N];
		
		int d = 0;
		int val = 1;
		int x = X;
		int y = Y;
		while (true) {
			for (int i = 0; i < val; i++) {
				if (dq.isEmpty()) {
					break;
				}
				x += dii[d];
				y += djj[d];
				map[x][y] = dq.poll();
				
				if (x == 0 && y == 0) {
					break;
				}
			}

			if (dq.isEmpty()) {
				break;
			}
			if (x == 0 && y == 0) {
				break;
			}
			
			d = (d + 1) % 4;
			for (int i = 0; i < val; i++) {
				if (dq.isEmpty()) {
					break;
				}
				x += dii[d];
				y += djj[d];
				map[x][y] = dq.poll();
			}
			
			d = (d + 1) % 4;
			val++;
		}
	}
}

// N*N (N은 항상 홀수)
// 좌상단 (1,1), 우상단 (N,N)
// 마법사 상어 : ((N+1)/2, (N+1)/2) (정 중앙에 위치)
// -----------------------------------------
// N*N (N은 항상 홀수)
// 좌상단 (0,0), 우상단 (N-1,N-1)
// 마법사 상어 : (N/2, N/2) (정 중앙에 위치)

// 1.블리자드 마법
//  어느 방향 몇 칸 파괴시키면 소용돌이 모양으로 쭉 채워짐 (꿈의집)
// 2.구슬 폭발
//  4개 이상 연속하는 구슬이 있다면 폭발 (꿈의집..)
//  폭발 후 다시 쭉 채워지고, 또 폭발할게 있으면 또 폭발, 없으면 진짜 끝
// 3.구슬 변화
//  연속하는 구슬들을 하나의 그룹이라 함
//  하나의 그룹은 두 개의 구슬 A,B로 변화. A는 그룹의 구슬 개수, B는 그룹을 이루는 구슬의 번호
//  구슬이 하나든 두개든 세개든 무조건 A,B 두 개의 구슬로 변함
// 4.출력
//  1*(폭발한 1번 구슬 개수)
//  2*(폭발한 2번 구슬 개수)
//  3*(폭발한 3번 구슬 개수)

// - 블리자드 마법 (그냥 특정 방향으로 몇 칸 파괴)
// - 마법 시전 후 남은 애들 큐에 담기
// - 자리 채우기 및 4개 이상 폭발
//  일단 전체 큐에 담아두고.. 최종 큐 상태를 나타내는 스택 하나 만들자
//  다른 큐에 옮겨 넣으면서.. 지금 넣는게 몇 번 구슬 몇 개 쌓이고 있는지 체크
//  다음 구슬이 다른 숫자가 나올 때 까지 쭉쭉 하면서 4개 이상이면 다 빼버리기
// - 구슬 변화
//  전체 큐 뽑아 보면서 A,B 체크, 최종 큐에 다시 적립
// - 맵 그리기

// 중간 과정에서는 맵에 다시 다 기록 할 필요가 없음!!! 그냥 큐에서 하고 마지막에 맵 그리고 마법 시전할 때만!!
















