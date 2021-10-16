package swea;

import java.io.*;
import java.util.*;

public class d0_2115_벌꿀채취 {
	static int N, M, C, map[][], max;
	
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
			
			max = 0;
			A();
			
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();		
	}
	
	static void A() {
		// 일꾼 A 시작지점 선택
		for (int i = 0; i < N; i++) {
			for (int j = 0; N - j >= M; j++) {
				B(i, j);
			}
		}
	}
	
	static void B(int x, int y) {
		int[] A = new int[M]; // A 벌꿀통
		for (int i = 0; i < M; i++) {
			A[i] = map[x][y + i];
		}
		
		int aSum = subSet(A);
		
		// 일꾼 B 시작지점 선택
		for (int i = x; i < N; i++) {
			for (int j = i == x ? y + M : 0; N - j >= M; j++) {
				getHoney(aSum, i, j);
			}
		}
	}
	
	static void getHoney(int aSum, int x2, int y2) {
		int[] B = new int[M]; // B 벌꿀통
		for (int i = 0; i < M; i++) {
			B[i] = map[x2][y2 + i];
		}
		
		int bSum = subSet(B);
		
		max = Integer.max(max, aSum + bSum);
	}
	
	static int subSet(int[] arr) {
		int max = 0;
		
		for (int i = 0; i < 1 << arr.length; i++) {
			int total = 0;
			int powSum = 0;
			for (int j = 0; j < arr.length; j++) {
				if ((i & 1 << j) != 0) {
					total += arr[j];
					powSum += Math.pow(arr[j], 2);
				}
			}
			if (total > C) continue;
			if (max < powSum) {
				max = powSum;
			}
		}
		
		return max;
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