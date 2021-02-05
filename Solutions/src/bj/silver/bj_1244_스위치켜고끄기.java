package bj.silver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1244_스위치켜고끄기 {
	public static void main(String[] args) throws Exception {
		// 스위치 개수 입력받고 그 크기만큼 boolean 배열 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] swch = new boolean[N];

		// swch 배열에 스위치 상태 넣어줌
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			swch[i] = st.nextToken().equals("1") ? true : false;

		// 학생 수 입력받고 그만큼 반복
		int sN = Integer.parseInt(br.readLine());
		for (int i = 0; i < sN; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			boolean male = st.nextToken().equals("1") ? true : false; // 남 true 여 false
			int cardNum = Integer.parseInt(st.nextToken());

			// 남자 : 카드 번호 배수 자리 모두 토글
			if (male) {
				for (int j = 0; j < N; j++) {
					if ((j + 1) % cardNum == 0)
						swch[j] = !swch[j];
				}
			}

			// 여자 : 카드 번호 스위치 중심으로 좌우 대칭구간 최대한 길게 잡아서 모두 토글
			else {
				// 가운데 카드 토글
				int t = 1;
				cardNum--;
				swch[cardNum] = !swch[cardNum];
				while (cardNum + t < N && (cardNum - t) >= 0) {
					if (swch[cardNum + t] == swch[cardNum - t]) {
						swch[cardNum + t] = !swch[cardNum + t];
						swch[cardNum - t] = !swch[cardNum - t];
						t++;
					} else
						break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			if (swch[i])
				sb.append("1 ");
			else
				sb.append("0 ");

			if (((i + 1) % 20) == 0) {
				System.out.print(sb);
				sb.setLength(0);
				System.out.println();
			}
		}
		System.out.print(sb);

		br.close();
	}
}

// 1 ~ N 자연수 카드를 하나씩 받음
// 남학생 : 카드 번호의 배수 자리 스위치 모두 토글
// 
// 여학생 : 카드 번호 스위치 중심으로 좌우 대칭 최대한 긴 구간으로 잡아서 토글 (이때, 스위치 개수는 항상 홀수겠죠?)
// 
// 스위치 개수 / 스위치 상태 / 학생 수 / 남학생1 여학생2 카드번호
//
// 출력 시 스위치 수가 20 넘어가면 한 줄에 20개씩 출력