import java.io.*;
import java.util.*;

public class Main {

	static int n, m, r;
	static int[] items;
	static List<List<Node>> nodes = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		items = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= n; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i <= n; i++) {
			nodes.add(new ArrayList<>());
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			nodes.get(a).add(new Node(b, l));
			nodes.get(b).add(new Node(a, l));
		}
		
		int result = 0;
		for(int i = 1; i <= n; i++) {
			result = Math.max(result, dijkstra(i));
		}
		System.out.println(result);
	}

	private static int dijkstra(int start) {
		int[] weights = new int[n + 1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		
		weights[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(current.weight > weights[current.v]) continue;
			
			for(Node next: nodes.get(current.v)) {
				if(weights[current.v] + next.weight < weights[next.v]) {
					weights[next.v] = weights[current.v] + next.weight;
					pq.offer(new Node(next.v, weights[next.v]));
				}
			}
		}
		
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			if(weights[i] <= m) {
				sum += items[i];
			}
		}
		
		return sum;
	}

	static class Node implements Comparable<Node> {
		int v;
		int weight;
		
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
