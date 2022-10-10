package RTS;

import java.io.*;
import java.util.*;

public class bj_23288_주사위굴리기2 {
	static int N, M, K, scoreSum;
	static int curX, curY, curD;
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };
	static int[] dice;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dice = new int[] { 1, 2, 3, 4, 5, 6 }; // 위 상 우 좌 하 아래
		curX = curY = 0;
		curD = 1;
		scoreSum = 0;
		Solution();
		
		System.out.println(scoreSum);
		
		br.close();
	}
	
	private static void Solution() {
		while (K --> 0) {
			// 이동 (주사위 굴리기)
			rollDice();
			
			// 점수
			scoreSum += getScore();
			
			// 방향 전환
			setDir();
		}
	}

	private static void rollDice() {
		int nx = curX + di[curD];
		int ny = curY + dj[curD];
		
		// 벽이면 턴
		if (isLineOut(nx, ny)) {
			curD = (curD + 2) % 4;
			nx = curX + di[curD];
			ny = curY + dj[curD];
		}
		
		curX = nx;
		curY = ny;
		
		setDice();
	}

	// 위 = 0
	// 상 = 1
	// 우 = 2
	// 좌 = 3
	// 하 = 4
	// 아래 = 5
	
	private static void setDice() {
		// curD 방향으로 주사위 굴리기
		int[] tmp = new int[6];
		
		switch (curD) {
		case 0: // 위로 굴리기
			tmp[0] = dice[4];
			tmp[1] = dice[0];
			tmp[2] = dice[2];
			tmp[3] = dice[3];
			tmp[4] = dice[5];
			tmp[5] = dice[1];
			// 0 = 1
			// 1 = 5
			// 2 = 2
			// 3 = 3
			// 4 = 0
			// 5 = 4
			
			break;
			
		case 1: // 오른쪽으로 굴리기
			tmp[0] = dice[3];
			tmp[1] = dice[1];
			tmp[2] = dice[0];
			tmp[3] = dice[5];
			tmp[4] = dice[4];
			tmp[5] = dice[2];
			// 0 = 2
			// 1 = 1
			// 2 = 5
			// 3 = 0
			// 4 = 4
			// 5 = 3
			
			break;
			
		case 2: // 아래로 굴리기
			tmp[0] = dice[1];
			tmp[1] = dice[5];
			tmp[2] = dice[2];
			tmp[3] = dice[3];
			tmp[4] = dice[0];
			tmp[5] = dice[4];
			// 0 = 4
			// 1 = 0
			// 2 = 2
			// 3 = 3
			// 4 = 5
			// 5 = 1
			
			break;
			
		case 3: // 왼쪽으로 굴리기
			tmp[0] = dice[2];
			tmp[1] = dice[1];
			tmp[2] = dice[5];
			tmp[3] = dice[0];
			tmp[4] = dice[4];
			tmp[5] = dice[3];
			// 0 = 3
			// 1 = 1
			// 2 = 0
			// 3 = 5
			// 4 = 4
			// 5 = 2
			
			break;

		default:
			break;
		}
		
		for (int i = 0; i < 6; i++) 
			dice[i] = tmp[i];
	}

	private static int getScore() {
		int score = 0;
		
		int num = map[curX][curY];
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		
		dq.offer(new int[] { curX, curY });
		visited[curX][curY] = true;
		
		while (!dq.isEmpty()) {
			score++;
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				if (isLineOut(nx, ny) || map[nx][ny] != num || visited[nx][ny])
					continue;
				
				dq.offer(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}
		
		return score * num;
	}

	private static void setDir() {
		int A = dice[5]; // 주사위 아랫면
		int B = map[curX][curY];
		
		if (A > B) 
			curD = (curD + 1) % 4;
		
		else if (A < B) 
			curD = (curD + 3) % 4; // (curD - 1 + 4) % 4
		
		// 나머진 방향 유지
	}
	
	private static boolean isLineOut(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M;
	}
}

// N*M
// 1 base
// 처음 시작은 윗면이 1, 동쪽이 3인 상태로 0,0에서 시작, 방향은 동쪽
// 주사위 이동 규칙
// 1.이동 방향으로 한 칸 굴러감 (방향에 칸이 없다면 반대로 한 칸 구름)
// 2.도착한 칸 (x,y)에 대한 점수 획득
//  - 현재 칸의 숫자 상하좌우 bfs => 같은 숫자인 칸으로만 이동 가능 => 이동 가능한 칸의 수 구하기
//  - 점수 = 이동 가능한 칸의 수 * 현재 칸의 숫자
// 3.주사위 아랫면에 있는 정수 A, 주사위가 있는 칸 (x,y)에 있는 정수 B 비교하여 이동 방향 결정
//  - A > B인 경우 이동 방향을 90도 시계 방향으로 회전
//  - A < B인 경우 이동 방향을 90도 반시계 방향으로 회전
//  - A = B인 경우 방향 변화 X

// 할 일
// 1.주사위 굴리기 => 동서남북 굴릴 때 규칙.. 저장은 어떻게 할래.. 현재 방향 칸 없으면 방향 전환하는 것도
// 2.점수 구하기 = 현재 칸의 정수 기반 bfs => 가능한 칸 수 * 현재 칸 수 = 점수
// 3.주사위 방향 갱신 (주사위 아랫면 수와 현재 칸 정수 비교)
//  - A > B인 경우 이동 방향을 90도 시계 방향으로 회전
//  - A < B인 경우 이동 방향을 90도 반시계 방향으로 회전
//  - A = B인 경우 방향 변화 X
























