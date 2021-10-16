package swea;
import java.io.*;
import java.util.*;

public class d4_1218_괄호짝짓기 {
	static String open = "([{<";
	static String close = ")]}>";
			
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1218.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			Stack<Character> stack = new Stack<>();
			int result = 1;
			
			for (int i = 0; i < N; i++) {
				char val = str.charAt(i);
				if (open.indexOf(val) != -1) 
					stack.push(val);
				
				else if (close.indexOf(val) != open.indexOf(stack.peek())) {
					result = 0;
					break;
				}
				
				else
					stack.pop();
			}
			
			pw.println("#" + tc + " " + result);
		}

		pw.close();
		br.close();
	}
}