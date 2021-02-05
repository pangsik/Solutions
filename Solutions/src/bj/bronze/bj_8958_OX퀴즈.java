package bj.bronze;
import java.util.Scanner;

public class bj_8958_OX퀴즈{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		for (int tc = 1; tc <= T; tc++) {
			String[] ox = sc.nextLine().split("");
			int score = 0;
			int sum = 0;
			for (String answer:ox) {
				if (answer.equals("O"))
					score++;
				else 
					score = 0;
				sum += score;
			}
			System.out.println(sum);
		}
	}
}
/*
input
5
OOXXOXXOOO
OOXXOOXXOO
OXOXOXOXOXOXOX
OOOOOOOOOO
OOOOXOOOOXOOOOX

output
10
9
7
55
30
*/