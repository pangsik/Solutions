package bj.gold;

import java.io.*;
import java.util.*;

public class bj_14891_톱니바퀴 {
	static ArrayList<Integer> tobni[] = new ArrayList[4];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 4; i++) {
			tobni[i] = new ArrayList<>();
			String tmp = br.readLine();
			for (int j = 0; j < tmp.length(); j++) {
				tobni[i].add(tmp.charAt(j) - '0');
			}
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
			int d = Integer.parseInt(st.nextToken()); // 방향
			tmp(n, d);
		}

		System.out.println(getScore());

		br.close();
	}

	static void tmp(int n, int d) {
		// 톱니바퀴 번호를 인덱스로 회전 여부, 회전 방향에 대한 정보 있음
		boolean[][] check = new boolean[4][2];
		
		// 초기값들 설정 (왼쪽)
		int tmp = n;
		boolean flag = false;
		// 1이면 시계방향, 아니면 반시계(false 유지)
		if (d == 1)
			flag = true;
		
		check[n][0] = true;
		check[n][1] = flag;
		
		// 좌측 확인
		while (tmp-- > 0) {
			if (tobni[tmp].get(2) != tobni[tmp + 1].get(6)) {
				flag = !flag;
				check[tmp][0] = true;
				check[tmp][1] = flag;
			}
			else
				break;
		}

		// 초기값들 설정 (왼쪽, 오른쪽 두 번 가야하니까)
		tmp = n;
		flag = false;
		// 1이면 시계방향, 아니면 반시계(false 유지)
		if (d == 1)
			flag = true;
		// 우측 확인
		while (++tmp < 4) {
			if (tobni[tmp].get(6) != tobni[tmp - 1].get(2)) {
				flag = !flag;
				check[tmp][0] = true;
				check[tmp][1] = flag;
			}
			else
				break;
		}
		
		for (int i = 0; i < 4; i++) {
			if(check[i][0]) {
				rotate(i, check[i][1]);
			}
		}
	}

	static void rotate(int n, boolean d) {
		// 시계방향 회전
		if (d) {
			int tmp = tobni[n].get(tobni[n].size() - 1);
			tobni[n].remove(tobni[n].size() - 1);
			tobni[n].add(0, tmp);
		}

		// 반시계방향 회전
		else if (!d) {
			tobni[n].add(tobni[n].get(0));
			tobni[n].remove(0);
		}
	}

	static int getScore() {
		int score = 0;
		for (int i = 0; i < 4; i++) {
			if (tobni[i].get(0) == 1) {
				score += Math.pow(2, i);
			}
		}
		return score;
	}
}

// 0 1 2 3 4 5 6 7 => 총 8개
// 3시 : 2
// 9시 : 6
// 중심 양쪽으로 확인 후 왼쪽 먼저 쭉 확인..
// 1, true 시계방향 회전 : 제일 끝 떼서 제일 앞에 붙이면 됨
// -1, false 반시계방향 회전 : 제일 앞 떼서 제일 뒤에 붙이면 됨
// N극 0, S극 1
// 톱니 8개짜리 총 4개 있음
// 1, 2, 3, 4번
// 톱니바퀴 K번 회전.. 시계 or 반시계
// 회전시킬 톱니와 방향 결정..
// 극이 같으면 움직이지 않음
// 극이 다르면 서로 반대 방향으로 회전
// 움직인 톱니바퀴 기준으로 양옆으로 쭉쭉 확인.. 단, 같은 극이라 움직이지 않으면 다음꺼 확인 안해도 됨