package Algo_Essential;
import java.util.Scanner;

public class LSITest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N]; // 원소들 저장
		int[] LIS = new int[N]; // 각 원소를 마지막에 세웠을 때의 최장길이

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			LIS[i] = 1; // 본인 혼자 섰을 때의 길이로 초기화
			for (int j = 0; j < i; j++) { // 맨 앞부터 자신의 직전 원소들까지 비교
				if (arr[i] > arr[j] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			if (max < LIS[i]) max = LIS[i];
		}
		System.out.println(max);
		
		sc.close();
	}
}
