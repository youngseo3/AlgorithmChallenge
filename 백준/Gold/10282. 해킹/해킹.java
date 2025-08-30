import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			List<List<Computer>> computers = new ArrayList<>();
			
			for(int i = 0; i <= n; i++) {
				computers.add(new ArrayList<>());
			}
			
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				computers.get(b).add(new Computer(a, s));
			}
			
			int[] times = new int[n + 1];
			Arrays.fill(times, Integer.MAX_VALUE);
			times[c] = 0;
			
			PriorityQueue<Computer> pq = new PriorityQueue<>();
			pq.offer(new Computer(c, 0));
			
			while(!pq.isEmpty()) {
				Computer current = pq.poll();
				int v = current.v;
				int time = current.time;
				
				if(time > times[v]) continue;
				
				for(Computer next: computers.get(v)) {
					if(times[v] + next.time < times[next.v]) {
						times[next.v] = times[v] + next.time;
						pq.offer(new Computer(next.v, times[next.v]));
					}
				}
			}
			
			int cnt = 0;
			int max = 0;
			for(int i = 1; i <= n; i++) {
				if(times[i] != Integer.MAX_VALUE) {
					cnt++;
					max = Math.max(max, times[i]);
				}
			}
			
			sb.append(cnt + " ").append(max + "\n");
		}
		
		System.out.println(sb);
		
	}

	static class Computer implements Comparable<Computer> {
		int v;
		int time;
		
		public Computer(int v, int time) {
			this.v = v;
			this.time = time;
		}

		@Override
		public int compareTo(Computer o) {
			return Integer.compare(this.time, o.time);
		}
		
		
	}
}
