package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2617_구슬찾기 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
		}
		
		br.close();
	}
}

// 무게가 다른 N개의 구슬.. 1~N번
// 무게가 전체의 중간인 구슬을 찾음 (무게 순서 (N+1) / 2번째)

// 양팔저울에 올려보면서 M개의 쌍을 골라 모두 알아보고, 이 결과 무게가 중간이 될 수 없는 구슬들 먼저 제거

// N=5이고, M=4 쌍의 구슬에 대해서 어느 쪽이 무거운가를 알아낸 결과가 아래에 있다.
// 구슬 2번이 구슬 1번보다 무겁다.
// 구슬 4번이 구슬 3번보다 무겁다.
// 구슬 5번이 구슬 1번보다 무겁다.
// 구슬 4번이 구슬 2번보다 무겁다.
// 1번 구슬과 4번 구슬은 무게가 중간인 구슬이 절대 될 수 없다는 것은 확실히 알 수 있다.

// 첫 줄 : N, M
// 이후 M개 줄 : 두 번호.. 앞 번호가 뒤 번호보다 무겁다는 말 (num1 > num2)