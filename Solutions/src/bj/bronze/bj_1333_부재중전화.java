package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_1333_부재중전화 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		int songTime = L;
		int ringTime = D;
		boolean flag = false;
		
		for (int i = 0; i < N - 1; i++) {
			while (ringTime < songTime + 5) { 
				if (ringTime >= songTime) {
					flag = true;
					break;
				}
				ringTime += D;
			}
			if (flag) break;
			songTime += 5 + L;
		}
		while (ringTime < songTime) {
			ringTime += D;
		}
		
		System.out.println(ringTime);
		
		br.close();
	}
}

// 