package unSolved;

import java.util.*;
import java.io.*;

public class bj_17143_낚시왕2 {
	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r; // 행
			this.c = c; // 열
			this.s = s; // 속도
			this.d = d; // 방향
			this.z = z; // 크기
		}
	}

	// 상 하 우 좌 (0번 인덱스 버림)
	static int[] di = { 0, -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 0, 1, -1 };

	static int R, C, M, map[][];
	static HashMap<Integer, Shark> sharks;
	// 상어 크기 중복 없으므로 z를 key로 하여 상어정보 저장

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		M = Integer.parseInt(st.nextToken()); // 상어 수

		map = new int[R][C];
		sharks = new HashMap<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = z;
			sharks.put(z, new Shark(r, c, s, d, z));
		}

		System.out.println(fishking());

		br.close();
	}

	static int fishking() {
		int cur = -1;
		int sum = 0;

		// 낚시왕 이동 (끝에 도착하면 그만)
		while (++cur < C && !sharks.isEmpty()) {
			// 낚시 (같은 열의 가장 가까운 상어 캐치)
			sum += catchShark(cur);
			
			// 상어 이동
			moveShark();
			
//			// 객체 싹 갱신 후 맵에서 중복친구들 만져주기..
//			setShark();
		}
		return sum;
	}
	
	static void setShark() {
		// 맵 초기화
		map = new int[R][C];
		
		ArrayList<Integer> toRemove = new ArrayList<>();
		
		for (int t : sharks.keySet()) {
			Shark cur = sharks.get(t);
			
			// 내 자리 보기
			if (map[cur.r][cur.c] == 0) {
				map[cur.r][cur.c] = cur.z;
				continue;
			}
			
			// 내 자리에 누가 있으면 서로 크기 비교
			int loser = Math.min(map[cur.r][cur.c], cur.z);
			// 자리에 있는 애가 더 클 때 (본인 조용히 퇴장)
			if (loser == cur.z) {
				toRemove.add(loser);
			}
			// 본인이 더 클 때 원래 맵에 있던 친구 해시맵에서 제거, 본인 맵에 등록
			else {
				toRemove.add(loser);
				map[cur.r][cur.c] = cur.z;
			}
		}
		
		for (int rm : toRemove) {
			sharks.remove(rm);
		}
	}
	
	static void moveShark() {
		// 맵 초기화
		map = new int[R][C];
		
		// 삭제할 애들
		ArrayList<Integer> toRemove = new ArrayList<>();
		
		// 모든 상어들에 대해 객체에만 위치정보 세팅해줌
		for (int t : sharks.keySet()) {
			Shark cur = sharks.get(t);
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;
			int s = cur.s;
			int z = cur.z;
			
			for (int i = 0; i < s; i++) {
				int nr = r + di[d];
				int nc = c + dj[d];
				
				if (nr >= 0 && nr < R && nc >= 0 && nc < C) {
					r = nr;
					c = nc;
				}
				
				// 맵 밖으로 나가는 경우.. 방향 바꿔주고 새 위치 세팅
				else {
					d++;
					if (d == 3) d = 1;
					else if (d == 5) d = 3;
					r = r + di[d];
					c = c + dj[d];
				}
			}
			
			// 수정된 위치정보, 방향정보 저장
			sharks.put(t, new Shark(r, c, s, d, cur.z));
			
			// 내 자리 보기.. 별거 없으면 등록
			if (map[r][c] == 0) {
				map[r][c] = z;
				continue;
			}
			
			// 자리에 누가 있으면 본인과 크기 비교
			int loser = Math.min(map[r][c], cur.z);
			
			// 패배자는 삭제 예정
			toRemove.add(loser);
			
			// 본인이 더 큰 경우 해당 자리 차지
			if (loser != z) {
				map[r][c] = z;
			}
		}
	}
	
	static int catchShark(int cur) {
		int result = 0;
		
		for (int i = 0; i < R; i++) {
			if (map[i][cur] != 0) {
				result = map[i][cur];
				sharks.remove(map[i][cur]);
				map[i][cur] = 0;
				break;
			}
		}
		
		return result;
	}
}

// 1.낚시왕이 오른쪽으로 한 칸 이동
// 2.낚시왕과 같은 열의 상어 중 땅과 가장 가까운 상어 잡음 (잡으면 상어는 격자판에서 사라짐)
// 3.상어가 이동

// 상어는 초당 n칸씩 이동
// 벽 너머로 이동하려하면 방향 반대로 바꿔서 이동 (속력 유지) 벽에 부딪히면 방향만 바꾸는게 아니고 바꾼 방향으로 가야됨

// 상어가 이동을 마친 후 한 칸에 상어가 두 마리 이상 있을 경우 크기가 큰 상어가 나머지를 모두 잡아먹음

// 상어 정보 입력 r, c, s, d, z
// r,c : 상어위치
// s : 속력
// d : 방향
// z : 크기
// 1:상, 2:하, 3:우, 4:좌
