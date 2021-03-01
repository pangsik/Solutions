package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2442_별찍기5 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				if (N - j <= i + 1 && N - j > i * -1)
					System.out.print("*");
				else if (j < N)
					System.out.print(" ");
			}
			System.out.println();
		}
		
		br.close();
	}
}
