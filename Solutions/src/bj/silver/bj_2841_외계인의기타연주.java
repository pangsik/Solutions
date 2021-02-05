package bj.silver;
import java.io.*;
import java.util.*;

public class bj_2841_외계인의기타연주 {

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/input_d3_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		Stack<Integer>[] sl = new Stack[6];
		for (int i = 0; i < 6; i++) {
			sl[i] = new Stack<Integer>();
		}
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken()) - 1;
			int p = Integer.parseInt(st.nextToken());

			// 스택 제일 위가 비거나 현재 프렛보다 작은 프렛이 나올 때 까지 pop, cnt++
			while (true) {
				if (sl[l].isEmpty() || sl[l].peek() < p) {
					sl[l].push(p);
					cnt++;
					break;
				}
				else if (sl[l].peek() == p)
					break;

				else if (sl[l].peek() > p) {
					sl[l].pop();
					cnt++;
				}
			}
		}
		pw.println(cnt);

		pw.close();
		br.close();
	}
}

// 줄 1 ~ 6번
// 프렛 1 ~ P번
// 어떤 줄의 프렛을 여러 개 누르고 있으면 더 높은 번호의 프렛 음이 출력됨
// 손 대고 떼는 최소 횟수 구하기
// 스택에 넣자.. 스택 확인해서 지금 내려는 음보다 높은 음이 들어있으면 빼고 횟수+1, 지금 음 넣고 +1
// 낮은 음이 들어있으면 뺴지말고 바로 넣기..
// 스택 6개 만들어야하나?

// 프렛 수 길이만큼의 배열 6개(줄 수)만큼 선언
// 각 줄별로 스택 peek 확인해야함..