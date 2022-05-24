package problems;

import java.io.*;
import java.util.*;

public class Main_1697_숨바꼭질 {
	static int N, K, MAX_INDEX;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		MAX_INDEX = 200000;
		visited = new boolean[MAX_INDEX];
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		dq.add(N);
		visited[N] = true;
		
		int time = 0;
		boolean flag = false;
		while (!dq.isEmpty()) {
			int size = dq.size();
			
			for (int i = 0; i < size; i++) {
				int cur = dq.poll();
				
				if (cur == K) {
					flag = true;
					break;
				}
				
				int left = cur - 1;
				int right = cur + 1;
				int teleport = cur * 2;
				
				if (check(left)) {
					dq.add(left);
					visited[left] = true;
				}
				
				if (check(right)) {
					dq.add(right);
					visited[right] = true;
				}
				
				if (check(teleport)) {
					dq.add(teleport);
					visited[teleport] = true;
				}
			}
			
			if (flag)
				break;

			time++;
		}
		
		System.out.println(time);
		
		br.close();
	}

	private static boolean check(int target) {
		return target >= 0 && target < MAX_INDEX && !visited[target];
	}
}

// 현재 갈 수 있는곳 확인하고 아직 가본적 없으면 큐에 넣기
// visited를 int형 배열로 선언해서 최단시간에 해당 위치로 가는 경우를 저장
// 가장 처음 시작 위치는 0이라도 다시 방문 안하게 처리
// 배열 크기는?