package Algo_Essential;


import java.util.Arrays;
import java.util.Scanner;

public class NextPermutation {
	static int N;
	static int[] input, numbers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		numbers = new int[N];
		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
		} while(np());

		sc.close();
	}

	public static boolean np() {
		
		// Step1 값 두 개씩 비교하면서 꼭대기를 찾음
		int i = N - 1;
		while (i > 0 && input[i - 1] >= input[i]) i--;

		if (i == 0)
			return false;

		// Step2 꼭대기 이전 값과 비교해서 더 큰 값 찾기(뒤쪽부터).. 꼭대기 우측은 내림차순이라 그냥 뒤에서부터 쭉 보면 됨
		int j = N - 1;
		while (input[i - 1] >= input[j]) j--;

		// Step3 찾은 값 교환
		swap(i - 1, j);

		// Step4 교환한곳 오른쪽부터 (꼭대기부터) 오름차순 정렬
		int k = N - 1;
		while (i < k) swap(i++, k--);
		
		return true;
	}

	private static void swap(int i, int j) {
		int tmp = input[i];
		input[i] = input[j];
		input[j] = tmp;
	}
}
