package bj.silver;
import java.io.*;
import java.util.*;

public class bj_1966_프린터큐 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			int answer = 0;

			Queue<Integer> q = new LinkedList<>();

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}

			while (true) {
				boolean b = true;
				for (int a : q) {
					if (a > q.peek()) {
						q.offer(q.poll());
						if (--target < 0)
							target = q.size() - 1;
						b = false;
						break;
					}
				}
				if (b) {
					q.poll();
					answer++;
					if (--target < 0)
						break;
				}
			}
			System.out.println(answer);
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