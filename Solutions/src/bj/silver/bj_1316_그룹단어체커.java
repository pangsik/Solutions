package bj.silver;
import java.util.Scanner;

public class bj_1316_그룹단어체커 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int answer = T;
		for (int tc = 1; tc <= T; tc++) {
			String[] va = sc.next().split("");
			int[] arr = new int[26];
			char current = ' ';
			char prev = ' ';
			for (String v:va) {
				current = v.charAt(0);
				if (arr[current - 'a'] != 0 && current != prev) {
					answer--;
					break;
				}
				arr[current - 'a']++;
				prev = v.charAt(0);
			}
		}
		System.out.println(answer);
	}
}
/*
input
3
happy
new
year

4
aba
abab
abcabc
a


output

*/