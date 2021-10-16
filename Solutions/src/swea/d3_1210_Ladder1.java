package swea;


import java.io.*;
import java.util.*;

public class d3_1210_Ladder1 {
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] ladder = new int[100][100];

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine(); // tc번호 입력 (필요 없음)
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < 100; i++) {
				int x = 0, y = i, d = 0;
				if (!(ladder[0][i] == 1))
					continue;

				while (x < 99) {
					if (y + 1 < 100 && ladder[x][y + 1] == 1 & d >= 0) {
						y++;
						d = 1;
					}

					else if (y - 1 >= 0 && ladder[x][y - 1] == 1 && d <= 0) {
						y--;
						d = -1;
					}

					else {
						x++;
						d = 0;
					}
				}
				
				if (ladder[x][y] == 2) {
					System.out.println("#" + tc + " " + i);
					break;
				}
			}
		}

		br.close();
	}
}
