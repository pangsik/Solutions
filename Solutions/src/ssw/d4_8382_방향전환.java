package ssw;

import java.io.*;
import java.util.*;

public class d4_8382_방향전환 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int diffx = Math.abs(x1 - x2);
			int diffy = Math.abs(y1 - y2);
			
			int answer = Math.max(diffx, diffy) * 2 - ((diffx + diffy) % 2);
			
			sb.append('#').append(tc).append(' ').append(answer).append('\n');
		}
		
		System.out.println(sb);
		
		br.close();
	}
}

// t = x+y/2
// 2t + math.abs(x-t) + math.abs(y-t)
