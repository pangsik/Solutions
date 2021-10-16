package swea;
import java.io.*;
import java.util.*;

public class d3_1225_암호생성기 {

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_1225.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());

			Queue<Integer> q = new LinkedList<>();

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}

			int tmp = -1;
			while (tmp != 0) {
				int cnt = 1;
				for (int i = 0; i < 5; i++) {
					tmp = q.poll() - cnt++;
					if (tmp <= 0) {
						tmp = 0;
						q.offer(tmp);
						break;
					}

					else {
						q.offer(tmp);
					}
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			for (int i = 0; i < 8; i++) {
				sb.append(q.poll() + " ");
			}
			pw.println(sb);

		}
		pw.close();
		br.close();
	}
}
