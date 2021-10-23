package bj.gold;

import java.util.*;
import java.io.*;

/*
 * @date : 21.10.22
 * 풀이 시간 : 
 * 
 */

public class bj_21611_마법사상어와블리자드_2 {
	static int N, M, d, s, startX, startY;
	static int[] di = { 0, 1, 0, -1 }; // 좌 하 우 상
	static int[] dj = { -1, 0, 1, 0 };
	static int[] ballCnt;
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
		
		startX = N / 2;
		startY = N / 2;
		ballCnt = new int[3 + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			d = Integer.parseInt(st.nextToken()) - 1;
			s = Integer.parseInt(st.nextToken());
			Solution();
		}
		
		System.out.println(getAnswer());
		
		br.close();
	}
	
	// 1*(폭발한 1번 개수) + 2*(폭발한 2번 개수) + 3*(폭발한 3번 개수)
	private static int getAnswer() {
		int res = 0;
		for (int i = 1; i < 4; i++) {
			res += ballCnt[i] * i;
		}
		
		return res;
	}

	private static void Solution() {
		// 1.마법으로 구슬 파괴 (이때는 폭발한 구슬 개수로 세지 않음)
		magic();
		
		// 2.소용돌이 모양으로 맵 돌며 구슬 수집, 큐에 담기
		ArrayDeque<Integer> balls = getBalls();
		
		// 3.구슬 폭발
//		ArrayDeque<Integer> remainBalls = destroyBalls(balls);
		
		boolean flag = true;
		while (flag) {
			flag = destroyBalls(balls);
		}
		
		ArrayDeque<Integer> remainBalls = new ArrayDeque<>();
		
		// 4.3에서 만든 결과 큐를 바탕으로 그룹핑 진행
		ArrayDeque<Integer> resultBalls = changeBalls(remainBalls);
		
		// 5.4에서 받은 결과 큐를 맵에 새로 저장 (결과 큐 길이 끝나면 나머지 칸들은 0으로 채우기)
		setMap(resultBalls);
	}

	private static void magic() {
		// 1.마법으로 구슬 파괴, 상하좌우, 맵 밖을 벗어나는 경우는 주어지지 않음
		int[] di = { -1, 1, 0, 0 };
		int[] dj = { 0, 0, -1, 1 };
		
		int nx = startX;
		int ny = startY;
		for (int i = 0; i < s; i++) {
			nx += di[d];
			ny += dj[d];
			
			map[nx][ny] = 0;
		}
	}

	private static ArrayDeque<Integer> getBalls() {
		// 2.소용돌이 모양으로 맵 돌며 구슬 수집, 큐에 담아 반환
		ArrayDeque<Integer> balls = new ArrayDeque<>();
		
		int x = startX;
		int y = startY;
		int d = 0;
		int s = 1;
		
		while (true) {
			for (int i = 0; i < s; i++) {
				x += di[d];
				y += dj[d];
				
				if (y == -1) 
					break;
				
				if (map[x][y] == 0)
					continue;
				
				balls.add(map[x][y]);
			}
			
			if (y == -1) break;
			
			d = (d + 1) % 4;
			
			for (int i = 0; i < s; i++ ) {
				x += di[d];
				y += dj[d];

				if (map[x][y] == 0)
					continue;
				
				balls.add(map[x][y]);
			}
			
			d = (d + 1) % 4;
			s = s + 1;
		}
		
		return balls;
	}

	private static boolean destroyBalls(ArrayDeque<Integer> balls) {
		// 3.구슬 폭발
		boolean flag = false;
		// 예외!!! 큐가 빈 상태라면 그냥 리턴
		if (balls.isEmpty())
			return flag;
	
		// 기존 큐에서 값을 하나씩 뽑으며 같은 구슬이 몇개까지 연속해서 나오는지 체크
		int size = balls.size();
		int prev = balls.poll();
		int cnt = 1;
		
		for (int i = 0; i < size-1; i++) {
			int next = balls.peekFirst();
			
			// 다음 값이 이전 값과 같다면 카운트만 ++해주고 넘어감
			if (prev == next) {
				balls.poll();
				cnt++;
				continue;
			}
			
			// 다음 값이 이전 값과 다르다면 여태 뽑은 구슬들의 카운트 확인
			// 4보다 작으면 결과 큐에 넣고, 다음 값 뽑고 넘어감
			if (cnt < 4) {
				while (cnt-- > 0) {
					balls.add(prev);
				}
				prev = balls.poll();
				cnt = 1;
				continue;
			}
			
			// 4 이상이면 구슬 폭발, 터진 구슬 번호 개수 저장
			ballCnt[prev] += cnt;
			
			prev = balls.poll();
			cnt = 1;
			
			flag = true;
		}
		
		// 기존 큐 크기가 0이 되면 마지막으로 여태 뽑은 값 카운트 확인하고 결과 큐에 넣고 결과 큐 반환
		if (cnt < 4) {
			while (cnt-- > 0) {
				balls.add(prev);
			}
		}
		
		else {
			ballCnt[prev] += cnt;
			flag = true;
		}
		
		return flag;
	}
	
	private static ArrayDeque<Integer> changeBalls(ArrayDeque<Integer> remainBalls) {
		// 새로운 결과 큐 생성
		ArrayDeque<Integer> resultBalls = new ArrayDeque<>();
		
		if (remainBalls.isEmpty()) {
			return resultBalls;
		}
		
		// 3과 비슷한 로직으로 값을 하나씩 뽑으며 같은 구슬이 몇개까지 연속해서 나오는지 체크
		int prev = remainBalls.poll();
		int cnt = 1;
		while (!remainBalls.isEmpty()) {
			int next = remainBalls.peekFirst();
			// 다음 값이 이전 값과 같다면 카운트만 세주고 넘어감
			if (prev == next) {
				remainBalls.poll();
				cnt++;
				continue;
			}
			
			// 다음 값이 이전 값과 다르다면 이전 구슬들의 카운트, 구슬 번호 확인
			int A = cnt;
			int B = prev;
			
			// 카운트 값을 A, 구슬 번호를 B로 하여 결과 큐에 삽입 (A,B순서로)
			resultBalls.add(A);
			resultBalls.add(B);
			
			prev = remainBalls.poll();
			cnt = 1;
		}
		
		// 기존 큐 크기가 0이 되면 마지막으로 여태 뽑은 값 카운트 확인하고 결과 큐에 넣고 결과 큐 리턴
		int A = cnt;
		int B = prev;
		
		resultBalls.add(A);
		resultBalls.add(B);
		
		return resultBalls;
	}

	private static void setMap(ArrayDeque<Integer> resultBalls) {
		// 5.4에서 받은 결과 큐를 맵에 새로 저장 (결과 큐 길이 끝나면 나머지 칸들은 0으로 채우기)
		int x = startX;
		int y = startY;
		int d = 0;
		int s = 1;
		
		while (true) {
			for (int i = 0; i < s; i++) {
				x += di[d];
				y += dj[d];
				
				if (y == -1) 
					break;
				
				if (!resultBalls.isEmpty()) 
					map[x][y] = resultBalls.poll();
				
				else 
					map[x][y] = 0;
			}
			
			if (y == -1) break;
			
			d = (d + 1) % 4;
			
			for (int i = 0; i < s; i++ ) {
				x += di[d];
				y += dj[d];

				if (!resultBalls.isEmpty()) 
					map[x][y] = resultBalls.poll();
				
				else 
					map[x][y] = 0;
			}
			
			d = (d + 1) % 4;
			s = s + 1;
		}
	}
}

// N*N
// N은 항상 홀수, 시작 위치 (N/2, N/2)
// 소용돌이 모양으로 구슬이 들어감.. 좌 하 우 상
// 구슬은 1번, 2번, 3번 존재
// 같은 번호를 가진 구슬이 연속하면 그 구슬은 연속하는 구슬
// 방향 d, 거리 s만큼 구슬들 파괴 (그 칸은 빈 칸이 됨)
// 꿈의집처럼 소용돌이 따라서 쭉쭉 빈 칸 채움
// 구슬 폭발, 4개 이상 연속하는 구슬이 있다면 파괴
// 그럼 또 그 빈 칸 만큼 빈 칸 채워서 들어옴.. 또 4개 이상 있으면 또 파괴
// 파괴 끝나면 구슬 변환이 시작됨
// 연속된 구슬들을 하나의 그룹으로 취급
// A : 그룹의 구슬 수
// B : 그룹의 구슬 번호
// 쭉 변환해서 소용돌이 모양으로 채움.. 소용돌이 크기보다 구슬이 많아지면 그냥 순서대로 넣고 늦은 애들은 없어짐
// M번의 마법 시전, 1*(폭발한 1번 개수) + 2*(폭발한 2번 개수) + 3*(폭발한 3번 개수)

// 맵에 구슬 정보 저장
// 1.마법으로 구슬 파괴 (이때는 폭발한 구슬 개수로 세지 않음)
// 2.소용돌이 모양으로 맵 돌며 구슬 수집, 큐에 담기
// 3.구슬 폭발
//  - 큐를 하나 더 생성 (결과 큐)
//  - 기존 큐에서 값을 하나씩 뽑으며 같은 구슬이 몇개까지 연속해서 나오는지 체크
//  - 다음 값이 이전 값과 다르다면 이전 구슬들의 카운트 확인
//  - 4보다 작으면 결과 큐에 넣고 터진 개수 저장, 그렇지 않으면 아무 것도 하지 않음
//  - 기존 큐 크기가 0이 되면 마지막으로 여태 뽑은 값 카운트 확인하고 결과 큐에 넣고 결과 큐 반환
// 4.3에서 만든 결과 큐를 바탕으로 그룹핑 진행
//  - 새로운 결과 큐 생성
//  - 3과 비슷한 로직으로 값을 하나씩 뽑으며 같은 구슬이 몇개까지 연속해서 나오는지 체크
//  - 다음 값이 이전 값과 다르다면 이전 구슬들의 카운트 확인
//  - 카운트 값을 A, 구슬 번호를 B로 하여 결과 큐에 삽입 (A,B순서로)
//  - 기존 큐 크기가 0이 되면 마지막으로 여태 뽑은 값 카운트 확인하고 결과 큐에 넣고 결과 큐 리턴
// 5.4에서 받은 결과 큐를 맵에 새로 저장 (결과 큐 길이 끝나면 나머지 칸들은 0으로 채우기)













