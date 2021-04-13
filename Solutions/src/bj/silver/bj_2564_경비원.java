package bj.silver;

import java.io.*;
import java.util.*;

public class bj_2564_경비원 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine(), " ");
		int garo = Integer.parseInt(st.nextToken()); // 가로
		int sero = Integer.parseInt(st.nextToken()); // 세로

		int num = Integer.parseInt(br.readLine());
		ArrayList<int[]> shops = new ArrayList<>();

		for (int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int val1 = Integer.parseInt(st.nextToken());
			int val2 = Integer.parseInt(st.nextToken());
			shops.add(new int[] { val1, val2 });
		}

		st = new StringTokenizer(br.readLine(), " ");
		int d0 = Integer.parseInt(st.nextToken());
		int d1 = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		while (!shops.isEmpty()) {
			int[] shop = shops.remove(0);
			
			// 동근, 상점이 같은 라인에 있을 때.. 그냥 일직선 거리
			if (d0 == shop[0]) {
				sum += Math.abs(d1 - shop[1]);
			}
			
			// 동근 북쪽
			else if (d0 == 1) {
				// 가게 서쪽
				if (shop[0] == 3) {
					sum += d1 + shop[1];
				}
				// 가게 동쪽
				else if (shop[0] == 4) {
					sum += (garo - d1) + shop[1];					
				}
				// 가게 남쪽
				else if (shop[0] == 2) {
					sum += Math.min(d1 + shop[1] + sero, (garo - d1) + (garo - shop[1]) + sero);
				}
			}
			
			// 동근 남쪽
			else if (d0 == 2) {
				// 가게 서쪽
				if (shop[0] == 3) {
					sum += d1 + (sero - shop[1]);
				}
				// 가게 동쪽
				else if (shop[0] == 4) {
					sum += (garo - d1) + (sero - shop[1]);					
				}
				// 가게 북쪽
				else if (shop[0] == 1) {
					sum += Math.min(d1 + shop[1] + sero, (garo - d1) + (garo - shop[1]) + sero);
				}
			}
			
			// 동근 서쪽
			else if (d0 == 3) {
				// 가게 북쪽
				if (shop[0] == 1) {
					sum += d1 + shop[1];
				}
				
				// 가게 남쪽
				else if (shop[0] == 2) {
					sum += (sero - d1) + shop[1];
				}
				
				// 가게 동쪽
				else if (shop[0] == 4) {
					sum += Math.min(d1 + shop[1] + garo, (sero - d1) + (sero - shop[1]) + garo);
				}
			}
			
			// 동근 동쪽
			else if (d0 == 4) {
				// 가게 북쪽
				if (shop[0] == 1) {
					sum += d1 + (garo - shop[1]);
				}
				
				// 가게 남쪽
				else if (shop[0] == 2) {
					sum += (sero - d1) + (garo - shop[1]);
				}
				
				// 가게 서쪽
				else if (shop[0] == 3) {
					sum += Math.min(d1 + shop[1] + garo, (sero - d1) + (sero - shop[1]) + garo);
				}
			}
		}
		
		System.out.println(sum);

		br.close();
	}
}

// 첫째 수 : 1북 2남 3서 4동
// 둘째 수 : 북,남 경우 왼쪽까지의 거리 / 동,서 경우 위쪽까지의 거리


