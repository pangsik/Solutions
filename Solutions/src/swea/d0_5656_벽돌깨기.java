package swea;

import java.io.*;
import java.util.*;

public class d0_5656_벽돌깨기 {
	static class Pos {
		int h, w, val;

		public Pos(int h, int w, int val) {
			this.h = h;
			this.w = w;
			this.val = val;
		}
	}
	
	static int N, W, H, min;
	static int[][] original;
	static int[][] map;
	static int[] selected;

	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_5656.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			original = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					original[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			selected = new int[N];
			min = Integer.MAX_VALUE;
			dfs(0);
			
			sb.append('#').append(tc).append(' ').append(min).append('\n');
		}
		
		System.out.println(sb);

		br.close();
	}

	// 공 위치 선택 (중복순열)
	static void dfs(int cnt) {
		// 구슬 발사 위치 선정 완료 -> 벽 부수기
		if (cnt == N) {
			copyMap();
			for (int i = 0; i < N; i++) {
				destroy(selected[i]);
				drop();
			}
			
			min = Integer.min(min, remains());
			
			return;
		}
		
		for (int i = 0; i < W; i++) {
			selected[cnt] = i;
			dfs(cnt + 1);
		}
	}
	
	// 벽돌 파괴
	static void destroy(int loc) {
		// 파괴할 벽돌 고르기.. 벽돌 없으면 바로 리턴
		int idx = 0;
		while(map[idx][loc] == 0) {
			idx++;
			if (idx == H)
				return;
		}
		
		Queue<Pos> q = new LinkedList<>();
		q.offer(new Pos(idx, loc, map[idx][loc]));
		
		while(!q.isEmpty()){
			Pos cur = q.poll();
			map[cur.h][cur.w] = 0;
			if (cur.val == 1) continue;
			
			for (int d = 0; d < 4; d++) {
				int nh = cur.h;
				int nw = cur.w;
				for (int i = 0; i < cur.val - 1; i++) {
					nh += di[d];
					nw += dj[d];
					
					if (nh >= 0 && nh < H && nw >= 0 && nw < W) {
						if (map[nh][nw] > 1) {
							q.offer(new Pos(nh, nw, map[nh][nw]));
						}
						map[nh][nw] = 0;
					}
				}
			}
		}
	}

	// 벽돌 파괴 후 아래로 몰기
	static void drop(){
		Queue<Integer> q = new LinkedList<>();
		for(int j = 0; j < W; j++) {
			for (int i = H - 1; i >= 0; i--) {
				if (map[i][j] > 0) {
					q.offer(map[i][j]);
					map[i][j] = 0;
				}
			}
			
			int idx = H - 1;
			while(!q.isEmpty()) {
				map[idx--][j] = q.poll();
			}
		}
	}
	
	// 남은 벽돌 수
	static int remains() {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	// original -> map 깊은 복사
	static void copyMap() {
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = original[i][j];
			}
		}
	}
}

// 벽돌 게임
// 0:빈 칸, 그 외:벽돌
// *규칙*
// 1.구슬은 좌우로 움직일 수 있고 항상 제일 위의 벽돌만 깰 수 있다.
// 2.벽돌은 숫자 1~9로 표현됨
//   구슬이 명중한 벽돌은 상하좌우로 (벽돌에 적힌 숫자 - 1)칸 만큼 같이 제거됨 (동시에 제거!!)
// 연쇄적으로 제거된 벽돌에 숫자가 쓰여있으면 그 벽돌도 적힌 수 만큼 규칙대로 연쇄제거 수행
// 빈 공간이 생기면 아래로 쭉 다 내려옴

// N : 구슬 발사 가능 횟수
// W * H 맵