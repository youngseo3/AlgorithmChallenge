import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < n; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		bfs();
	}

	private static void bfs() {
		PriorityQueue<Point> q = new PriorityQueue<>();
		q.offer(new Point(0, 0, 0));
		
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			int blackToWhite = p.blackToWhite;
			
			if(x == n - 1 && y == n - 1) {
				System.out.println(blackToWhite);
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				
				if(visited[nx][ny]) continue;
	
				visited[nx][ny] = true;
				
				if(map[nx][ny] == 0){
					q.offer(new Point(nx, ny, blackToWhite + 1));
				} else {
					q.offer(new Point(nx, ny, blackToWhite));
				}
	
				
			}
		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int blackToWhite;
		
		public Point(int x, int y, int blackToWhite) {
			this.x = x;
			this.y = y;
			this.blackToWhite = blackToWhite;
		}
		
		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.blackToWhite, o.blackToWhite);
		}
	}
}
