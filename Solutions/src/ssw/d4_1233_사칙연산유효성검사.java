package ssw;

import java.io.*;
import java.util.*;

public class d4_1233_사칙연산유효성검사 {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			boolean check = true;
			int N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				st.nextToken();
				char tmp = st.nextToken().charAt(0);
				if (tmp >= '0' && tmp <= '9') {
					if (st.hasMoreTokens())
						check = false;
				}
				
				else {
					if (!st.hasMoreTokens())
						check = false;
				}
				
			}
			sb.append("#").append(tc).append(" ").append(check ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}
}

// 리프노드가 아닌데 숫자면 false
// 리프노드가 연산자면 false
// 자식이 하나인 노드가 들어오는 경우?? -> false처리 해줘야할듯