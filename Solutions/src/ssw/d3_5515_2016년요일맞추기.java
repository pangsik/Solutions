package ssw;

import java.io.*;
import java.util.*;

public class d3_5515_2016년요일맞추기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int[] days = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int answer = d + 3;
			
			for (int i = 1; i < m; i++) {
				answer += days[i];
			}
			
			sb.append('#').append(tc).append(' ').append(answer % 7).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}
// 월	화	수	목	금	토	일
// 0	1	2	3	4	5	6
// 1월 1일은 금요일 (4)