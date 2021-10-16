package swea;

import java.io.*;
import java.util.*;

public class d0_4013_특이한자석 {
	static ArrayList<Integer>[] magnet;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			
			magnet = new ArrayList[4];
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				magnet[i] = new ArrayList<>();
				for (int j = 0; j < 8; j++) {
					magnet[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken()) - 1; // 회전시킬 자석
				int d = Integer.parseInt(st.nextToken()); // 회전 방향
				
				rotate(n, d, 0);
			}
			
			sb.append('#').append(tc).append(' ').append(getScore()).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	// n번 자석을 d 방향으로 회전 (1:시계, -1:반시계), rl:0이면 양쪽, 1이면 우측, -1이면 좌측
	static void rotate(int n, int d, int rl) {
		// n 오른쪽 확인
		if (rl >= 0 && n < 3) {
			if (magnet[n].get(2) != magnet[n + 1].get(6)) {
				rotate(n + 1, d * -1, 1);
			}
		}
		
		// n 왼쪽 확인
		if (rl <= 0 && n > 0) {
			if (magnet[n].get(6) != magnet[n - 1].get(2)) {
				rotate(n - 1, d * -1, -1);
			}
		}
		
		// 시계
		if (d == 1) {
			int tmp = magnet[n].remove(7);
			magnet[n].add(0, tmp);
		}

		// 반시계
		else {
			int tmp = magnet[n].remove(0);
			magnet[n].add(tmp);
		}
	}
	
	
	static int getScore() {
		int score = 0;
		
		for (int i = 0; i < 4; i++) {
			if (magnet[i].get(0) == 1) {
				score += Math.pow(2, i);
			}
		}
		
		return score;
	}
}

// 인덱스 0번이 마지막 점수 체크하는곳
// 인덱스 2번이 우측 확인
// 인덱스 6번이 좌측 확인

// 붙어 있는 자석은 서로 붙어 있는 날의 자성이 다를 경우에만 반대 방향으로 1칸 회전
// 자석회전 시계방향 1, 반시계 -1
// N극 0, S극 1
// 시계방향순으로 입력