package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.12 
 * 풀이 시간 : 못품
 * 코멘트 : 컴퍼레이터 사용법, 진법 변환!!
 */

public class Solution_sw_5658_보물상자비밀번호_1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String num = br.readLine();
			
			Long answer = Solution(N, K, num);
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}

	private static Long Solution(int N, int K, String inputNum) {
		HashSet<String> numbers = new HashSet<String>();
		
		int size = N / 4;
		
		// 생성 가능한 수 구하기
		for (int i = 0; i < size; i++) {
			int start = i;
			
			for (int j = 0; j < 3; j++) {
				numbers.add(inputNum.substring(start, start + size));
				start += size;
			}
			numbers.add(inputNum.substring(start, inputNum.length()).concat(inputNum.substring(0, i)));
		}

		// 구한 수들 정렬을 위해 어레리에 넣고 정렬
		ArrayList<String> sortedNums = new ArrayList<String>();
		for (String num : numbers) {
			sortedNums.add(num);
		}
		
		Collections.sort(sortedNums, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				for (int i = 0; i < size; i++) {
					if (o1.charAt(i) > o2.charAt(i)) {
						return -1;
					} else if (o1.charAt(i) < o2.charAt(i)) {
						return 1;
					}
				}
				return 0;
			}
		});
		
		// 정렬 후 K번째 수 10진수로 변환하여 리턴
		return convert(size - 1, sortedNums.get(K - 1));
	}
	
	// 16진수 -> 10진수 변환
	private static Long convert(int size, String num) {
		Long res = 0l;
		
		for (int i = 0; i < num.length(); i++) {
			int n;
			char cn = num.charAt(i);
			if (cn >= 'A') {
				n = cn - 'A' + 10;
			}
			else {
				n = cn - '0';
			}
			
			res += n * (long) Math.pow(16, size - i);
		}
		
		return res;
	}
}

// 16진수 숫자 (0~F) 보물상자 (사각형)
// 자물쇠 비밀번호는 보물상자에 적힌 숫자로 만들 수 있는 모든 수 중, K번째로 큰 수를 10진수로 변환
// - 정렬
// - K번째 수 10진수로 변환
// N개의 숫자가 주어졌을 때 비밀번호 출력하는 프로그램을 만들어보자
// 