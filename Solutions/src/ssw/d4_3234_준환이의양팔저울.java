package ssw;

import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.Select;

public class d4_3234_준환이의양팔저울 {
	static int ans;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input_d0_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] weights = new int[N];
			boolean[] visit = new boolean[N];
			ans = 0;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				weights[i] = Integer.parseInt(st.nextToken());

			perm(weights, visit, 0, 0, 0);

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb);

		br.close();
	}

	static void perm(int[] weights, boolean[] visit, int cnt, int left, int right) {
		if (cnt == weights.length) {
			ans++;
			return;
		}

		for (int i = 0; i < weights.length; i++) {
			if (visit[i])
				continue;

			visit[i] = true;
			perm(weights, visit, cnt + 1, left + weights[i], right);

			if (left >= right + weights[i])
				perm(weights, visit, cnt + 1, left, right + weights[i]);

			visit[i] = false;
		}
	}
}

// 무게추 N개
// 모든 무게추를 올리는 순서 N!
// 양팔저울 왼,오른쪽에 놓을 경우까지 고려하면 2^N * N!
// 근데 조건 생김
// 항상 left >= right
