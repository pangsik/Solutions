package problems;

import java.util.*;
import java.io.*;

public class Main_3190_뱀 {
	static int N, K, L, dir, time;
	static int[][] map;
	static ArrayDeque<int[]> snake, move;
	static int[] di = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dj = { 1, 0, -1, 0 };
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N][N];
		move = new ArrayDeque<>();
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] = 2;
		}
		
		L = Integer.parseInt(br.readLine());
		
		for (int i = 0 ; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			
			move.offer(new int[] { time, dir });
		}
		
		snake = new ArrayDeque<>();
		snake.offer(new int[] { 0, 0 });
		dir = 0;
		time = 0;
		map[0][0] = 1;
		Solution();
		
		System.out.println(time);
		
		br.close();
	}
	
	private static void Solution() {
		while (true) {
			// 턴 할 시간이면 턴
			if (move.size() > 0 && time == move.peek()[0]) {
				int d = move.poll()[1];
				
				// 좌회전
				if (d == 'L') {
					dir = (dir + 3) % 4;
				}
				
				// 우회전
				else if (d == 'D') {
					dir = (dir + 1) % 4;
				}
			}
			
			time++;
			
			// 몸 길이 늘려 다음 칸에 위치
			int nx = snake.peek()[0] + di[dir];
			int ny = snake.peek()[1] + dj[dir];
			
			// 벽에 닿음
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				return;
			}
			
			// 본인 몸에 닿음
			if (map[nx][ny] == 1) {
				return;
			}
			
			// 머리 다음 칸에 위치,, 사과 유무 확인
			snake.offerFirst(new int[] { nx, ny });
			
			// 사과 있으면? 그냥 넘어가
			if (map[nx][ny] == 2) {
				map[nx][ny] = 1;
				continue;
			}
			
			// 없으면? 꼬리 짤라
			map[nx][ny] = 1;
			int[] arr = snake.pollLast();
			map[arr[0]][arr[1]] = 0;
		}
	}
}

// 벽 or 본인 몸과 부딪히면 게임 끝
// N*N
// 시작 위치 0,0
// 시작 길이 1
// 시작 방향 우측
// 매 초마다 이동
// - 뱀은 몸 길이를 늘려 머리를 다음 칸에 위치시킴
// - 만약 이동한 칸에 사과 있다? -> 그 칸 사과 없어지고 꼬리는 움직이지 않음
// - 만약 이동한 칸에 사과 없다? -> 몸 길이 줄여서 꼬리 위치한 칸 비움 (즉, 몸 길이는 변하지 않음)
// 사과 위치, 뱀 이동경로 주어질 때 게임이 몇 초에 끝나는지 구하라
// 몸은 1, 사과는 2
// L : 좌회전
// D : 우회전