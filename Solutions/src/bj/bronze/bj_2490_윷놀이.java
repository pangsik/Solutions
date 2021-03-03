package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2490_윷놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		//StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			int cnt = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) 
				if (st.nextToken().equals("0")) cnt++;
			
			if (cnt == 4) System.out.println("D");
			else if (cnt == 3) System.out.println("C");
			else if (cnt == 2) System.out.println("B");
			else if (cnt == 1) System.out.println("A");
			else if (cnt == 0) System.out.println("E");
		}
		
		br.close();
	}
}

// 배 0 등 1
// 도개걸윷모 ABCDE