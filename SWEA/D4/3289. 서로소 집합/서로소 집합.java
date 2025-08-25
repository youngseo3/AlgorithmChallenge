import java.io.*;
import java.util.*;

public class Solution {

	static int[] parents;
	static int[] rank; //  1. 랭크를 저장할 배열 선언
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			parents = new int[n + 1];
			rank = new int[n + 1]; //  2. 랭크 배열 생성
			sb.append("#" + tc + " ");
			
		    for (int i = 1; i <= n; i++) {
		        parents[i] = i;
		        // rank[i] = 0; // Java에서 int 배열은 자동으로 0으로 초기화되므로 생략 가능
		    }
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int order = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(order == 0) {
					union(a, b);
				} else {
					if(find(a) == find(b)) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static int find(int x) {
		if(x != parents[x]) {
			parents[x] = find(parents[x]);
		}
		return parents[x];
	}

	//  3. Union-by-Rank 로직이 적용된 union 메서드
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		// 랭크 비교
		if (rank[rootA] < rank[rootB]) {
			parents[rootA] = rootB;
		} else if (rank[rootA] > rank[rootB]) {
			parents[rootB] = rootA;
		} else { // 랭크가 같은 경우
			parents[rootB] = rootA; // 한쪽을 다른 쪽에 붙이고
			rank[rootA]++;         // 루트가 된 노드의 랭크를 1 증가
		}
		
		return true;
	}
}