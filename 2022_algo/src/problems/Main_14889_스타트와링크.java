package problems;

import java.util.*;
import java.io.*;

public class Main_14889_스타트와링크 {
	static int N, min;
	static int[] numbers;
	static int[][] S;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		numbers = new int[N / 2];
		S = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " "); 
			for (int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		comb(0, 0);
		
		System.out.println(min);
		
		br.close();
	}
	
	private static void comb(int start, int cnt) {
		if (cnt == N / 2) {
			getMinGap();
			return;
		}
		
		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			comb(i + 1, cnt + 1);
		}
	}

	private static void getMinGap() {
		int[] numbers2 = new int[N / 2];
		int idx1 = 0;
		int idx2 = 0;
		
//		System.out.println(Arrays.toString(numbers));
		
		for (int i = 0; i < N; i++) {
			if (idx1 < N / 2 && numbers[idx1] == i) {
				idx1++;
				continue;
			}
			numbers2[idx2] = i;
			idx2++;
		}
		
//		System.out.println(Arrays.toString(numbers2));
//		System.out.println();
		
		int teamStart = getAbilitySum(numbers);
		int teamLink = getAbilitySum(numbers2);
		min = Math.min(min, Math.abs(teamStart - teamLink));
	}

	private static int getAbilitySum(int[] list) {
		int sum = 0;
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < N / 2; j++) {
				sum += S[list[i]][list[j]];
			}
		}
		
		return sum;
	}
}

// N은 짝수
// N/2명씩 팀 나눔
// N/2 조합 구하기
