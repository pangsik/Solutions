package problems;

import java.io.*;
import java.util.*;

public class Main_1620_나는야포켓몬마스터이다솜 {
	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 
		 st = new StringTokenizer(br.readLine(), " ");
		 
		 int N = Integer.parseInt(st.nextToken());
		 int M = Integer.parseInt(st.nextToken());
		 
		 HashMap<Integer, String> numMap = new HashMap<>();
		 HashMap<String, Integer> nameMap = new HashMap<>();
		 
		 for (int i = 1; i <= N; i++) {
			 String input = br.readLine();
			 numMap.put(i, input);
			 nameMap.put(input, i);
		 }
		 
		 for (int i = 0; i < M; i++) {
			 String input = br.readLine();
			 
			 // 숫자로 들어오는 경우
			 if (input.charAt(0) >= '0' && input.charAt(0) <= '9') {
				 int num = Integer.parseInt(input);
				 System.out.println(numMap.get(num));
			 }
			 
			 // 이름으로 들어오는 경우
			 else {
				 System.out.println(nameMap.get(input));
			 }
		 }
		 
		 br.close();
	}
}
