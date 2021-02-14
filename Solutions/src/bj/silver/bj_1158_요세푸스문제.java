package bj.silver;
import java.io.*;
import java.util.*;

public class bj_1158_요세푸스문제 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			arr.add(i);
		}
		
		int idx = K - 1;
		
		sb.append("<");
		while (arr.size() > 0) {
			sb.append(String.valueOf(arr.get(idx))).append(", ");
			arr.remove(idx--);
			
			for (int i = 0; i < K; i++) {
				if (++idx >= arr.size())
					idx = 0;
			}
		}
		sb.setLength(sb.length() - 2);
		sb.append(">");
		
		System.out.println(sb);
		
		br.close();
	}
}
// 0 1 2 3 4 5 6
// 1 2 3 4 5 6 7
// 3씩 jump 하면서 삭제