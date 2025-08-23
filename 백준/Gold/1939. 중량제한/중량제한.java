import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Point>> points = new ArrayList<>();

		for(int i = 0; i <= N; i++) {
			points.add(new ArrayList<>());
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			points.get(A).add(new Point(B, C));
			points.get(B).add(new Point(A, C));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int[] weights = new int[N + 1];
		Arrays.fill(weights, Integer.MIN_VALUE);

		weights[start] = Integer.MAX_VALUE;

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(start, Integer.MAX_VALUE));
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			int v = current.v;
			int weight = current.weight;
			
			if(weights[v] > weight) continue;
			
			for(Point next: points.get(v)) {
				if(Math.min(weights[v], next.weight) > weights[next.v]) {
					weights[next.v] = Math.min(weights[v], next.weight);
					pq.offer(new Point(next.v, weights[next.v]));
				}
			}
		}
		
		System.out.println(weights[end]);
	}

	static class Point implements Comparable<Point> {
		int v;
		int weight;

		public Point(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(o.weight, this.weight);
		}
	}

}
