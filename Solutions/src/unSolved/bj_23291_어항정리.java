package unSolved;

import java.io.*;
import java.util.*;

public class bj_23291_어항정리 {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		
		br.close();
	}
}

// 1.물고기 수가 가장 적은 어항에 물고기 +1 해줌.. 가장 작은 어항이 여러 개라면 모두 +1 해줌
// 2.반죽을 접어 올림 (단, 2층이 없다면 1층 제일 왼쪽을 접어올림)
//   2층 이상인 칸 반죽들을 접어 올림,, 만약 접어 올렸는데 가장 오른쪽이 1층보다 길면 그만
// 3.모든 인접한 두 어항에 대해 물고기 수의 차이를 구함
//   이 차이를 5로 나눈 몫을 d, d가 0보다 크면 두 어항 중 물고기가 더 많은 곳의 물고기 d마리를 작은 곳으로 보냄
//   이건 모든 어항에 대해 동시에 일어남
// 4.다시 바닥에 일렬로 놓음. 가장 왼쪽부터, 가장 아래부터 가장 위까지 순으로 놔야함
// 5.이번에는 가운데를 중심으로 왼쪽 N/2개를 접어올림, 이를 두 번 반복
// 6.다시 3번 작업 수행, 4번 작업 수행

//