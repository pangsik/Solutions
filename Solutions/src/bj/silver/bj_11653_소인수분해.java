package bj.silver;
import java.util.Scanner;

public class bj_11653_소인수분해 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int div = 2;

		while (!isPrime(N)) {
			if ((N % div) == 0) {
				System.out.println(div);
				N /= div;
			} else {
				div += 1;
			}
		}
		if (N != 1)
			System.out.println(N);
	}

	static boolean isPrime(int n) {
		if (n == 1 || n == 2)
			return true;

		if ((n % 2) == 0)
			return false;

		for (int i = 3; i < n; i += 2) {
			if ((n % i) == 0)
				return false;
		}

		return true;
	}
}
