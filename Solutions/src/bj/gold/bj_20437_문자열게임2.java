package bj.gold;

import java.io.*;
import java.util.*;

public class bj_20437_문자열게임2 {
	static String W;
	static int K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			
			int rule3 = rule3();
			
			if (rule3 == -1)
				sb.append(-1 + "\n");
			else
				sb.append(rule3 + " " + rule4() + "\n");
		}
		
		System.out.println(sb);
		
		br.close();
	}
	
	// 0 ~ 25

	// 3.어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이
	// 큐에다가 넣어서.. 넣을 때 마다 해당 문자 cnt++, 조건 만족하면 min값 비교 후 poll,cnt--, W 끝까지
	static private int rule3() {
		Queue<Integer> q = new LinkedList<>();
		int[] cnt = new int[26];
		int min = Integer.MAX_VALUE;
		int idx = 0;
		
		while (idx < W.length()) {
			// 큐에 삽입, 해당 문자 cnt++;
			int cur = W.charAt(idx++) - 'a';
			q.offer(cur);
			cnt[cur]++;
			
			// K개보다 작아질 때 까지 반복
			while (cnt[cur] == K) {
				// K개 조건 만족하면 현재 큐 크기로 최소값 갱신
				min = Math.min(min, q.size());
				
				// poll 해주고 cnt--
				cnt[q.poll()]--;
			}
		}
		
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	// 4.어떤 문자를 정확히 K개 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
	// 모든 글자 다 확인해보기
	static private int rule4() {
		int max = 0;
		for (int i = 0; i < W.length(); i++) {
			int cur = W.charAt(i) - 'a';
			int idx = i;
			int len = 0;
			int cnt = 0;
			while (idx < W.length()) {
				len++;
				
				if (cur == W.charAt(idx) - 'a')
					cnt++;
				
				if (cnt == K) {
					max = Math.max(max, len);
					break;
				}
				
				idx++;
			}
		}
		
		return max;
	}
}

// 1.알파벳 소문자로 이루어진 문자열 W 입력
// 2.양의 정수 K 입력
// 3.어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이 구함
// 4.어떤 문자를 정확히 K개 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열 