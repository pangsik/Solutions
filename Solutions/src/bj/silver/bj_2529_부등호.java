package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2529_부등호 {
	static int k;
	static char[] bdh;
	static boolean isSelected[];
	static int[] numbers;
	static StringBuilder max = new StringBuilder();
	static StringBuilder min = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		k = Integer.parseInt(br.readLine());
		bdh = new char[k];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < k; i++) {
			bdh[i] = st.nextToken().charAt(0);
		}

		numbers = new int[k + 1];
		isSelected = new boolean[10];
		getMax(0);
		
		numbers = new int[k + 1];
		isSelected = new boolean[10];
		getMin(0);
		
		System.out.println(max);
		System.out.println(min);

		br.close();
	}

	static void getMax(int cnt) {
		if (cnt >= 2) {
			switch (bdh[cnt - 2]) {
			case '<':
				if (numbers[cnt - 2] > numbers[cnt - 1])
					return;
				break;
				
			case '>':
				if (numbers[cnt - 2] < numbers[cnt - 1])
					return;
				break;
			}
		}
		
		if (cnt == k + 1) {
			for (int i = 0; i < k + 1; i++) {
				max.append(numbers[i]);
			}
			return;
		}

		for (int i = 9; i >= 0; i--) {
			if (max.length() != 0)
				return;
			if (isSelected[i])
				continue;

			numbers[cnt] = i;
			isSelected[i] = true;

			getMax(cnt + 1);
			isSelected[i] = false;
		}
	}

	static void getMin(int cnt) {
		if (cnt >= 2) {
			switch (bdh[cnt - 2]) {
			case '<':
				if (numbers[cnt - 2] > numbers[cnt - 1])
					return;
				break;
				
			case '>':
				if (numbers[cnt - 2] < numbers[cnt - 1])
					return;
				break;
			}
		}
		
		if (cnt == k + 1) {
			for (int i = 0; i < k + 1; i++) {
				min.append(numbers[i]);
			}
			return;
		}


		for (int i = 0; i < 10; i++) {
			if (min.length() != 0)
				return;
			if (isSelected[i])
				continue;

			numbers[cnt] = i;
			isSelected[i] = true;

			getMin(cnt + 1);
			isSelected[i] = false;
		}
	}
}

// 