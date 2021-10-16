package swea;

import java.io.*;
import java.util.*;

public class d0_2115_벌꿀채취2 {
	static int N, M, C, map[][];
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d0_2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append('#').append(tc).append(' ').append(getMaxBenefit()).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();		
	}
	
	private static int getMaxBenefit() {
		return processCombination();
	}
	
	private static int processCombination() {
		int result = 0;
		int aBenefit = 0;
		int bBenefit = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N - M; j++) { // 첫열부터 연속된 M개 채취가 가능한 열까지, 일꾼 A 선택
				
				// A일꾼 선택된 M개 벌통에서 얻을 수 있는 최대이익
				maxSum = 0;
				makeMaxSubset(i, j, 0, 0, 0);
				aBenefit = maxSum;
				
				
				maxSum = 0;
//				bBenefit = 0;
				// 일꾼 B 선택
				// 일꾼 A와 같은행에서 선택
				for (int j2 = j + M; j2 <= N - M; j2++) {
					makeMaxSubset(i, j2, 0, 0, 0);
//					if (bBenefit < maxSum) bBenefit = maxSum;
				}
				
				// 일꾼 A의 다음행부터 선택
				for (int i2 = i + 1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N - M; j2++) {
						makeMaxSubset(i2, j2, 0, 0, 0);
//						if (bBenefit < maxSum) bBenefit = maxSum;
					}
				}
//				result = Integer.max(result, aBenefit + bBenefit);
				result = Integer.max(result, aBenefit + maxSum);
			}
		}
		
		return result;
	}
	
	private static int maxSum = 0;
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powerSum) {
		// 조건 넘어가면 바로 컷~
		if (sum > C) return;
		
		// 마지막 원소까지 부분집합에 고려해봤다면 (기저조건)
		if (cnt == M) {
			if (maxSum < powerSum) maxSum = powerSum;
			return;
		}
		
		// 선택
		makeMaxSubset(i, j + 1, cnt + 1, sum + map[i][j], powerSum + (map[i][j] * map[i][j]));
		
		// 비선택
		makeMaxSubset(i, j + 1, cnt + 1, sum, powerSum);
		
	}
}





// 각 칸 숫자는 꿀 양
// 최대한 많은 벌꿀 채취가 목표
// 1.두 명의 일꾼. 꿀을 채취할 수 있는 벌통의 수 M
//   각 일꾼은 가로로 연속되도록 M개의 벌통을 선택 및 채취 가능
//   겹치면 안됨
// 2.M개의 벌통에서 채취한 꿀 양이 C를 넘어가면 안됨.. C를 넘지 않도록 M개 중 최대로 선택해야함 (부분집합?)
// 3.선택한 꿀의 제곱만큼 수익 발생

//[제약사항]
//
//1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초.
//
//2. 벌통들의 크기 N은 3 이상 10 이하의 정수이다. (3 ≤ N ≤ 10)
//
//3. 선택할 수 있는 벌통의 개수 M은 1 이상 5 이하의 정수이다. (1 ≤ M ≤ 5)
//
//4. 선택할 수 있는 벌통의 개수 M은 반드시 N 이하로만 주어진다.
//
//5. 꿀을 채취할 수 있는 최대 양 C는 10 이상 30 이하의 정수이다. (10 ≤ C ≤ 30)
//
//6. 하나의 벌통에서 채취할 수 있는 꿀의 양은 1 이상 9 이하의 정수이다.
//
//7. 하나의 벌통에서 일부분의 꿀만 채취할 수 없고, 벌통에 있는 모든 꿀을 한번에 채취해야 한다.