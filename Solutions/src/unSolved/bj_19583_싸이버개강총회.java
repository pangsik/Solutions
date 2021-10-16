package unSolved;

import java.io.*;
import java.util.*;

public class bj_19583_싸이버개강총회 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] SEQ = br.readLine().split(" ");
		String[] inEnd = SEQ[0].split(":");
		String[] outStart = SEQ[1].split(":");
		String[] outEnd = SEQ[2].split(":");
		
		int startInH = Integer.parseInt(inEnd[0]) - 1;
		int startInM = Integer.parseInt(inEnd[1]);
		int endInH = Integer.parseInt(inEnd[0]);
		int endInM = Integer.parseInt(inEnd[1]);
		
		int startOutH = Integer.parseInt(outStart[0]);
		int startOutM = Integer.parseInt(outStart[1]);
		int endOutH = Integer.parseInt(outEnd[0]);
		int endOutM = Integer.parseInt(outEnd[1]);
		
		
		HashSet<String> inHs = new HashSet<String>();
		HashSet<String> outHs = new HashSet<String>();
		
		String input = "";
		while ((input = br.readLine()) != null) {
			String[] info = input.split(" ");
			
			if (info[0].equals(""))
				break;
			
			String[] time = info[0].split(":");
			int H = Integer.parseInt(time[0]);
			int M = Integer.parseInt(time[1]);
			
			// 1.입장 가능 시간 내에 들어온 회원들 집합에 저장
			if (H >= startInH && H <= endInH) {
				if (H == startInH) {
					if (startInH == endInH) {
						if (M >= startInM && M <= endInM) {
							inHs.add(info[1]);
						}
					}
					else if (M >= startInM) {
						inHs.add(info[1]);
					}
				}
				else if (H == endInH) {
					if (M <= endInM)
						inHs.add(info[1]);
				}
				else {
					inHs.add(info[1]);
				}
			}
			
			// 2.퇴장 가능 시간 내에 들어온 회원들 이미 집합에 있으면(입장을 제시간에 했으면) 집합에 다시 저장
			// 입장 여부 확인
			if (!inHs.contains(info[1]))
				continue;
			
			if (H >= startOutH && H <= endOutH) {
				if (H == startOutH) {
					if (startOutH == endOutH) {
						if (M >= startOutM && M <= endOutM) {
							outHs.add(info[1]);
						}
					}
					else if (M >= startOutM) {
						outHs.add(info[1]);
					}
				}
				else if (H == endOutH) {
					if (M <= endOutM) {
						outHs.add(info[1]);
					}
				}
				else {
					outHs.add(info[1]);
				}
			}
		}
		
		System.out.println(inHs);
		System.out.println(outHs);
		System.out.println(outHs.size());
		
		br.close();
	}
}



// 1.개총 시작 전, 입장 여부 확인
//  1-1.시작 한 시간 전 대화 기록이 있는 닉네임 보고 체크
//  1-2.개총 시작하자마자 채팅을 남긴 회원도 제 시간 입장으로 간주
// 2.개총 끝내고, 스트리밍 끝날 때 까지 학회원 퇴장 여부 확인
//  2-1.개총 끝(끝나자마자) ~ 스트리밍 끝(끝나자마자) 채팅 기록도 제 시간 퇴장으로 간주 
// 입장, 퇴장 모두 확인되면 출석 인정

// 00:00 개총 시작 전 대기 시간

// 채팅이 시간순으로 입력..
// 1.입장 가능 시간 먼저 구해서 그 범위안에 드는 회원들 집합에 저장 (입장 회원 체크)
// 2.퇴장 가능 시간 구해서 그 사이에 채팅 남긴 회원들 집합에 있는지 검사하고 있으면 집합에 넣기
// 3.집합 크기 리턴


/*
12:00 13:40 15:20
10:59 a
11:00 b
11:30 c
11:59 d
12:00 e
12:01 f
13:39 a
13:40 b
14:30 c
14:55 AA
15:19 d
15:20 e
15:21 f
 */


/*
12:00 13:40 13:50
10:59 a
11:00 b
11:30 c
11:59 d
12:00 e
12:01 f
13:39 b
13:40 a
13:45 f
13:46 e
13:50 c
13:51 d
 */




