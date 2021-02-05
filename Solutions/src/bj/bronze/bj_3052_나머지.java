package bj.bronze;
import java.util.Scanner;

public class bj_3052_나머지 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int remainder[] = new int[42];
		int answer = 0;
		for (int i = 0; i < 10; i++) {
			remainder[sc.nextInt() % 42]++;
		}
		for (int tmp:remainder) {
			if (tmp != 0)
				answer++;
		}
		System.out.println(answer);
	}
}
/*
input


output

*/