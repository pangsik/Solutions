package swea;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.16
 * 풀이 시간 : 50분
 * 코멘트 : 어레리에 추가하고 제거하는거라 인덱스 신경 잘 써줘야함
 * 		   귀찮다고 인덱스 직접 넣어 삭제하려다가 추가 후 젤 뒤에꺼 삭제할 때 8번 인덱스를 삭제해야하는데 7번 인덱스를 삭제하는 실수 함
 * 		  .size() 이용했다면 이런 실수 없었을 듯
 */

public class Solution_sw_4013_특이한자석_1 {
	static int K;
	static ArrayList<Integer>[] magnets = new ArrayList[4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				magnets[i] = new ArrayList<Integer>();
				for (int j = 0; j < 8; j++) {
					magnets[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int magnetNum = Integer.parseInt(st.nextToken()) - 1;
				int dirInt = Integer.parseInt(st.nextToken());
				boolean dir;
				if (dirInt == 1) {
					dir = true;
				}
				else {
					dir = false;
				}
				
				rotate(magnetNum, dir, 0);
			}
			
			int answer = getScore();
			
			System.out.println("#" + tc + " " + answer);
		}
		
		br.close();
	}
	
	private static void rotate(int magnetNum, boolean dir, int lr) {
		// lr
		// 0 : 양방향
		// 1 : 오른쪽
		// -1 : 왼쪽
		
		// dir
		// true : 시계 방향 회전
		// false : 반시계 방향 회전
		
		// 오른쪽 자석과 붙는 인덱스 : 2
		// 왼쪽 자석과 붙는 인덱스 : 6
		
		// 양방향 or 오른쪽 방향 진행 && 오른쪽 자석이 존재한다면 수행
		if (lr >= 0 && magnetNum + 1 < 4) {
			// 오른쪽 자석이 돌아가는지 확인
			if (magnets[magnetNum].get(2) != magnets[magnetNum + 1].get(6)) {
				// 돌아간다면 재귀 타고 들어감
				rotate(magnetNum + 1, !dir, 1);
				
			}
		}
		
		// 왼쪽 자석이 존재한다면 수행
		if (lr <= 0 && magnetNum - 1 >= 0) {
			// 왼쪽 자석이 돌아가는지 확인
			if (magnets[magnetNum].get(6) != magnets[magnetNum - 1].get(2)) {
				// 돌아간다면 재귀 타고 들어감
				rotate(magnetNum - 1, !dir, -1);
				
			}
		}

		// 나오면서 회전
		// 시계방향
		if (dir) {
			magnets[magnetNum].add(0, magnets[magnetNum].get(7));
			magnets[magnetNum].remove(8);
		}
		
		// 반시계방향
		else {
			magnets[magnetNum].add(magnets[magnetNum].get(0));
			magnets[magnetNum].remove(0);
		}
	}
	
	private static int getScore() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += magnets[i].get(0) * Math.pow(2, i);
		}
		return sum;
	}
}

// 자석,, 8개 방향  극
// 총 4개 일렬로 배치되어 있음
// 자석이 회전할 때 붙어있는 자석의 극이 다를 경우 반대 방향으로 1칸 회전 (같으면 회전 안 함)
// 최종적으로 0번 인덱스의 극에 따라 점수 부여 (N극은 모두 0점) N극 0, S극 1
// 1번 자석 : S극 1점
// 2번 자석 : S극 2점
// 3번 자석 : S극 4점
// 4번 자석 : S극 8점
// 시계방향 1, 반시계방향 -1

// 자석끼리 붙는 인덱스 체크, 회전하게 되면 젤 뒤에꺼 떼서 앞에 붙이거나 그 반대
// 재귀, 모든 자석이 한 번에 돌아가야 하기 때문에 재귀 타서 젤 안쪽 들어간 후 나오면서 회전시키기
