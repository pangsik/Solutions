package ssw;

import java.io.*;
import java.util.*;

public class d3_6808_규영이와인영이의카드게임 {
	static ArrayList<Integer> gyuDragon, inDragon;
	
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input_d3_3499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			gyuDragon = new ArrayList<>();
			inDragon = new ArrayList<>();
			
			// 승리조건 : sum이 total / 2 보다 높기
			int total = 0;
			int win = 0;
			int lose = 0;
			
			st = new StringTokenizer(br.readLine(), " ");
			
			// 규영이 카드 입력
			for (int i = 0; i < 9; i++)
				gyuDragon.add(Integer.parseInt(st.nextToken()));
			
			// 인영이 카드 입력 (1~18 중 규영이가 가지고 있지 않은 카드 입력)
			for (int n = 1; n <= 18; n++) {
				total += n;
				if (!gyuDragon.contains(n)) 
					inDragon.add(n);
			}
			
			total /= 2;
			
			do {
				int sum = 0;
				for (int i = 0; i < 9; i++) {
					if (inDragon.get(i) > gyuDragon.get(i))
						sum += (inDragon.get(i) + gyuDragon.get(i));
				}
				if (sum > total)
					win++;
				else
					lose++;
				
			} while (np());
			
			sb.append("#").append(tc).append(" ").append(lose).append(" ").append(win).append("\n");
		}
		
		System.out.println(sb);

		br.close();
	}
	
	public static boolean np() {
		int i = inDragon.size() - 1;
		while (i > 0 && inDragon.get(i - 1) >= inDragon.get(i)) --i;

		if (i == 0)
			return false;

		int j = inDragon.size() - 1;
		while (inDragon.get(i - 1) >= inDragon.get(j)) --j;

		swap(i - 1, j);

		int k = inDragon.size() - 1;
		while (i < k) swap(i++, k--);
		
		return true;
	}

	private static void swap(int i, int j) {
		int tmp = inDragon.get(i);
		inDragon.set(i, inDragon.get(j));
		inDragon.set(j, tmp);
	}
}
