package bj.gold;

import java.util.*;
import java.io.*;

public class bj_15684_사다리조작 {
	static int N, M, H;
	static boolean ladder[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 세로선 수
		M = Integer.parseInt(st.nextToken()); // 이은 다리 수
		H = Integer.parseInt(st.nextToken()); // 점선 수
		
		ladder = new boolean[H][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			// b, b+1번 세로선을 a번 위치에서 연결 (인덱스 0부터 시작하기 위해 -1)
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			ladder[a][b] = true;
		}
		
		for (int i = 0; i < H; i++) {
			System.out.println(Arrays.toString(ladder[i]));
		}
		
		br.close();
	}
}

// N : 세로선
// M : 가로선 (실제로 연결된 다리 말하는거)
// H : 세로선마다 가로선을 놓을 수 있는 위치의 개수 (가로 점선)
// a, b : b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결했다는 의미