import java.io.*;
import java.util.*;

public class Solution {

	static int V, E;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parents = new int[V + 1];
			for(int i = 0; i <= V; i++) {
				parents[i] = i;
			}
			
			Edge[] edges = new Edge[E];
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(a, b, w);
			}
			
			Arrays.sort(edges);
			
			int cnt = 0;
			long totalWeight = 0;
			for(int i = 0; i < E; i++) {
				Edge edge = edges[i];
				int a = edge.a;
				int b = edge.b;
				int w = edge.w;
				
				if(union(a, b)) {
					cnt++;
					totalWeight += w;
				}
				
				if(cnt == V - 1) {
					break;
				}
			}
			
			sb.append("#" + tc + " ").append(totalWeight + "\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}

	private static int find(int a) {
		if(a != parents[a]) {
			parents[a] = find(parents[a]);
		}
		
		return parents[a];
	}

	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		int w;
		
		public Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

}
