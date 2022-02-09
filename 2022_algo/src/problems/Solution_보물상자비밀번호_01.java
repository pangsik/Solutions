package problems;

import java.util.*;
import java.io.*;

/*
 * 풀이 시간 : 25분
 * 
 * 문제 자체는 어렵지 않았지만 컬렉션 소트 사용하는거 구글 찾아서 풀었음
 * N진수 -> 10진수로 변환하는거 저거 개꿀팁 잊지말고
 * 컴페어러블, 컴퍼레이터도 써보려 했는데 쓸 줄 몰라서 못씀.. 공부하자
 * 
 * Collection sort, Comparable, Comparator, Integer.parseInt(str, radix)
 */

public class Solution_보물상자비밀번호_01 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			ArrayList<String> list = new ArrayList<>();
			String input = br.readLine();
			
			int len = input.length() / 4;
			
			for (int i = 0; i < input.length(); i++) {
				String str = "";
				for (int j = i; j < i + len; j++) {
					int idx = j;
					if (idx >= input.length())
						idx -= input.length();
					str += input.charAt(idx);
				}
				if (list.contains(str))
					continue;
				
				list.add(str);
			}
			
			Collections.sort(list, Collections.reverseOrder());
			
			System.out.println("#" + tc + " " + Integer.parseInt(list.get(K - 1), 16));
		}
		
		br.close();
	}
}

// 16진수 (0~F)
// 시계방향 회전 가능 (한 칸씩 회전)
// 이걸로 만들 수 있는 k번째로 큰 수를 구해서 10진수로 변환
// N개의 숫자 입력되었을 때 ..
// 중복 배제하기