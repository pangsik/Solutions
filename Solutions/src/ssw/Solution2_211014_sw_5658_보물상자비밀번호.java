package ssw;

import java.io.*;
import java.util.*;

/*
 * 풀이 시간 : 20분
 * 코멘트 : substring 하는 부분에서 약간 헷갈림 (예쁘게 짜려다가..), Collection 정렬, parseInt 진법 변환되는거 개꿀팁!!!!
 */

public class Solution2_211014_sw_5658_보물상자비밀번호 {
	static int N, K;
	static String LockNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			LockNum = br.readLine();
			
			int answer = Solution();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static int Solution() {
		int size = N / 4;
		
		HashSet<String> numbers = new HashSet<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < 4; j++) {
				int startPoint = i + (size * j);
				int endPoint = startPoint + size;
				if (endPoint > LockNum.length()) {
					numbers.add(LockNum.substring(startPoint, LockNum.length()).concat(LockNum.substring(0, endPoint - LockNum.length())));
				}
				else {
					numbers.add(LockNum.substring(startPoint, endPoint));
				}
			}
		}
		
		ArrayList<String> sortNumbers = new ArrayList<>();
		for (String element : numbers) {
			sortNumbers.add(element);
		}
		
		Collections.sort(sortNumbers, Collections.reverseOrder());
		
		return Integer.parseInt(sortNumbers.get(K - 1), 16);
	}
}

// 상자 돌려서 만들 수 있는 (변 길이)자리의 모든 수 중 K번째로 큰 수를 10진수로 출력
// N : 4의 배수 => /4로 한 변의 길이 구하기
// 회전하여 만들 수 있는 모든 수 = 연속된 (변 길이)개로 만들 수 있는 모든 수
// 내림차순 정렬

