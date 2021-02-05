package bj.silver;
import java.util.Scanner;

public class bj_20309_트리플소트 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean check = true;
		for (int i = 0; i < N; i++) {
			if (sc.nextInt() % 2 != (i + 1) % 2) {
				check = false;
				break;
			}
		}
		if (check)
			System.out.println("YES");
		else
			System.out.println("NO");

		sc.close();
	}
}

//import java.util.Scanner;
//
//public class Solution_boj_20309_트리플소트 /* Main */ {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt();
//		int arr[] = new int[N];
//		for (int i = 0; i < N; i++) 
//			arr[i] = sc.nextInt();
//		int cnt = 0;
//		
//		while (cnt++ < 100) {
//			for (int i = 0; i < N-2; i++) {
//				if (arr[i] > arr[i+2]) {
//					int tmp = arr[i];
//					arr[i] = arr[i+2];
//					arr[i+2] = tmp;
//				}
//			}
//			if (isSorted(arr))
//				break;
//		}
//		
//		if (isSorted(arr))
//			System.out.println("YES");
//		else
//			System.out.println("NO");
//		
//		sc.close();
//	}
//	static boolean isSorted(int arr[]) {
//		for (int i = 0; i < arr.length - 1; i++) {
//			if (arr[i] > arr[i+1])
//				return false;
//		}
//		return true;
//	}
//}
/*
 * input
 * 
 * 
 * output
 * 
 */