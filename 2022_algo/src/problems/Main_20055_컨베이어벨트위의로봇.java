package problems;

import java.io.*;
import java.util.*;

public class Main_20055_컨베이어벨트위의로봇 {
	static class State {
		int hp;
		boolean isRobot;
		public State(int hp, boolean isRobot) {
			this.hp = hp;
			this.isRobot = isRobot;
		}
	}
	static int N, K, step, zeroCnt;
	static ArrayList<State> up, down;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		up = new ArrayList<>();
		down = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			up.add(new State(Integer.parseInt(st.nextToken()), false));
		}
		for (int i = 0; i < N; i++) {
			down.add(0, new State(Integer.parseInt(st.nextToken()), false));
		}
		
		step = 0;
		zeroCnt = 0;
		Solution();
		
		System.out.println(step);
		
		br.close();
	}

	private static void Solution() {
		while (zeroCnt < K) {
			step++;
			
			// 1.벨트가 각 칸의 로봇과 함께 한 칸 회전 (내리는 위치 도착 로봇 내려주고 올리는 위치 올려주기, 내구도 내리는것도)
			rotateBelt();
			
			// 2."가장 먼저 벨트에 올라간 로봇"부터 벨트 회전 방향으로 한 칸 이동이 가능하다면 이동, 불가능하다면 가만히
			//  2-1.로봇이 이동하려면 이동하려는 칸에 로봇이 없어야하고, 내구도 1 이상 남아있어야 함
			moveRobot();
			
			// 3.올리는 위치에 있는 칸 내구도가 0이 아니면 로봇 올림
			setRobot();
			
			// 4.내구도가 0인 칸 개수가 K개 이상이라면 종료, 아니라면 다시 1번으로 => while문 조건
		}
	}

	private static void rotateBelt() {
		// 위 제일 끝 내리기
		State goDown = up.get(up.size() - 1);
		up.remove(up.size() - 1);
		goDown.isRobot = false;
		down.add(goDown);
		
		// 아래 제일 앞 올리기
		State goUp = down.get(0);
		down.remove(0);
		up.add(0, goUp);
		
		// 위 제일 끝 내릴 수 있으면 내리기
		up.get(up.size() - 1).isRobot = false;
	}

	private static void moveRobot() {
		int size = up.size();
		
		for (int i = size - 2; i >= 0; i--) {
			if (!up.get(i).isRobot)
				continue;
			
			if (up.get(i + 1).isRobot || up.get(i + 1).hp == 0)
				continue;
			
			up.get(i).isRobot = false;
			up.get(i + 1).isRobot = true;
			if (--up.get(i + 1).hp == 0)
				zeroCnt++;
		}
	}

	private static void setRobot() {
		if (up.get(0).hp > 0) {
			up.get(0).isRobot = true;
			if (--up.get(0).hp == 0)
				zeroCnt++;
		}
	}
}

// 벨트 위아래로 두 개 관리해야하고..
// 로봇 이동할 때 뒤에서부터 봐야하니까 그냥 어레리? 크기도 100밖에 안되긴함

// 컨베이어 길이 N
// 벨트 길이 2N
// 1 ~ 2N까지 번호 매겨져있음
// 벨트 회전 시 1 ~ 2N-1 칸은 다음 칸으로 이동, 2N칸은 1번 칸으로 이동
// 칸마다 내구도 있음 (Ai)
// 1번 칸 : 올리는 위치
// N번 칸 : 내리는 위치

// 1.벨트가 각 칸의 로봇과 함께 한 칸 회전
// 2."가장 먼저 벨트에 올라간 로봇"부터 벨트 회전 방향으로 한 칸 이동이 가능하다면 이동, 불가능하다면 가만히
//  2-1.로봇이 이동하려면 이동하려는 칸에 로봇이 없어야하고, 내구도 1 이상 남아있어야 함
// 3.올리는 위치에 있는 칸 내구도가 0이 아니면 로봇 올림
// 4.내구도가 0인 칸 개수가 K개 이상이라면 종료, 아니라면 다시 1번으로

// 종료되었을 때 몇 번째 단계가 진행중이었는지 구하기