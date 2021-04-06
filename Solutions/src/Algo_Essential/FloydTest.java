package Algo_Essential;

import java.util.Scanner;

/*
5
0 4 2 5 0
0 0 1 0 4
1 3 0 1 2
-2 0 0 0 2
0 -3 3 1 0


5
0 4 2 5 0
0 0 1 0 4
1 3 0 1 2
2 0 0 0 2
0 3 3 1 0
*/
/**
 * @author taeheekim
 */
public class FloydTest {

	static final int INF = 9999999;
	static int N, distance[][];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		distance = new int[N][N];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				distance[i][j] = sc.nextInt();
				if (i != j && distance[i][j] == 0) {// 자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
					distance[i][j] = INF;
				}
			}
		}
		System.out.println("===========입력==========");
		print();
		// 출발지-->경유지-->목적지로 3중 루프 돌리면 오답
		// 경유지-->출발지-->목적지로 3중 루프 돌려야 정답
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
			print();
		}
		sc.close();
	}

	private static void print() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				System.out.print(distance[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("=====================================");

	}

}
