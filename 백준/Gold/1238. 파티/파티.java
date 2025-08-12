import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<List<Home>> homes = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			homes.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			homes.get(u).add(new Home(v, t));
		}
		
		// 다익스트라
		// 시작점을 X로 했을 때 모든 집에 도달하기 까지의 최단 시간
		int[] times = dijkstraToX(N, X, homes);
		
		// 시작점을 모든 집으로 했을 때 X에 도달하기 까지의 최단 시간
		for(int i = 1; i <= N; i++) {
			int[] temp = dijkstraToX(N, i, homes);
			times[i] += temp[X];
		}
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, times[i]);
		}
		
		System.out.println(max);
	}

	private static int[] dijkstraToX(int N, int X, List<List<Home>> homes) {
		int[] times = new int[N + 1];
		Arrays.fill(times, Integer.MAX_VALUE);
		
		times[X] = 0;
		
		PriorityQueue<Home> pq = new PriorityQueue<>();
		pq.offer(new Home(X, 0));
		
		while(!pq.isEmpty()) {
			Home current = pq.poll();
			
			if(times[current.student] < current.time) continue;
			
			for(Home next: homes.get(current.student)) {
				if(times[current.student] + next.time < times[next.student]) {
					times[next.student] = times[current.student] + next.time;
					pq.offer(new Home(next.student, times[next.student]));
				}
			}
		}
		
		return times;
	}

	static class Home implements Comparable<Home> {
		int student;
		int time;
		
		public Home(int student, int time) {
			this.student = student;
			this.time = time;
		}
		
		@Override
		public int compareTo(Home o) {
			return Integer.compare(this.time, o.time);
		}
	}
}
