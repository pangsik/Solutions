package bj.silver;

import java.io.*;
import java.util.*;

public class bj_10972_다음순열 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		if (nextPerm(arr)) {
			for (int i = 0; i < N; i++)
				System.out.print(arr[i] + " ");
			System.out.println();
		}

		else
			System.out.println("-1");

		br.close();
	}

	public static boolean nextPerm(int[] arr) {
		// 뒤에서부터 a - 1보다 a가 더 큰 경우 탐색
		int a = arr.length - 1;
		while (a > 0 && arr[a - 1] >= arr[a])
			a--;
		// 없으면 없는거
		if (a <= 0)
			return false;

		// 다시 뒤에서부터 a - 1보다 더 큰 숫자 탐색
		int b = arr.length - 1;
		while (arr[a - 1] >= arr[b])
			b--;

		// a - 1, b swap
		int tmp = arr[a - 1];
		arr[a - 1] = arr[b];
		arr[b] = tmp;

		// a에서부터 오름차순 정렬
		int start = a;
		int end = arr.length - 1;
		while (start < end) {
			tmp = arr[start];
			arr[start] = arr[end];
			arr[end] = tmp;
			start++;
			end--;
		}
		return true;
	}
}

// 다음 순열 구하는 규칙 찾아서 swap해서 풀기 ㅠ
