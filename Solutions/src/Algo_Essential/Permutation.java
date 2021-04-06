package Algo_Essential;
import java.util.Arrays;
import java.util.Scanner;

//nPn
public class Permutation {
	static int N;
	static int R;
	static boolean[] isSelected;
	static int[] numbers;
	static int total = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();
		numbers = new int[R];
		isSelected = new boolean[N];

		permutation(0);
		System.out.println(total);
		
		sc.close();
	}

	static void permutation(int cnt) {
		if (cnt == R) {
			System.out.println(Arrays.toString(numbers));
			total++;
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;
			numbers[cnt] = i + 1;
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}
}
