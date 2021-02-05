package bj.bronze;
import java.util.Scanner;

public class bj_2675_문자열반복 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int re = sc.nextInt();
			String[] str = sc.next().split("");
			for (String out:str) {
				for (int i = 0; i < re; i++)
					System.out.print(out);
			}
			System.out.println();
		}
	}
}
/*
input

output

*/