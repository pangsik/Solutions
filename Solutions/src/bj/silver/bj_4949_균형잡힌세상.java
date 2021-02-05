package bj.silver;
import java.io.*;
import java.util.*;

public class bj_4949_균형잡힌세상 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Stack<Character> s = new Stack<>();
		while (true) {
			s.clear();
			char[] ca = br.readLine().toCharArray();
			if (ca.length == 1 && ca[0] == '.')
				break;

			for (char c : ca) {
				if (c == '(' || c == '[') {
					s.push(c);
				}

				else if (c == ')') {
					if (s.isEmpty() || (s.peek() != '(')) {
						s.push(' ');
						break;
					}
					s.pop();
				}

				else if (c == ']') {
					if (s.isEmpty() || (s.peek() != '[')) {
						s.push(' ');
						break;
					}
					s.pop();
				}
			}
			
			if (s.isEmpty()) System.out.println("yes"); 
			else System.out.println("no");
		}
	}
}
/*
 * input
 * 
 * 
 * output
 * 
 */