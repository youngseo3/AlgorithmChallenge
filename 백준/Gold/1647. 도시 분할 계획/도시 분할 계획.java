import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<List<Vertex>> graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Vertex(b, c));
			graph.get(b).add(new Vertex(a, c));
		}
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		
		boolean[] visited = new boolean[N + 1];
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.offer(new Vertex(1, 0));
		
		while(!pq.isEmpty()) {
			Vertex current = pq.poll();
			
			if(visited[current.n]) continue;
			visited[current.n] = true;
			
			for(Vertex next: graph.get(current.n)) {
				int to = next.n;
				int weight = next.w;
				
				if(!visited[to] && weight < dist[to]) {
					dist[to] = weight;
					pq.offer(new Vertex(to, dist[to]));
				}
			}
		}
		
		int total = 0;
		int max = 0;
		for(int k: dist) {
			if(k != Integer.MAX_VALUE) {
				total += k;
				max = Math.max(max, k);
			}
		}
		System.out.println(total - max);
	}

	static class Vertex implements Comparable<Vertex> {
		int n;
		int w;
		
		public Vertex(int n, int w) {
			this.n = n;
			this.w = w;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.w, o.w);
		}
	}
}
