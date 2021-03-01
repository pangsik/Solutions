package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2441_별찍기4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j >= i)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		
		br.close();
	}
}
