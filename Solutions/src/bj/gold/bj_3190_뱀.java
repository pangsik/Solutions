package bj.gold;

import java.util.*;
import java.io.*;

public class bj_3190_뱀 {
	static int N, time, dir;
	static int[] di = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] dj = { 1, 0, -1, 0 };
	static int[][] map;
	static ArrayDeque<int[]> snake;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = -1; // 사과
		}
		
		time = 0;
		snake = new ArrayDeque<int[]>();
		snake.add(new int[] { 0, 0 });
		map[0][0] = 1;
		dir = 0;
		
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			
			if (!snakeMove(X, C)) {
				break;
			}
		}
		
		// 마지막 직진
		int[] cur = snake.peekFirst();
		while (true) {
			int nx = cur[0] + di[dir];
			int ny = cur[1] + dj[dir];
			
			if (!check(nx, ny))
				break;
			cur[0] = nx;
			cur[1] = ny;
			map[nx][ny] = 1;
			time++;
		}
		
		System.out.println(time + 1);
		
		br.close();
	}
	
	// 게임 종료 시 false 리턴
	private static boolean snakeMove(int goalTime, char LD) {
		// 덱에 저장해서 새로운 헤드 위치 제일 앞에 넣어주고 해당 위치가 사과면 아무 작업도 안함, 사과가 아니면 poll
		while (time < goalTime) {
			int[] cur = snake.peekFirst();
			
			int nx = cur[0] + di[dir];
			int ny = cur[1] + dj[dir];
			
			if (!check(nx, ny))
				return false;
			
			snake.addFirst(new int[] { nx, ny });
			if (map[nx][ny] != -1) {
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = 0;
			}
			
			map[nx][ny] = 1;
			time++;
		}
		
		// 방향 전환
		switch (LD) {
		case 'L':
			dir -= 1;
			if (dir < 0)
				dir += 4;
			break;
		
		case 'D':
			dir = (dir + 1) % 4;
			break;
			
		default:
			break;
		}
		
		return true;
	}

	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] > 0)
			return false;
		return true;
	}
}

// N*N, 상하좌우 벽 존재
// 시작 (0,0), 뱀길이 1, 우측 방향
// 1초마다 뱀 이동
// - 뱀은 몸길이를 늘려 머리를 다음 칸에 위치시킴
// - 이동한 칸에 사과가 있다면 사과는 사라지고 꼬리는 움직이지 않음
// - 이동한 칸에 사과가 없다면 몸 길이를 줄여 꼬리가 위치한 칸을 비워줌 (즉, 몸 길이 변화 X)
// 사과 위치, 뱀 이동경로가 주어질 때 게임이 몇 초에 끝나는지 계산하라

// map
// 0 : 빈 칸
// 1 : 뱀
// -1 : 사과

// L : 좌회전
// D : 우회전
// 3 D (3초 후 후 우회전)
// 15 L (15초 후 좌회전)
// 단, 여기서 초는 전체 게임 시간으로 두 3 D, 15 L 이면 3초 후 우회전, 이어서 12초 후 좌회전






