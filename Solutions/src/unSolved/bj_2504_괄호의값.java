package unSolved;

import java.io.*;
import java.util.*;

public class bj_2504_괄호의값 {
	
	static Stack<Character> stack = new Stack<>();
	static String str;
	int sum = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		str = br.readLine();
		int answer = 1;
		stack.push(str.charAt(0));
		
		System.out.println(answer);

		br.close();
	}
	
	// cnt = 1부터 시작.. 0은 미리 넣어두고
	static int next(int cnt) {
		char item = str.charAt(cnt);
		if (item == '(' || item == '[') {
			if (!stack.isEmpty()) {
				int n = stack.peek() == '(' ? 2 : 3;
				stack.push(item);
				return n * next(cnt + 1);
			}
		}
		
		if (item == ')') {
			if (stack.pop() != '(') {
				//error
			}
			else {
				
			}
		}
		
		int sum = 0;
		
		return sum;
	}
}

// () : 2
// [] : 3
// (X) : 2 * X
// [X] : 3 * X

// ()[] : 2 + 3
// ([]) : 2 * 3

// (()[[]])([])

// 스택 비었을 때 ] 들어오는 경우 나중에 생각하자
