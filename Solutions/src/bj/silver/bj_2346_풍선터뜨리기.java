package bj.silver;
import java.util.Scanner;

public class bj_2346_풍선터뜨리기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] balloons = new int[N];
		for (int i = 0; i < N; i++) {
			balloons[i] = sc.nextInt();
		}

		int[] used = new int[N]; // 터진 풍선 표시
		int idx = 0; //
		int move = balloons[0]; // 이동
		int output[] = new int[N]; // 출력용 배열
		for (int i = 0; i < N - 1; i++) {
			used[idx] = 1; // 터진 풍선 인덱스 1
			output[i] = idx + 1; // 터진 풍선 위치 저장 (순서대로)
			move = balloons[idx]; // 다음 이동값 저장

			for (int j = 0; j < Math.abs(move); j++) {
				if (move < 0)
					idx--;
				else
					idx++;

				if (idx >= N)
					idx = 0;

				else if (idx < 0)
					idx = N - 1;

				// used == 1이고 음수일 땐 좌로 한 칸 더, 양수일 땐 우로 한 칸 더
				if (used[idx] == 1) {
					if (move > 0)
						move++;
					else
						move--;
				}
			}
		}
		output[N - 1] = idx + 1;
		for (int i = 0; i < N; i++)
			System.out.print(output[i] + " ");

		sc.close();
	}
}
/*
5
3 2 1 -3 -1


 */