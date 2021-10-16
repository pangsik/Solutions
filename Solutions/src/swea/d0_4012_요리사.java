package swea;

import java.io.*;
import java.util.*;

public class d0_4012_요리사 {
	static int N, minGap;
	static int[][] S;
	static int[] groupA, groupB;
	static boolean[] flagA;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			minGap = Integer.MAX_VALUE;
			S = new int[N][N];
			groupA = new int[N / 2];
			groupB = new int[N / 2];
			flagA = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}

//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(S[i]));
//			}
			
			comb(0, 0);
			
			sb.append("#").append(tc).append(" ").append(minGap).append("\n");
		}
		
		System.out.println(sb);

		br.close();
	}

	static void comb(int cnt, int start) {
		if (cnt == N / 2) {
			int cntB = 0;
			for (int i = 0; i < N; i++) {
				if (!flagA[i])
					groupB[cntB++] = i;
			}

			int A = calc(groupA);
			int B = calc(groupB);

			minGap = minGap < Math.abs(A - B) ? minGap : Math.abs(A - B);

			return;
		}

		for (int i = start; i < N; i++) {
			groupA[cnt] = i;
			flagA[i] = true;
			comb(cnt + 1, i + 1);
			flagA[i] = false;
		}
	}

	// 맛 계산
	static int calc(int[] group) {
		int sum = 0;
		for (int i = 0; i < group.length; i++) {
			for (int j = i + 1; j < group.length; j++) {
				sum += S[group[i]][group[j]] + S[group[j]][group[i]];
			}
		}
		return sum;
	}
}

// N 까지 숫자가 있을 때 N / 2 개 고르는 조합 만들고 선택안된 N / 2 개도 같이 보내면 되겠다
// 그리고 두 집합에서 각각 뽑아서 경우의 수 다 더해주는거.. 이건 포문 돌리면 될듯

// N개의 식재료
// N/2 개의 식재료로 두 개의 요리를 한다 (N은 짝수)
// A음식, B음식의 맛의 차이가 최소가 되도록 재료 배분
// 음식 맛은 재료 구성따라.. 순서 상관X -> 조합

// 식재료 i는 식재료 j와 함께 요리하면 시너지 발생..! S[i][j] 만큼 발생
// 식재료별 맛 값은 없고, 시너지 맛만 존재
// 식재료 1, 2로 요리를 한다? S[1][2] + S[2][1] 해야됨
