package swea;

import java.util.*;
import java.io.*;

public class d4_1223_계산기2 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_d4_1223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			char[] token = br.readLine().toCharArray();
			char[] convert = new char[N];
			int index = 0;
			Stack<Character> op = new Stack<>();
			Stack<Integer> calc = new Stack<>();

			// 후위표기법으로 변환
			for (int i = 0; i < N; i++) {
				// 0 ~ 9 들어오면 그냥 넣기
				if (token[i] >= '0' && token[i] <= '9')
					convert[index++] = token[i];
				// + 들어오면 스택 모조리 pop하고 push해주기
				else if (token[i] == '+') {
					while (!op.isEmpty()) {
						convert[index++] = op.pop();
					}
					op.push('+');
				}
				// * 들어오면 스택 peek가 +이거나 스택이 비울때 까지 pop해주기.. 그리고 push
				else if (token[i] == '*') {
					while (!op.isEmpty() && op.peek() == '*')
						convert[index++] = op.pop();
					op.push('*');
				}
			}
			// 남은 애들 다 push
			while (!op.isEmpty())
				convert[index++] = op.pop();
			//System.out.println(Arrays.toString(convert));
			
			// 후위표기식 계산하기
			for (int i = 0; i < N; i++) {
				// 숫자 만나면 push
				if (convert[i] >= '0' && convert[i] <='9') 
					calc.push(convert[i] - '0');
				
				// 연산자 만나면 숫자 두 개 뽑아서 계산 후 다시 push
				else if (convert[i] == '+')
					calc.push(calc.pop() + calc.pop());
				
				else if (convert[i] == '*')
					calc.push(calc.pop() * calc.pop());
			}
			
			sb.append("#").append(tc).append(" ").append(calc.pop()).append("\n");
		}
		System.out.print(sb);
	}
}
