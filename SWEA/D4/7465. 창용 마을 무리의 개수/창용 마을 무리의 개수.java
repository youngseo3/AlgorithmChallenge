import java.io.*;
import java.util.*;

public class Solution {

	static int N, M;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parents = new int[N + 1];
			
			for(int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			HashSet<Integer> set = new HashSet<>();
			for(int i = 1; i <= N; i++) {
				int root = find(i);
				set.add(root);
			}
			
			sb.append("#" + tc + " ").append(set.size()).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parents[rootB] = rootA;
	}

	private static int find(int x) {
		if(x != parents[x]) {
			parents[x] = find(parents[x]);
		}
		
		return parents[x];
	}

}
