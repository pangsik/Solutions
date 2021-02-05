package unSolved;
import java.io.*;
import java.util.*;

public class bj_17225_세훈이의선물가게 {

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/input_d3_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int A = Integer.parseInt(st.nextToken()); // 상민 포장속도
		int B = Integer.parseInt(st.nextToken()); // 지수 포장속도
		int N = Integer.parseInt(st.nextToken()); // 손님 수
		
		// 큐 3개에 각각 저장?
		Queue<Integer> ti = new LinkedList<>();
		Queue<Character> ci = new LinkedList<>();
		Queue<Integer> mi = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ti.offer(Integer.parseInt(st.nextToken())); // N번 손님의 주문 시각
			ci.offer(st.nextToken().charAt(0)); // 포장지 색깔
			mi.offer(Integer.parseInt(st.nextToken())); // 주문 개수
		}
		
		// 반복 돌면서 시간? 재면서.. 
		int time = 0;
		int pNum = 1;
		int n = 1;
//		while (true) {
//			// 주문 시간 확인
//			if (time )
//			
//			time++;
//		}
		
		pw.close();
		br.close();
	}
}

// 몇 개 살지, 어떤 색 포장받을지 선택 가능
// 파란 포장지 -> 상민 : A초
// 빨간 포장지 -> 지수 : B초
// 남은 선물 중 가장 앞 선물 가져와서 포장함 (주문 받은 개수만큼)
// 동시에 선물을 가져와야하면 상민이가 먼저


// A, B, 손님 수 입력
// 1 ~ N번 손님 주문 시간, 포장지 색, 선물 개수("B", "R") 주어짐
// 주문시간 오름차순으로 입력 들어오고 동시에 주문한 손님은 없다.

// 상민 포장 선물 개수 출력, 상민 포장 선물 번호 출력
// 지수 ~~~