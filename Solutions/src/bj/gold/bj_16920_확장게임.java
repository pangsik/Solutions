package bj.gold;

import java.util.*;
import java.io.*;

public class bj_16920_확장게임 {
	static int N, M, P;
	static char[][] map;
	static int[] S;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // N * M
		M = Integer.parseInt(st.nextToken()); // 
		P = Integer.parseInt(st.nextToken()); // 플레이어 수
		
		map = new char[N][M];
		S = new int[P + 1]; // 각 플레이어가 한 번에 확장 가능한 수
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= P; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		
		
		br.close();
	}
	
	static void playgame() {
		int turn = 1;
		while(check()) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == turn + '0') {
						extend(i, j, map[i][j]);
					}
				}
			}
			
			if (++turn > P) {
				turn = 1;
			}
		}
	}
	
	static void extend(int x, int y, char cur) {

	}
	
	// '.' 찾아서 상하좌우 보고 숫자랑 닿는거 없으면 더 볼거 없다~
	static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '.') {
//					if () {
//						
//						return true;
//					}
				}
			}
		}
		
		return false;
	}
}

// N*M 맵에서 플레이어들은 각 하나 이상의 성을 배치시켜둔 상태 (한 칸에 중복은 불가)
// 각 라운드마다 플레이어는 본인 턴에 성을 확장해야함. 플레이어 1, 2, 3... 순으로 확장
// 각 턴이 돌아왔을 때 플레이어는 본인 성을 빈 칸으로 확장.. 본인 성에서 상하좌우로 한 번에 확장 가능한 만큼 전부 확장시킴 (남의 성, 벽으로는 확장 불가능)
// 누구도 확장이 불가능할 때 게임 오버.. 최종 상태 출력 (플레이어 1이 가진 성 수, 2가 가진 수, 3 수...)
