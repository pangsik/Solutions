package Algo_Essential;
import java.util.Scanner;

public class KnapsackTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 물건의 개수
		int W = sc.nextInt(); // 가방의 무게

		int[] weights = new int[N + 1]; // 물건의 무게 정보
		int[] profits = new int[N + 1]; // 물건의 가치 정보
		int[][] D = new int[N + 1][W + 1]; // 해당물건까지 고려하여 해당무게를 만들때의 최대가치

		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) { // 첫 물건부터 고려
			for (int w = 1; w <= W; w++) { // 무게 1부터 고려
				if (weights[i] <= w) { // 가방에 넣을 수 있는 상황 (가방 남은 무게가 더 크거나 같을 때)
					// 넣는 경우, 안 넣는 경우 비교해서 더 큰 값을 선택
					D[i][w] = Math.max(D[i - 1][w - weights[i]] + profits[i], D[i - 1][w]);
				}

				else { // 가방에 넣지 못하는 상황
					D[i][w] = D[i - 1][w]; // 직전까지 가능했던 무게를 그대로 넣음
				}
			}
		}
		
		System.out.println(D[N][W]);
		
		sc.close();
	}
}
