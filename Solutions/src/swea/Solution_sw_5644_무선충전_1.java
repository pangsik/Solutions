package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.19
 * 풀이 시간 : 못품
 * 코멘트
 * 그냥 개 못품......... 이전 풀이 보고 공부하기.. 이전 풀이에서는 중복순열로 모든 경우 다 따짐
 * 배열 크기가 엄청 작기 때문에 순열 사용해도 괜찮았을 듯.. 작으면 의심 함 해보자
 */

public class Solution_sw_5644_무선충전_1 {
	static class BC {
		int x, y, c, p;
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}
	
	static class User {
		int x, y;
		public User(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int M, A, totalCharge;
	static int[] di = { 0, -1, 0, 1, 0 }; // 가만히 상 우 하 좌
	static int[] dj = { 0, 0, 1, 0, -1 };
	static ArrayDeque<Integer> routeA, routeB;
	static BC[] BCInfo;
	static User userA, userB;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			routeA = new ArrayDeque<Integer>();
			routeB = new ArrayDeque<Integer>();
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				routeA.add(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				routeB.add(Integer.parseInt(st.nextToken()));
			}
			
			BCInfo = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				BCInfo[i] = new BC(x, y, c, p);
			}
			
			userA = new User(0, 0);
			userB = new User(9, 9);
			
			totalCharge = 0;
			Solution();
			
			System.out.println("#" + tc + " " + totalCharge);
		}
		
		br.close();
	}
	
	private static void Solution() {
		int time = M;
		while (time-- > 0) {
			// 충전
			charge();
			
			// 이동
			move();
		}
		
		charge();
	}

	private static void charge() {
		// A 연결 가능한 BC 중 p가 가장 높은 두 놈 얻기
		List<BC> topA = getTop(userA.x, userA.y);
		
		// B 연결 가능한 BC 중 p가 가장 높은 두 놈 얻기
		List<BC> topB = getTop(userB.x, userB.y);
		
		// A,B 둘 다 접근 가능 BC가 있는 경우
		if (topA.size() > 0 && topB.size() > 0) {
			// A,B 1인자가 다른 경우 각자 충전하고 끝
			if (topA.get(0).p != topB.get(0).p) {
				totalCharge += topA.get(0).p;
				totalCharge += topB.get(0).p;
				return;
			}
			// 같은 경우 2인자 비교
			
			// 둘 다 2인자 없는 경우 반띵해서 추가
			if (topA.size() < 2 && topB.size() < 2) {
				totalCharge += topA.get(0).p;
				return;
			}
			
			// A 2인자 없는 경우
			if (topA.size() < 2) {
				// B도 2인자 없는 경우
				if (topB.size() < 2) {
					totalCharge += topA.get(0).p;
					return;
				}
				// B는 2인자 있는 경우, A는 1인자, B는 2인자
				totalCharge += topA.get(0).p;
				totalCharge += topB.get(1).p;
				return;
			}
			
			if (topB.size() < 2) {
				if (topA.size() < 2) {
					totalCharge += topB.get(0).p;
					return;
				}
				totalCharge += topB.get(0).p;
				totalCharge += topA.get(1).p;
				return;
			}
		}
		
		else if (topA.isEmpty()) {
			if (!topB.isEmpty()) {
				totalCharge += topB.get(0).p;
			}
		}
		
		else if (topB.isEmpty()) {
			if (!topA.isEmpty()) {
				totalCharge += topA.get(0).p;
			}
		}
	}

	private static List<BC> getTop(int x, int y) {
		ArrayList<BC> list = new ArrayList<>();
		
		for (int i = 0; i < A; i++) {
			BC cur = BCInfo[i];
			int dist = getDist(x, y, cur.x, cur.y);
			if (dist > cur.c)
				continue;
			list.add(cur);
		}
		
		Collections.sort(list, new Comparator<BC>() {
			@Override
			public int compare(BC o1, BC o2) {
				return Math.max(o1.p, o2.p);
			}
		});
		
		if (list.size() < 2) {
			return list;
		}
		
		return list.subList(0, 2);
	}

	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	private static void move() {
		int d;
		d = routeA.poll();
		userA.x += di[d];
		userA.y += dj[d];
		
		d = routeB.poll();
		userB.x += di[d];
		userB.y += dj[d];
	}
}


// 시간 마다 서있는 곳에서 BC와 연결할 수 있으면 연결하여 충전
// 두 BC에 동시에 연결됐을 때, 가장 많이 얻을 수 있는 경우를 채택
// 두 사람이 같은 BC에 연결하면 BC 성능의 반만큼만 충전이 가능
// 두 점 사이 거리 = |X1 - X2| + |Y1 - Y2|

// A,B 이동
// A,B의 위치를 모든 BC와 비교
// A가 속할 수 있는 BC 리스트업 (내림차순)
// B가 속할 수 있는 BC 리스트업 (내림차순)
// 제일 높은 BC 선택, 만약 A와 B가 같은 BC를 선택한다면?
// A에서 두 번째 BC, B에서 두 번째 BC를 비교하여 더 높은애를 선정 + 다른애는 처음 선택한 제일 높은 애로 충전















