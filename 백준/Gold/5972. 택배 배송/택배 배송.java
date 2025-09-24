import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<List<Node>> graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));
		
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int v = current.v;
			int distance = current.distance;
			
			if(distance > dist[v]) continue;
			
			for(Node next: graph.get(v)) {
				if(next.distance + dist[v] < dist[next.v]) {
					dist[next.v] = next.distance + dist[v];
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		System.out.println(dist[N]);
	}
	
	static class Node implements Comparable<Node> {
		int v;
		int distance;
		
		public Node(int v, int distance) {
			this.v = v;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
}
