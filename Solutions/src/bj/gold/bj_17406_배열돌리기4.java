package bj.gold;

import java.io.*;
import java.util.*;

public class bj_17406_배열돌리기4 {
	// 배열, 회전 정보, 최소값
	static int N, M, K;
	static int[] r, c, s;
	static int[][] A; // 원본 배열
	static int[][] arr; // 복사 배열 (돌릴놈)
	static int min = Integer.MAX_VALUE;

	// 우 하 좌 상
	static final int[] di = { 0, 1, 0, -1 };
	static final int[] dj = { 1, 0, -1, 0 };

	// 순열 구하기
	static int[] numbers;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		// 배열 A의 크기 N * M, 회전 연산의 개수 K 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 원본 배열 A, 복사 배열 arr, 회전 연산의 정보 r c s를 K개씩 저장할 배열 초기화
		A = new int[N][M];
		arr = new int[N][M];
		r = new int[K];
		c = new int[K];
		s = new int[K];

		// 순열 생성을 위한 배열 초기화
		numbers = new int[K];
		isSelected = new boolean[K];

		// 배열 A 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}

		// 회전 연산 r, c, s 입력 (배열 인덱스를 맞춰주기 위해 r, c는 -1하여 저장)
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r[i] = Integer.parseInt(st.nextToken());
			c[i] = Integer.parseInt(st.nextToken());
			s[i] = Integer.parseInt(st.nextToken());
		}

		// 가능한 회전 연산 순서를 모두 구해서 다 돌려보고 그 중 가장 작은 값을 구하자
		perm(0);
		System.out.println(min);

		br.close();
	}

	// 배열 초기화 (복사)
	static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				arr[i][j] = A[i][j];
		}
	}

	// 배열 회전 연산
	static void rotate(int n) {
		// 시작점(r-s, c-s) 끝점(r+s, c+s) 저장
		int istart = r[n] - s[n] - 1;
		int iend = r[n] + s[n];
		int jstart = c[n] - s[n] - 1;
		int jend = c[n] + s[n];

		// 현재 위치 i, j
		int i = istart;
		int j = jstart;
		int d = 0; // 0 우 1 하 2 좌 3 상

		// 값 교체를 위한 변수
		int nextVal = arr[i][j];
		int tmp = 0;

		int ss = s[n];
		// s 값이 0보다 크면 계속 진행
		while (ss > 0) {
			int ni = i + di[d];
			int nj = j + dj[d];

			// ni, nj 범위 체크 후 가능하면 스왑 진행
			if (ni >= istart && ni < iend && nj >= jstart && nj < jend) {
				tmp = arr[ni][nj];
				arr[ni][nj] = nextVal;
				nextVal = tmp;
				i = ni;
				j = nj;
			}

			// 범위를 벗어나면 방향을 바꿔줌
			else {
				d++;
				// 한 바퀴를 다 돌았을 경우, 다시 처음 방향으로 바꿔주고 ss 1 감소.. 시작점, 끝점 +- 해주고 인덱스 현재위치 재설정
				if (d > 3) {
					d = 0;
					ss--;
					
					i = ++istart;
					j = ++jstart;
					--iend;
					--jend;
					nextVal = arr[i][j];
				}
			}
		}
	}

	// 행별로 합을 구해 가장 작은 값을 저장
	static void getMin() {
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++)
				sum += arr[i][j];

			if (min > sum)
				min = sum;
		}
	}

	// 주어진 회전 연산 가능한 순서 모두 구하기..
	static void perm(int cnt) {
		if (cnt == K) {
			init();
			for (int i = 0; i < K; i++)
				rotate(numbers[i]);
			getMin();

			return;
		}

		for (int i = 0; i < K; i++) {
			if (isSelected[i])
				continue;

			numbers[cnt] = i;
			isSelected[i] = true;

			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
}
