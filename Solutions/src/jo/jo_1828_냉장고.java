package jo;

import java.io.*;
import java.util.*;

public class jo_1828_냉장고 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; // new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] xy = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			xy[i][0] = Integer.parseInt(st.nextToken());
			xy[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(xy, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				int diff = Integer.compare(o1[0], o2[0]);
				return diff != 0 ? diff : Integer.compare(o1[1], o2[1]);
			}
		});
		
		int t = 0;
		int answer = 1;
		
		for (int i = 1; i < N; i++) {
			// 현재 값이 비교대상에 포함될 때.. 비교대상을 현재 값으로 바꿔줌
			if (xy[t][1] >= xy[i][1]) {
				t = i;
			}
			
			// 현재 값, 비교대상이 크로스일 때.. 머 딱히? 아무것도 안함 그냥 냉장고 하나에 집어넣기
			else if (xy[t][1] >= xy[i][0]) {
				
			}
			
			// 겹치지 않을 때.. 냉장고 한 대 늘려주고 비교대상 바꿔줌
			else {
				answer++;
				t = i;
			}
		}
		
		System.out.println(answer);
		
		br.close();
	}
}

// a 시작점이 b 시작점보다 작고(소팅해주니 무시해도됨), a 끝점이 b 끝점보다 크면 b는 a에 속한다
// b가 a에 속하는 경우.. 비교 끝점을 a가 아니고 b로 바꿔준다
// a의 시작점이 b의 끝점보다 작으면 크로스