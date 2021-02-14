package bj.silver;
import java.io.*;
import java.util.*;

public class bj_2563_색종이 {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		boolean[][] paper = new boolean[100][100];
		int sum = 0;
		
		int N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (!paper[x + i][y + j]) {
						paper[x + i][y + j] = true;
						sum += 1;
					}
					
				}
			}
		}
		
		System.out.println(sum);
		
		br.close();
	}
}

