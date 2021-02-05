package ssw;
import java.io.*;
import java.util.*;

public class d3_1873_상호의배틀필드 {
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static char[] cn = { '^', 'v', '<', '>' };
	// 0상 1하 2좌 3우

	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("res/input_d3_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			// 맵 크기 받아서 H*W 크기 맵 생성
			st = new StringTokenizer(br.readLine(), " ");
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			char[][] map = new char[H][W];

			int col = 0, row = 0, d = 0; // 대포 좌표

			// 맵 정보 입력 및 대포 위치 저장
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						col = i;
						row = j;
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();
			for (int i = 0; i < N; i++) {
				switch (str.charAt(i)) {
				case 'U':
					if (col - 1 >= 0 && map[col - 1][row] == '.')
						map[col--][row] = '.';
					map[col][row] = '^';
					break;

				case 'D':
					if (col + 1 < H && map[col + 1][row] == '.')
						map[col++][row] = '.';
					map[col][row] = 'v';

					break;

				case 'L':
					if (row - 1 >= 0 && map[col][row - 1] == '.')
						map[col][row--] = '.';
					map[col][row] = '<';

					break;

				case 'R':
					if (row + 1 < W && map[col][row + 1] == '.')
						map[col][row++] = '.';
					map[col][row] = '>';

					break;

				case 'S':
					switch (map[col][row]) {
					case '^':
						d = 0;
						break;
					case 'v':
						d = 1;
						break;
					case '<':
						d = 2;
						break;
					case '>':
						d = 3;
						break;
					}
					int ccol = col;
					int crow = row;
					int ni = ccol + di[d];
					int nj = crow + dj[d];
					while (ni < H && ni >= 0 && nj < W && nj >= 0 && map[ni][nj] != '#') {
						if (map[ni][nj] == '*') {
							map[ni][nj] = '.';
							break;
						}		
						ccol = ni;
						crow = nj;
						
						ni = ccol + di[d];
						nj = crow + dj[d];
					}
					break;
				}
			}
			System.out.print("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++)
					System.out.print(map[i][j]);
				System.out.println();
			}
		}

		br.close();
	}
}

//.	평지 (전차 in 가능)
//*	벽돌
//#	강철
//-	물 (전차 in 불가)
//^	위쪽을 바라보는 전차(아래는 평지이다.)
//v	아래쪽을 바라보는 전차(아래는 평지이다.)
//<	왼쪽을 바라보는 전차(아래는 평지이다.)
//>	오른쪽을 바라보는 전차(아래는 평지이다.)
//
//U	Up : 전차 방향 위로 바꾸고, 앞이 평지라면 한 칸 이동
//D	Down
//L	Left
//R	Right
//S	Shoot : 전차의 현재 방향으로 포탄 발사
//
//전차가 이동하려는 곳이 맵 밖이면 당연히 이동 x
//포탄은 벽돌, 강철을 만나거나 맵 밖으로 나갈 때 까지 감
//포탄 + 벽돌 = 평지
//강철에는 영향 x
//
//초기 맵 상태 + 사용자 입력 => 맵 상태 출력 
