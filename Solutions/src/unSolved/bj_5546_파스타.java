package unSolved;

import java.util.*;
import java.io.*;

public class bj_5546_파스타 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] plan = new int[N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int day = Integer.parseInt(st.nextToken()) - 1;
			int pasta = Integer.parseInt(st.nextToken());
			plan[day] = pasta;
		}
		
		for (int d = 0; d < 2; d++) {
			if (plan[d] > 0) {
				
			}
			
		}
		
		br.close();
	}
}



// N일간 매일 세 종류 중 한 종류 선택
// 3일 이상 연속으로 같은 파스타 먹지 않음
// N일 중 K일에 먹을 파스타는 미리 정해둠

// 2일까지만 연속으로 먹을 수 있음
