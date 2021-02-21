package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1703_생장점 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			int level = Integer.parseInt(st.nextToken());
			if (level == 0) break;
			
			int answer = 1;
			
			for (int i = 0; i < level; i++) {
				int splitfactor = Integer.parseInt(st.nextToken());
				int cut = Integer.parseInt(st.nextToken());
				
				answer *= splitfactor;
				answer -= cut;
			}
			System.out.println(answer);		
		}
		
		br.close();
	}
}

// a = 나무의 나이
// 2a개의 정수가 공백을 두고 주어짐
// 이 정수들은 splitting factor와 가지치기 한 가지의 수가 레벨별로 나열