package unSolved;

import java.util.*;
import java.io.*;

public class bj_20055_컨베이어벨트위의로봇 {
	static int N, K;
	static boolean[] isRobot;
	static ArrayDeque<Integer> robot = new ArrayDeque<>();
	static ArrayDeque<Integer> top = new ArrayDeque<>();
	static ArrayDeque<Integer> bottom = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		isRobot = new boolean[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			top.offerLast(Integer.parseInt(st.nextToken()));
		for (int i = 0; i < N; i++)
			bottom.offerFirst(Integer.parseInt(st.nextToken()));

		Solution();
		
		br.close();
	}
	
	private static int Solution() {
		int cnt = 0;
		int stage = 0;
		while (true) {
			stage++;
			
			// 1. 벨트 회전
			rotateBelt();
			
			// 2. 로봇 이동
			moveRobot();
			
			// 3. 로봇 올림
			setRobot();
			
			// 4. 내구도 0 개수 검사
			if (cnt >= K)
				break;
		}
		return stage;
	}

	private static void rotateBelt() {
		// 벨트 회전
		bottom.offerLast(top.pollLast());
		top.offerFirst(bottom.pollFirst());
		
		// 로봇 위치 정보 수정1
		for (int i = N - 1; i > 0; i--) 
			isRobot[i - 1] = isRobot[i];
		isRobot[0] = false; // 새로운 벨트가 올라올 예정이니까 false
		isRobot[N - 1] = false; // 내리는 위치에 도착했으니 내려줌
		
		// 로봇 위치 정보 수정2
		int size = robot.size();
		for (int i = 0; i < size; i++) {
			int cur = robot.poll() + 1;
			if (cur == N - 1)
				continue;
			robot.offer(cur);
		}
	}

	private static void moveRobot() {
		while (!robot.isEmpty()) {
			int cur = robot.poll();
			
			if (!isRobot[cur + 1] ) {
				isRobot[cur] = false;
				if (cur + 1 == N - 1) {
					
				}
				isRobot[cur + 1] = true;
				robot.offer(cur + 1);
			}
			else {
				
			}
		}
	}

	private static void setRobot() {
		// TODO Auto-generated method stub
		
	}
}

// 길이 N, 벨트 길이 2N
// 각 칸마다 내구도 존재, 해당 칸에 로봇이 올라가거나 로봇이 이동하면 내구도 1 감소
// 1. 벨트가 각 칸 위의 로봇들과 함께 한 칸 회전 (내리는 위치에 도달하면 그 칸은 바로 비워줌) 
// 2. 가장 먼저 벨트에 올라간 로봇부터, 회전 방향으로 이동 가능하면 한 칸 이동
//  2.1 이동 조건은 해당 칸이 비어있어야 하고, 내구도가 1 이상 남아 있어야 함
// 3. 올리는 위치 칸 내구도가 0이 아니면 로봇을 올림
// 4. 내구도가 0인 칸 개수가 K개 이상이라면 종료
// 종료 시 몇 번째 단계가 진행 중이었는지 출력 (단계는 1부터 시작)

// 위쪽 벨트, 아래쪽 벨트 나눠서 관리?
// 큐에는 내구도, 로봇 존재 여부 배열 담기
// 




