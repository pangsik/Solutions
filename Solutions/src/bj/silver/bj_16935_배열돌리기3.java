package bj.silver;
import java.io.*;
import java.util.*;

public class bj_16935_배열돌리기3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < R; i++) {
			int op = Integer.parseInt(st.nextToken());
			switch (op) {
			case 1:
				// 상하반전
				arr = op1(arr);
				break;

			case 2:
				// 좌우반전
				arr = op2(arr);
				break;

			case 3:
				// 90도 회전
				arr = op3(arr);
				break;

			case 4:
				// -90도 회전
				arr = op4(arr);
				break;

			case 5:
				// 배열 4등분 후 시계방향
				arr = op5(arr);
				break;

			case 6:
				// 배열 4등분 후 반시계방향
				arr = op6(arr);
				break;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		br.close();
	}

	// 상하반전
	static int[][] op1(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				int tmp = arr[i][j];
				arr[i][j] = arr[N - 1 - i][j];
				arr[N - 1 - i][j] = tmp;
			}
		}
		return arr;
	}

	// 좌우반전
	static int[][] op2(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				int tmp = arr[i][j];
				arr[i][j] = arr[i][M - 1 - j];
				arr[i][M - 1 - j] = tmp;
			}
		}
		return arr;
	}

	// 90도 회전
	static int[][] op3(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] newArr = new int[M][N];
		int ni = 0;
		int nj = 0;
		for (int j = 0; j < M; j++) {
			nj = 0;
			for (int i = N - 1; i >= 0; i--) {
				newArr[ni][nj] = arr[i][j];
				nj++;
			}
			ni++;
		}

		return newArr;
	}

	// -90도 회전
	static int[][] op4(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] newArr = new int[M][N];
		int ni = 0;
		int nj = 0;
		for (int j = M - 1; j >= 0; j--) {
			nj = 0;
			for (int i = 0; i < N; i++) {
				newArr[ni][nj] = arr[i][j];
				nj++;
			}
			ni++;
		}

		return newArr;
	}

	// 4등분 후 시계방향 회전
	static int[][] op5(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] newArr = new int[N][M];
		int[][] arrPt1 = copy(arr, 0, N / 2, 0, M / 2);
		int[][] arrPt2 = copy(arr, 0, N / 2, M / 2, M);
		int[][] arrPt3 = copy(arr, N / 2, N, M / 2, M);
		int[][] arrPt4 = copy(arr, N / 2, N, 0, M / 2);

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				newArr[i][j] = arrPt4[i][j]; // 4 -> 1
				newArr[i][j + M / 2] = arrPt1[i][j]; // 1 -> 2
				newArr[i + N / 2][j + M / 2] = arrPt2[i][j]; // 2 -> 3
				newArr[i + N / 2][j] = arrPt3[i][j]; // 3 -> 4
			}
		}
		return newArr;
	}

	// 4등분 후 반시계방향 회전
	static int[][] op6(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] newArr = new int[N][M];
		int[][] arrPt1 = copy(arr, 0, N / 2, 0, M / 2);
		int[][] arrPt2 = copy(arr, 0, N / 2, M / 2, M);
		int[][] arrPt3 = copy(arr, N / 2, N, M / 2, M);
		int[][] arrPt4 = copy(arr, N / 2, N, 0, M / 2);

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				newArr[i][j] = arrPt2[i][j]; // 2 -> 1
				newArr[i][j + M / 2] = arrPt3[i][j]; // 3 -> 2
				newArr[i + N / 2][j + M / 2] = arrPt4[i][j]; // 4 -> 3
				newArr[i + N / 2][j] = arrPt1[i][j]; // 1 -> 4
			}
		}
		return newArr;
	}

	// 1/4 사이즈로 잘라서 반환
	static int[][] copy(int[][] arr, int istart, int iend, int jstart, int jend) {
		int[][] result = new int[arr.length / 2][arr[0].length / 2];
		int ni = 0;
		int nj = 0;
		for (int i = istart; i < iend; i++) {
			nj = 0;
			for (int j = jstart; j < jend; j++) {
				result[ni][nj] = arr[i][j];
				nj++;
			}
			ni++;
		}
		return result;
	}
}

// 1.배열 상하반전
// 2.배열 좌우반전
// 3.배열 90도 회전
// 4.배열 -90도 회전

// #5, 6번 수행하려면 배열을 N/2 * M/2 크기 4개로 나눠야함.. 4등분
// 1 2
// 4 3

// 5.4개 배열 시계방향 회전 
// 6.4개 배열 반시계방향 회전
