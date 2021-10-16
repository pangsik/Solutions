package swea;
import java.io.*;
import java.util.*;

public class d2_2001_파리퇴치 {
	static int getSum(int M, int startX, int startY, int[][] arr) {
		int sum = 0;
		
		for (int i = startX; i < startX + M; i++) {
			for (int j = startY; j < startY + M; j++) {
				sum += arr[i][j];
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); //파리 판 N*N
			int M = Integer.parseInt(st.nextToken()); //파리채 M*M
			
			int[][] arr = new int[N][N];
			
			//파리 판 값 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int max = 0;
			int sum = 0;
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					sum = getSum(M, i, j, arr);
					if (max < sum)
						max = sum;
				}
			}
			System.out.println("#" + tc + " " + max);
		}
	}
}
