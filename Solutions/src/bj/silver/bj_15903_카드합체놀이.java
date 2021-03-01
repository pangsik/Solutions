package bj.silver;

import java.io.*;
import java.util.*;

public class bj_15903_카드합체놀이 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;// = new StringTokenizer(br.readLine(), " ");
		// StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			pq.offer(Long.parseLong(st.nextToken()));
		}

		int cnt = 0;

		while (cnt < m) {
			long sum = pq.poll() + pq.poll();
			pq.offer(sum);
			pq.offer(sum);
			cnt++;
		}
		
		long sum = 0;
		
		while(!pq.isEmpty()) {
			sum += pq.poll();
		}
		
		System.out.println(sum);

		br.close();
	}
}
