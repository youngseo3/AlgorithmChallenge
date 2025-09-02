import java.io.*;
import java.util.*;

import sun.util.resources.cldr.ms.TimeZoneNames_ms;

public class Solution {

	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			int ans = dijkstra(new Point(0, 0, 0));
			
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		
		System.out.println(sb);
	}
	
	private static int dijkstra(Point p) {
		int[][] times = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(times[i], Integer.MAX_VALUE);	
		}
		times[p.x][p.y] = p.t;
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(p.x, p.y, p.t));
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			int x = current.x;
			int y = current.y;
			int t = current.t;
			
			if(t > times[x][y]) continue;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i]; 
				int ny = y + dy[i]; 
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(times[x][y] + map[nx][ny] < times[nx][ny]) {
					times[nx][ny] = times[x][y] + map[nx][ny];
					pq.offer(new Point(nx, ny, times[nx][ny]));
				}
			}
		}
		
		return times[N - 1][N - 1];
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int t;
		
		public Point(int x, int y, int t) {
			this.x = x;
			this.y = y;
			this.t = t;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.t, o.t);
		}
		
	}
}
