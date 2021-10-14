package bj.gold;

import java.io.*;

public class bj_14906_스러피 {
	static int N;
	static String str;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append("SLURPYS OUTPUT \n");
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			if (isSlurpys(str.length() - 1))
				sb.append("YES \n");
			else
				sb.append("NO \n");
		}
		
		sb.append("END OF OUTPUT");
		System.out.println(sb);
		
		br.close();
	}
	
	static private boolean isSlump(int cur, int end) {
		// 인덱스 벗어나는 경우 컷
		if (end - cur < 1)
			return false;
		
		// 스럼프 1번 조건
		if (str.charAt(cur) != 'D' && str.charAt(cur) != 'E')
			return false;
		
		// 스럼프 2번 조건 (두 번째 문자는 하나 이상의 'F' 반복)
		if (str.charAt(++cur) != 'F')
			return false;
		
		// 스럼프 2번 조건 ('F' 반복하다가 그냥 끝나면 false ('G'로 끝나야 함))
		while (str.charAt(cur) == 'F') {
			if (++cur == str.length()) 
				return false;
		}
		
		// 스럼프 3번 조건 (마지막이 'G'로 끝나면 스럼프, 마지막이 아닌데 'G'가 들어가면 스럼프 아님.. 새로운 스럼프가 와야됨)
		if (str.charAt(cur) == 'G') {
			if (cur == end)
				return true;
			else
				return false;
		}
		
		// 스럼프 
		return isSlump(cur, end);
	}

	static private boolean isSlimps(int cur, int end) {
		// 인덱스 벗어나는 경우 컷
		if (end - cur < 1)
			return false;
		
		// 스림프 1번 조건
		if (str.charAt(cur) != 'A')
			return false;
		
		// 스림프 2번 조건
		if (end - cur++ == 1 && str.charAt(cur) == 'H')
			return true;
		
		// 스림프 3-1번 조건
		if (str.charAt(cur) == 'B') {
			return isSlimps(cur + 1, end - 1) && str.charAt(end) == 'C';
		}
		
		// 스림프 3-2번 조건
		else {
			return isSlump(cur, end - 1) && str.charAt(end) == 'C';
		}
	}
	
	static private boolean isSlurpys(int len) {
		// 스러피 + 스럼프는 길이 4 이상이니까..
		if (len < 4)
			return false;
		
		// 스림프 끝 지점 찾기 (스림프 끝 + 1 == 스럼프 시작 지점)
		int end = 0;
		for (int i = len; i >= 0; i--) {
			if (str.charAt(i) == 'C' || str.charAt(i) == 'H') {
				end = i;
				break;
			}
		}
		
		// 스림프 검사
		if (!(isSlimps(0, end)))
			return false;
		
		// 스럼프 검사
		if (!(isSlump(end + 1, len)))
			return false;
		
		return true;
	}
}

// 스럼프
// 1.첫 번째 문자는 'D' or 'E'
// 2.첫 번째 문자 뒤에는 하나 이상의 ‘F’가 반복
// 3.반복되는 'F' 뒤에는 또 다른 스럼프나 'G'가 옴.
//   따라서 스럼프는 항상 'F'끝에 오는 스럼프 or 'G'로 끝남
//   DFFEFFFG는 DFF 스럼프, 그 뒤에 오는 EFFFG도 스럼프이기 때문에 스럼프이다.
// 4.위 조건 만족못하면 스럼프가 아니다.

// 스림프
// 1.첫 번째 문자는 'A'
// 2.문자열 길이 2이면 두 번째 문자는 'H'
// 3.문자열 길이 3이상이면 다음 중 하나
//  3-1.'A'+'B'+스림프+'C'
//  3-2.'A'+스-럼-프+'C'
// 4.스림프는 길이 2 이상, 위 경우가 아니면 스림프가 아님

// 스러피
// 스림프 + 스럼프

// 뒤에서부터 탐색해서 C or H 나오는 인덱스 찾기
// 저장해두고 0번 ~ 거기까지 스림프인지 확인
// 스림프가 맞다면 나머지 뒤에도 스럼프인지 확인









