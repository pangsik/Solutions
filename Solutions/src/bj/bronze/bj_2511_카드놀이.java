package bj.bronze;

import java.io.*;
import java.util.*;

public class bj_2511_카드놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		int[] A = new int[10];
		int[] B = new int[10];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 10; i++)
			A[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 10; i++)
			B[i] = Integer.parseInt(st.nextToken());

		char winner = 'D';
		int scoreA = 0;
		int scoreB = 0;
		for (int i = 0; i < 10; i++) {
			// A 승
			if (A[i] > B[i]) {
				scoreA += 3;
				winner = 'A';
			}

			// B 승
			else if (A[i] < B[i]) {
				scoreB += 3;
				winner = 'B';
			}

			// 비김
			else {
				scoreA++;
				scoreB++;
			}
		}
		
		// 총점 비교 안하고 마지막 승자만 생각해서 계속 틀림..
		// 아래 if-else문으로 총점 비교 후 승자 결정.. 총점이 같다면 마지막 승자
		if (scoreA > scoreB)
			winner = 'A';
		else if (scoreA < scoreB)
			winner = 'B';
		
		System.out.println(scoreA + " " + scoreB);
		System.out.println(winner);
		
		br.close();
	}
}

// A, B한테 0~9 숫자카드 줌
// 매 라운드 진행, 번호 높은사람 승점 +1
// 비기면 둘 다 +1
// 두 사람 최종 승점이 같다면 마지막에 이긴사람이 승
// 첨부터 끝까지 다 비긴거 아니면 비기는 경우는 없다