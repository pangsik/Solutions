package ssw;

import java.io.*;
import java.util.*;

public class d3_3499_퍼펙트셔플 {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_3499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 카드 수
			int half = (int) Math.ceil(N / 2.0); // 절반.. N = 5 -> half = 3
			String[] cards = new String[N];

			// cards 배열에 저장
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				cards[i] = st.nextToken();
			}

			// 절반 기준으로 두 뭉치 있다 생각하고 하나씩 번갈아가며 놓기
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < half; i++) {
				sb.append(cards[i] + " ");
				if (half + i >= N) break; // 절반 기준때문에 인덱스 넘어가는거 잡아주기
				sb.append(cards[half + i] + " ");
			}

			pw.println("#" + tc + " " + sb);
		}
		pw.close();
		br.close();
	}
}

// 카드 절반 떼서 하나씩 깔기
// 홀수인 경우 왼쪽이 +1 ex) 5장이면 3장 2장으로 나눔