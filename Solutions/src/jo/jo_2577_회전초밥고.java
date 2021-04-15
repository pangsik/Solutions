package jo;

import java.util.*;
import java.io.*;

public class jo_2577_회전초밥고 {
	static int N, d, k, c, cnt, max;
	static int[] belt, visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 벨트에 놓인 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 (서비스 초밥 번호)
		
		belt = new int[N];
		visited = new int[d + 1];
		
		for (int i = 0; i < N; i++) {
			belt[i] = Integer.parseInt(br.readLine());
		}
		
		max = 0;
		cnt = 0;
		sushi();
		
		System.out.println(max);
		
		br.close();
	}
	
	static void sushi() {
		// 0~k 표시
		for (int i = 0; i < k; i++) {
			if (++visited[belt[i]] == 1) { // 새로운 초밥 추가
				cnt++;
			}
		}
		
		// 이벤트 초밥 추가
		if (++visited[c] == 1)
			cnt++;
		
		max = cnt;
		
		// 시작지점 이전 --, 도착지점 ++ 해주기
		for (int i = 1; i < N; i++) {
			calc(i);
			max = Integer.max(max, cnt);
		}
	}
	
	static void calc(int start) {
		// 이전 위치 초밥종류수 -1 해주고 0이 되면 선택된 종류 개수도 -1 (0이 안되면 그 초밥 남아있다는거니까)
		if (--visited[belt[start - 1]] == 0) {
			cnt--;
		}
		
		int end = start + k - 1;
		
		// 범위 초과시
		if (end >= N) {
			end %= N;
		}
		
		// 벨트 마지막 위치 초밥 (새로 추가된 초밥) 추가 시 해당 초밥이 선택된 수가 1이라면 (기존에 0이었다면) 초밥 종류 수 +1
		if (++visited[belt[end]] == 1) {
			cnt++;
		}
	}
}

// 1.원래 회전초밥은 손님 마음대로 고르고 먹은만큼 계산
//  -> 벨트의 특정 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 계산
// 2.각 고객에게 초밥 종류 하나가 쓰인 쿠폰을 발행
//  -> 1번 행사에 참가할 경우 이 쿠폰에 적힌 초밥 하나를 추가로 무료 제공
//  -> 쿠폰에 적힌 번호 초밥이 벨트 위에 없을 경우, 요리사가 새로 만들어 제공

// 손님이 먹을 수 있는 초밥 가짓수의 최대값
// 2 ≤ N ≤ 3,000,000
// 2 ≤ d ≤ 3,000
// 2 ≤ k ≤ 3,000 (k ≤ N)
// 1 ≤ c ≤ d

// 벨트 0 부터 k 까지 돌면서 먹은거 visited에 표시, max 구하기.. 쿠폰에 적힌것도 vistied에 넣기
// 벨트 0번~4번 검사 -> 1번~5번 검사할 차례.. -> 0번자리 visited -1해주고 5번자리 visited +1 --> 시간 단축!
// 시작자리 --해준게 0이 된다? 종류 -1, 도착자리 ++한게 1이다? 종류+1
// 쿠폰 초밥도 추가해두기
