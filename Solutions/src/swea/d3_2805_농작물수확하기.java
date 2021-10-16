package swea;
import java.io.*;
import java.util.*;

public class d3_2805_농작물수확하기 {

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/input_d3_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			//int farm[][] = new int[N][N];

			int half = N / 2;
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					int val = str.charAt(j) - '0';
					//farm[i][j] = val;
					if (i <= half) {
						if (j <= half && j >= half - i)
							sum += val;
						else if (j > half && j <= half + i)
							sum += val;
					}
					if (i > half) {
						if (j <= half && j >= i - half)
							sum += val;
						else if (j > half && j < half + N - i)
							sum += val;
					}
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if (i <= half) {
//						if (j <= half && j >= half - i)
//							farm[i][j] = -1;
//						if (j > half && j <= half + i)
//							farm[i][j] = -2;
//					}
//					if (i > half) {
//						if (j <= half && j >= i - half)
//							farm[i][j] = -3;
//						if (j > half && j < half + N - i)
//							farm[i][j] = -4;
//					}
//					pw.printf("%3d", farm[i][j]);
//				}
//				pw.println();
//			}

			pw.println("#" + tc + " " + sum);
		}
		pw.close();
		br.close();
	}
}

// 농장 크기 N 항상 홀수
// 농장 N * N
// 마름모 모양 수확.. 2차원 배열 마름모 탐색