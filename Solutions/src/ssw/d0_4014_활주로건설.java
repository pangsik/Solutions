package ssw;

import java.io.*;
import java.util.*;

public class d0_4014_활주로건설 {
	static int[][] map1, map2;
	static int N, X, cnt;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_4014.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 맵 크기
			X = Integer.parseInt(st.nextToken()); // 활주로 길이

			map1 = new int[N][N];
			map2 = new int[N][N];
			cnt = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					map1[i][j] = tmp;
					map2[j][i] = tmp;
				}
			}
			
			runway(map1);
			runway(map2);

			sb.append('#').append(tc).append(' ').append(cnt).append('\n');
		}

		System.out.println(sb);

		br.close();
	}

	static void runway(int[][] map) {
		for (int i = 0; i < N; i++) {
			int sameCnt = 1;
			boolean check = false;
			// 앞 검사 중 : false
			// 뒤 검사 중 : true
			for (int j = 1; j < N; j++) {
				// 이전, 현재가 같은 경우
				if (map[i][j - 1] == map[i][j]) {
					sameCnt++;
					// 뒤로 경사로 만드는중인 경우
					if (check) {
						// 경사로 덜 만들었는데 맵 끝 도달
						if (j == N - 1 && sameCnt < X) break;
						
						// 경사로 완성
						if (sameCnt == X) {
							sameCnt = 0;
							check = false;
						}
					}
				}

				// 이전이 현재보다 작은 경우 2 3
				else if (map[i][j - 1] < map[i][j]) {
					// 높이 차이가 2 이상 나면 땡
					if (map[i][j] - map[i][j - 1] > 1) break;
					
					// 경사로 겹치는 경우?
					if (check) break;
					
					// 경사로 설치 불가능
					if (sameCnt < X) break;
					
					sameCnt = 1;					
				}
				
				// 이전이 현재보다 큰 경우 3 2
				else if (map[i][j - 1] > map[i][j]) {
					// 높이 차이가 2 이상 나면 땡
					if (map[i][j - 1] - map[i][j] > 1) break;

					// 경사로 만드는중에 또 높이차이 발생
					if (check) break;
					
					// 남은 칸수가 경사로 길이보다 작은 경우
					if (N - j < X) break;
					
					sameCnt = 1;
					check = true;
				}
				
				// 활주로 설치 가능!
				if (j == N - 1) cnt++;
			}
		}
	}
}

// N*N
// 모든 가로, 세로 라인 확인?
// 경사로 길이 X, 높이는 항상 1

// 활주로 불가능한 경우
// 1.블록 높이가 다른데 경사로 설치가 불가능한 경우
// 2.경사로가 겹치는 경우
// 3.블록 높이가 2 이상 차이나는 경우 
