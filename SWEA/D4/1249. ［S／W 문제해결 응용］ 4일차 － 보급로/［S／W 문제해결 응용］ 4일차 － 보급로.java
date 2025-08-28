import java.io.*;
import java.util.*;

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
			
			sb.append("#" + tc + " ").append(dijkstra() + "\n");
		}
		
		System.out.println(sb);
	}

	private static int dijkstra() {
		int[][] times = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(times[i], Integer.MAX_VALUE);
		}
		times[0][0] = 0;
		
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Point current = pq.poll();
			int x = current.x;
			int y = current.y;
			int t = current.t;
			
			if(current.t > times[x][y]) continue;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(map[nx][ny] + times[x][y] < times[nx][ny]) {
					times[nx][ny] = map[nx][ny] + times[x][y];
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
