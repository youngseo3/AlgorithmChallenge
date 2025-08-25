import java.io.*;
import java.util.*;

public class Solution {

	static int[] parents; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			parents = new int[n + 1];
			sb.append("#" + tc + " ");
			
		    for (int i = 1; i <= n; i++) {
		        parents[i] = i; // 모든 원소가 자기 자신을 부모로 설정
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

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int	rootB = find(b);
		
		if(rootA == rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}

}
