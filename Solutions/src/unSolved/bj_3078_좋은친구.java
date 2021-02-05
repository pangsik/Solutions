package unSolved;
import java.io.*;
import java.util.*;

public class bj_3078_좋은친구 {

	public static void main(String args[]) throws Exception {
		// System.setIn(new FileInputStream("res/input_d3_1873.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] nameLen = new int[N];
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			nameLen[i] = br.readLine().length();
			for (int j = i - K; j < i; j++) {
				if (j >= 0 && nameLen[i] == nameLen[j])
					cnt++;
			}
		}
		
		pw.println(cnt);
		
		pw.close();
		br.close();
	}
}