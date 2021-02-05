package bj.silver;
import java.io.*;
import java.util.*;

public class bj_18258_큐2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String cmd = st.nextToken();
			
			if (cmd.equals("push")) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			else if (cmd.equals("pop")) {
				if (queue.size() == 0)
					sb.append(-1).append('\n');
				else
					sb.append(queue.poll()).append('\n');
			} 
			
			else if (cmd.equals("size")) {
				sb.append(queue.size()).append('\n');
			} 
			
			else if (cmd.equals("empty")) {
				if (queue.size() == 0)
					sb.append(1).append('\n');
				else
					sb.append(0).append('\n');
			} 
			
			else if (cmd.equals("front")) {
				if (queue.size() == 0)
					sb.append(-1).append('\n');

				else
					sb.append(queue.peek()).append('\n');
			}
			
			else if (cmd.equals("back")) {
				if (queue.size() == 0)
					sb.append(-1).append('\n');
				
				else
					sb.append(queue.peekLast()).append('\n');
			}
		}
		System.out.println(sb);
	}
}
