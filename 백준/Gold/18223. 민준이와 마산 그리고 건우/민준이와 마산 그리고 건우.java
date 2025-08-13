import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		List<List<Node>> nodes = new ArrayList<>();
		for(int i = 0; i <= V; i++) {
			nodes.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			nodes.get(a).add(new Node(b, c));
			nodes.get(b).add(new Node(a, c));
		}
		
		// 다익스트라
		int[] distFromStart = dijkstra(1, V, nodes);
		int[] distFromP = dijkstra(P, V, nodes);

		if(distFromStart[V] == distFromStart[P] + distFromP[V]) {
			System.out.println("SAVE HIM");
		} else {
			System.out.println("GOOD BYE");
		}
	}

	
	
	private static int[] dijkstra(int start, int V, List<List<Node>> nodes) {
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		dist[start] = 0;		
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(current.distance > dist[current.num]) continue;
			
			for(Node next: nodes.get(current.num)) {
				if(dist[current.num] + next.distance < dist[next.num]) {
					dist[next.num] = dist[current.num] + next.distance;
					pq.offer(new Node(next.num, dist[next.num]));
				}
			}
		}
		
		return dist;
	}



	static class Node implements Comparable<Node> {
		int num;
		int distance;
		
		public Node(int num, int distance) {
			this.num = num;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
}
