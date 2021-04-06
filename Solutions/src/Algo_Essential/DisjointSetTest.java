package Algo_Essential;
import java.util.Arrays;

// 서로소 집합!
public class DisjointSetTest {
	static int N;
	static int parents[];
	
	// 크기가 1인 단위집합 생성
	static void makeSet() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if (parents[a] == a) return a;
//		return findSet(parents[a]); // path compression 전
		return parents[a] = findSet(parents[a]); // path compression 후
		// 나의 parents를 가장 큰형님으로 바꾸겠다!
		
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		// 같은 조직이면 합병 불가능
		if (aRoot == bRoot) return false;
		
		// a 조직에 b 흡수시키자
		parents[bRoot] = aRoot;
		return true;
	}
	// 랭크 관리해서 union 하는 것과 path compression을 동시에 하는 것은 굉~~~~장히 어렵다
	// 랭크 관리중에 path compression이 일어나면 개판나서 안쓴다~
	// 둘 중 하나만 사용한다 보통!
	
	public static void main(String[] args) {
		N = 5;
		parents = new int[N];
		
		makeSet();
		
		System.out.println("=======================union=======================");
		System.out.println(union(0, 1));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(1, 2));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(3, 4));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(0, 2));
		System.out.println(Arrays.toString(parents));
		System.out.println(union(0, 4));
		System.out.println(Arrays.toString(parents));
		
		System.out.println("=======================find=======================");
		System.out.println(findSet(4));
		System.out.println(Arrays.toString(parents));
		System.out.println(findSet(3));
		System.out.println(Arrays.toString(parents));
		System.out.println(findSet(2));
		System.out.println(Arrays.toString(parents));
		System.out.println(findSet(1));
		System.out.println(Arrays.toString(parents));
		System.out.println(findSet(0));
		System.out.println(Arrays.toString(parents));
	}
}
