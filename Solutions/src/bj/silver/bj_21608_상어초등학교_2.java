package bj.silver;

import java.io.*;
import java.util.*;

/*
 * @date : 21.10.20
 * 풀이 시간 : 70분
 * 코멘트
 * 문제 좀 정확히 읽고 조건 체크 꼼꼼히, 제대로 이해하고 풀자
 * 내가 이해한게 틀릴 수 있다는 생각을 가지자
 */

public class bj_21608_상어초등학교_2 {
	static class Student {
		int num;
		int[] like = new int[4];
		
		public Student(int num, int[] like) {
			this.num = num;
			this.like = like;
		}
	}
	
	static int N, answer;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;
	static ArrayList<Student> studentList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		studentList = new ArrayList<>();
		
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int[] like = new int[4];
			for (int j = 0; j < 4; j++) {
				like[j] = Integer.parseInt(st.nextToken());
			}
			studentList.add(new Student(num, like));
		}
		
		answer = 0;
		Solution();
		
		System.out.println(answer);
		
		br.close();
	}
	private static void Solution() {
		// 모든 학생들마다 수행
		for (int i = 0; i < N * N; i++) {
			ArrayList<int[]> list = new ArrayList<int[]>(); // 인접 친구 수, 빈 칸 수 저장
			Student cur = studentList.get(i);
			
			setLists(cur.like, list);
			
			// 인접 친구가 가장 많은 칸이 한 칸 이라면 넣고 땡
			if (list.size() == 1) {
				int[] student = list.get(0);
				int x = student[0];
				int y = student[1];
				map[x][y] = cur.num;
				continue;
			}
			
			// 인접 친구 칸이 여러 칸일 때, 인접 빈 칸 가장 많은 칸을 또 리스트에 따로 저장, 만약 그 길이가 1이라면 넣고 땡
			ArrayList<int[]> list2 = new ArrayList<int[]>(); // 인접 빈 칸 수 가장 많은 애들 저장
			for (int[] curList : list) {
				if (list2.isEmpty()) {
					list2.add(curList);
					continue;
				}
				
				int max = list2.get(0)[3];
				
				if (curList[3] > max) {
					list2.clear();
					list2.add(curList);
				}
				
				else if (curList[3] == max) {
					list2.add(curList);
				}
			}
			
			if (list2.size() == 1) {
				int[] student = list2.get(0);
				int x = student[0];
				int y = student[1];
				map[x][y] = cur.num;
				continue;
			}
			
			// 행, 열 순서대로 정렬
			Collections.sort(list2, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if (o1[0] != o2[0])
						return Integer.max(o1[0], o2[0]);
					else
						return Integer.max(o1[1], o2[1]);
				}
			});
			
			int[] student = list2.get(0);
			int x = student[0];
			int y = student[1];
			map[x][y] = cur.num;
		}
		
		getScore();
	}
	
	private static void getScore() {
//		얘네로 정렬 안되는데 뭘 잘못했나..?
		studentList.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return Integer.compare(o1.num, o2.num);
			}
		});
		
//		Collections.sort(studentList, new Comparator<Student>() {
//			@Override
//			public int compare(Student o1, Student o2) {
//				return Integer.compare(o1.num, o2.num);
//			}
//		});
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int curNum = map[i][j];
				int[] like = new int[4];
				
				for (Student s : studentList) {
					if (s.num == curNum) {
						like = s.like;
						break;
					}
				}
				
				int cnt = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					if (!check(nx, ny)) {
						continue;
					}
					
					for (int n = 0; n < 4; n++) {
						if (like[n] == map[nx][ny]) {
							cnt++;
							break;
						}
					}
				}
				
				answer += Math.pow(10, cnt - 1);
			}
		}
	}
	
	private static void setLists(int[] like, ArrayList<int[]> list) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0)
					continue;
				
				int likeCnt = 0;
				int emptyCnt = 0;
				// 4방향 탐색
				for (int d = 0; d < 4; d++) {
					int nx = i + di[d];
					int ny = j + dj[d];
					
					if (!check(nx, ny))
						continue;
					
					// 빈 칸
					if (map[nx][ny] == 0) {
						emptyCnt++;
						continue;
					}
					
					// 좋아하는 친구
					for (int n = 0; n < 4; n++) {
						if (map[nx][ny] == like[n]) {
							likeCnt++;
							break;
						}
					}
				}
				
				// list 인접 친구 최대값 비교 후 갱신 (빈 칸 카운트도 같이 넣어줌)
				if (!list.isEmpty()) {
					if (likeCnt > list.get(0)[2]) {
						list.clear();
						list.add(new int[] { i, j, likeCnt, emptyCnt });
					}
					else if (likeCnt == list.get(0)[2]) {
						list.add(new int[] { i, j, likeCnt, emptyCnt });
					}
				} 
				
				else {
					list.add(new int[] { i, j, likeCnt, emptyCnt });
				}
			}
		}
	}
	
	private static boolean check(int nx, int ny) {
		if (nx < 0 || nx >= N || ny < 0 || ny >= N)
			return false;
		
		return true;
	}
}

// N*N
// 1 ~ N^2번 까지의 학생 존재
// (1,1) ~ (N,N) => 입력 받을 때 -1 하기
// 인접 칸 : 상하좌우
// 1.빈 칸 중 좋아하는 학생이 인접 칸에 가장 많은 칸으로 자리를 정함
// 2.1을 만족하는 칸이 여러 개라면, 인접 칸 중 빈 칸이 가장 많은 칸으로 자리를 정함
// 3.2를 만족하는 칸도 여러 개라면 행 번호 가장 작은 칸, 그것도 여러 개면 열 번호 가장 작은 칸
// 자리 배치 모두 끝난 후 학생들의 만족도 조사
// 인접 칸에 좋아하는 학생 수를 구함
// 0 : 0점
// 1 : 1점
// 2 : 10점
// 3 : 100점
// 4 : 1000점

// Student 클래스 작성 (좌표, 좋아하는 학생 리스트)
// 학생별로 자리 쭉 돌면서 인접 좋아하는 학생 수(list1), 인접 빈 칸 자리 수(list2)를 기록
// list1의 길이가 1보다 크다면 list2를 체크
// 





























