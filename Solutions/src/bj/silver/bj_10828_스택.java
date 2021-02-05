package bj.silver;
import java.io.*;
import java.util.*;

public class bj_10828_스택 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String cmd = st.nextToken();
			if (cmd.equals("push"))
				stack.push(Integer.parseInt(st.nextToken()));

			else if (cmd.equals("pop")) {
				if (stack.size() == 0)
					System.out.println(-1);
				else
					System.out.println(stack.pop());
			}

			else if (cmd.equals("size")) {
				System.out.println(stack.size());
			}

			else if (cmd.equals("empty")) {
				if (stack.size() == 0)
					System.out.println(1);
				else
					System.out.println(0);
			}

			else if (cmd.equals("top")) {
				if (stack.size() == 0)
					System.out.println(-1);

				else
					System.out.println(stack.peek());
			}
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