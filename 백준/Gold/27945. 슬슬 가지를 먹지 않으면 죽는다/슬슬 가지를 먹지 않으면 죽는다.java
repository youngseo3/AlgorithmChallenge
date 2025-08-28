import java.io.*;
import java.util.*;
public class Main {

	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N + 1];
		
		for(int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		List<Edge> edges = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(u, v, t));
		}
		
		Collections.sort(edges);

		long day = 1;
		int cnt = 0;
		for(Edge e: edges) {
			if(e.t != day) break;
			
			if(union(e.u, e.v)) {
				cnt++;
				day++;
			}
			
			if(cnt == N - 1) break;
		}
		
		System.out.println(day);
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
		int u;
		int v;
		long t;
		
		public Edge(int u, int v, long t) {
			this.u = u;
			this.v = v;
			this.t = t;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.t, o.t);
		}
	}
}
