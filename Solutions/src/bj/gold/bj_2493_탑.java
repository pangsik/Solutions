package bj.gold;

import java.util.*;
import java.io.*;

public class bj_2493_탑 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_d4_1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(br.readLine());
		Stack<int[]> s1 = new Stack<int[]>();
		Stack<int[]> s2 = new Stack<int[]>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			s1.add(new int[] {Integer.parseInt(st.nextToken()), i});
		}

		int[] answer = new int[N]; // 답 저장 배열
		while (true) {
			if (!s1.isEmpty()) {
				s2.push(s1.pop());
				while (!s1.isEmpty() && !s2.isEmpty() && s1.peek()[0] >= s2.peek()[0]) {
					answer[s2.pop()[1]] = s1.peek()[1] + 1;
				}
			}
			else	
				break;
		}
		for (int i = 0; i < N; i++)
			System.out.print(answer[i] + " ");
	}
}
// 스택에서 젤 위에 빼서 검사.. 안된다? 그럼 스택2에 넣고 다음꺼 본다
// 3 2 4 -> 이런 경우 4랑 2 비교 -> 안돼서 4 큐에 넣음 -> 2랑 3 비교 -> 2는 돼서 저거 넣음 -> 
// 4 7 5 2 6