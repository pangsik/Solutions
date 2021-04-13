package ssw;

import java.io.*;
import java.util.*;

public class d0_5644_무선충전2 {
	static int[] di = { 0, -1, 0, 1, 0 };
	static int[] dj = { 0, 0, 1, 0, -1 };
	static int M, A;
	static int[][] BC;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int aSum = 0;
			int bSum = 0;

			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 총 이동 시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수

			int aMove[] = new int[M]; // A 이동정보
			int bMove[] = new int[M]; // B 이동정보
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}
			
			// BC정보 입력
			BC = new int[A][4];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				BC[i][0] = Integer.parseInt(st.nextToken()) - 1; // x좌표
				BC[i][1] = Integer.parseInt(st.nextToken()) - 1; // y좌표
				BC[i][2] = Integer.parseInt(st.nextToken()); // 범위
				BC[i][3] = Integer.parseInt(st.nextToken()); // 처리량
			}
			
			int ax = 0, ay = 0;
			int bx = 9, by = 9;
			for (int i = 0; i < M; i++) {
				
				
				
				ax = ax + di[aMove[i]];
				ay = ay + dj[aMove[i]];
				bx = bx + di[bMove[i]];
				by = by + dj[bMove[i]];
			}
			
			
			
			sb.append('#').append(tc).append(' ').append(aSum + bSum);
		}

		br.close();
	}
	
	static ArrayList<Integer> get(int x, int y) {
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int i = 0; i < A; i++) {
			if (Math.abs(x - BC[i][0]) + Math.abs(y - BC[i][1]) <= BC[i][2]) {
				tmp.add(i);
			}
		}
		
		return tmp;
	}
}

// BC(Battery Charger)의 충전 범위가 C일 때, BC와의 거리가 C 이하면 BC에 접속 가능
// 두 지점 A(x1, y1), B(x2, y2) 사이의 거리 : |x1 - x2| + |y1 - y2|
// 겹치는 범위에 들어간다면 둘 중 하나 선택해서 접속 가능

// 맵 그리기 (BC정보..) 10*10
// A는 0,0에서 / B는 9,9에서 출발
// 사용자는 시작 위치(0초)부터 충전 가능
// 사용자 A, B는 같은 위치로 이동할 수도 있음.. 단, 맵 밖으로 나가진 않음