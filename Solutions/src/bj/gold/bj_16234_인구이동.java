package bj.gold;

import java.util.*;
import java.io.*;

public class bj_16234_인구이동 {
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	
	static int N, L, R;
	static int[][] map, alliance;
	static ArrayList<Integer> population = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(Solution());
		
		br.close();
	}
	private static int Solution() {
		int days = 0;
		while (true) {
			init();
			
			// 4방 탐색으로 연합 구하기
			int allianceCnt = getAlliance();
			
			// 연합이 없다면 종료
			if (allianceCnt == 0) break;
			
			// 인구이동 시작
			move();
			days++;
		}
		
		return days;
	}
	
	private static void init() {
		alliance = new int[N][N];
		population.clear();
	}
	
	private static int getAlliance() {
		int num = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 아직 연합 소속이 아니라면 bfs로 연합 탐색
				// 연합 형성이 잘 되면 num++
				if (alliance[i][j] == 0) {
					if (bfs(i, j, num)) {
						num++;
					}
				}
			}
		}
		
		return num - 1;
	}
	
	private static boolean bfs(int i, int j, int num) {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] { i, j });
		alliance[i][j] = num;
		
		int cnt = 1;
		int popSum = map[i][j];
		
		// 첫 순회를 1번 연합으로 시작해 N*N 배열 하나 만들어서 그 나라가 몇 번 연합인지 표시
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + di[d];
				int ny = cur[1] + dj[d];
				
				// 맵 밖을 나가거나 이미 방문한 (연합이 있는) 나라라면 패스
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || alliance[nx][ny] != 0)
					continue;
				
				// 조건에 맞으면 국경 열기 (연합)
				int tmp = Math.abs(map[cur[0]][cur[1]] - map[nx][ny]);
				if (tmp >= L && tmp <= R) {
					alliance[nx][ny] = num;
					popSum += map[nx][ny];
					dq.offer(new int[] { nx, ny });
					cnt++;
				}
			}
		}
		
		// 연합 형성이 안되면 (혼자라면) false 리턴
		if (cnt == 1) {
			alliance[i][j] = 0;
			return false;
		}
		
		// 순회를 돌며 인구 수를 모두 더하고 나라 수를 세어 순회가 끝나면 평균 인구수를 구해 어레리에 저장
		population.add(popSum / cnt);
		
		return true;
	}
	private static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (alliance[i][j] != 0) {
					map[i][j] = population.get(alliance[i][j] - 1);
				}
			}
		}
	}
}

// 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면 국경선을 하루동안 연다.
// 2. 위 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구이동 시작
// 3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
// 4. 연합을 이루고 있는 각 칸의 인구수는 (연합 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
// 5. 연합을 해체하고 모든 국경선을 닫는다.

// 각 나라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하시오

// 4방 탐색으로 연합 구하기
// 연합이 없다면 종료
// 첫 순회를 1번 연합, N*N 배열 하나 만들어서 그 나라가 몇 번 연합인지 표시
// 순회를 돌며 인구 수를 모두 더하고 나라 수를 세어 순회가 끝나면 평균 인구수를 구해 어레리
// 인구이동 시작














