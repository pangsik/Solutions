package ssw;


import java.io.*;
import java.util.*;

public class d3_1208_Flatten {
	static int dump(int dump, int arr[], int maxidx, int minidx) {
		if (dump == 0)
			return arr[maxidx] - arr[minidx];

		arr[maxidx]--;
		arr[minidx]++;

		for (int i = 0; i < 100; i++) {
			if (arr[i] > arr[maxidx]) {
				maxidx = i;
			}

			if (arr[i] < arr[minidx]) {
				minidx = i;
			}
		}

		return dump(dump - 1, arr, maxidx, minidx);
	}

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_1208.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int dump = Integer.parseInt(st.nextToken()) + 1;

			st = new StringTokenizer(br.readLine(), " ");
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			System.out.println("#" + tc + " " + dump(dump, arr, 0, 0));
		}
		br.close();
	}
}
