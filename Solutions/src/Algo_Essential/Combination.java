package Algo_Essential;
import java.util.Arrays;
import java.util.Scanner;

//nCr
public class Combination {
	static int[] numbers;
	static int N, R, total;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		numbers = new int[R];

		combination(0, 0);
		System.out.println(total);
		
		sc.close();
	}

	static void combination(int cnt, int start) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			combination(cnt + 1, i + 1);
		}
	}
}
