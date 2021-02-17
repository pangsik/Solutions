package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2504_괄호의값 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		Stack<String> stack = new Stack<>();
		String str;
		int answer = 0;
		
		str = br.readLine();
		stack.push(String.valueOf(str.charAt(0)));
		
		for (int i = 1; i < str.length(); i++) {
			char item = str.charAt(i);
			if (item == '(') {
				stack.push("(");
			}
			
			else if (item == '[') {
				stack.push("[");
			}
			
			else if (item == ')') {
				if (stack.peek().equals("(")) {
					stack.pop();
					stack.push("2");
				}
				else {
					int sum = 0;
					while (!stack.isEmpty()) {
						if (stack.peek().equals("(")) {
							stack.pop();
							stack.push(String.valueOf(sum * 2));
							break;
						}
						else if (stack.peek().equals("[") || stack.peek().equals("]") || stack.peek().equals(")")) {
							System.out.println(0);
							return;
						}
						else {
							sum += Integer.parseInt(stack.pop());
						}
					}
					if (stack.isEmpty()) {
						System.out.println(0);
						return;
					}
				}
			}
			
			else if (item == ']') {
				if (stack.peek().equals("[")) {
					stack.pop();
					stack.push("3");
				}
				else {
					int sum = 0;
					while (!stack.isEmpty()) {
						if (stack.peek().equals("[")) {
							stack.pop();
							stack.push(String.valueOf(sum * 3));
							break;
						}
						else if (stack.peek().equals("(") || stack.peek().equals(")") || stack.peek().equals("]")) {
							System.out.println(0);
							return;
						}
						else {
							sum += Integer.parseInt(stack.pop());
						}
					}
					if (stack.isEmpty()) {
						System.out.println(0);
						return;
					}
				}
			}
			
			else {
				System.out.println(0);
				return;
			}
		}
		
		while (!stack.isEmpty()) {
			String tmp = stack.pop();
			if (tmp.equals("(") || tmp.equals(")") || tmp.equals("[") || tmp.equals("]")) {
				System.out.println(0);
				return;
			}
			answer += Integer.parseInt(tmp);
		}
		System.out.println(answer);

		br.close();
	}
}

// () : 2
// [] : 3
// (X) : 2 * X
// [X] : 3 * X

// ()[] : 2 + 3
// ([]) : 2 * 3

// (()[[]])([])
