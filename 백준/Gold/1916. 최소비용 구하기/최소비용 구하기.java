import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<List<City>> cities = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			cities.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			cities.get(u).add(new City(v, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int[] costs = new int[N + 1];
		Arrays.fill(costs, Integer.MAX_VALUE);
		
		boolean[] visited = new boolean[N + 1];
		
		costs[start] = 0;
		
		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.offer(new City(start, 0));
		
		while(!pq.isEmpty()) {
			City current = pq.poll();
			
			if(visited[current.num]) continue;
			visited[current.num] = true;

			for(City next: cities.get(current.num)) {
				if(costs[current.num] + next.cost < costs[next.num]) {
					costs[next.num] = costs[current.num] + next.cost;
					pq.offer(new City(next.num, costs[next.num]));
				}
			}
		}
		
		System.out.println(costs[end]);
	}
	
	static class City implements Comparable<City> {
		int num;
		int cost;
		
		public City(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(City o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}