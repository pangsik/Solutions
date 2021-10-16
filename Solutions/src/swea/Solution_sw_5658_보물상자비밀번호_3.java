package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.16
 * 풀이 시간 : 25분
 * 코멘트
 * 아직 substring에서 헷갈리는 부분이 많은 듯. 10진수로 변환, 정렬하는건 OK!!!!
 */

public class Solution_sw_5658_보물상자비밀번호_3 {
	static int N, K;
	static String str;
	static long answer;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			str = br.readLine();
			
			Solution();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void Solution() {
		int size = str.length() / 4;
		
		HashSet<String> set = new HashSet<>();
		
		for (int i = 0; i < size; i++) {
			
			for (int j = 0; j < 4; j++) {
				int start = i + size * j;
				String sub;
				if (start + size <= str.length()) {
					sub = str.substring(start, start + size);
				}
				else {
					sub = str.substring(start, str.length()).concat(str.substring(0, start + size - str.length()));
				}
				set.add(sub);
			}
		}
		
		ArrayList<String> numbers = new ArrayList<>();		
		for (String num : set) {
			numbers.add(num);
		}
		
		Collections.sort(numbers, Collections.reverseOrder());
		
		answer = Long.parseLong(numbers.get(K - 1), 16);
	}
}


















