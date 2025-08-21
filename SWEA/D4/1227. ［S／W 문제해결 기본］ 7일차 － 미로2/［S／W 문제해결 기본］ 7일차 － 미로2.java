import java.io.*;
import java.util.*;

public class Solution {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc ++) {
			String t = br.readLine();
			
			map = new int[100][100];
			visited = new boolean[100][100];
			Point start = null;
			Point end = null;
			
			for(int i = 0; i < 100; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < 100; j++) {
					map[i][j] = line.charAt(j) - '0';
					
					if(map[i][j] == 2) {
						start = new Point(i, j);
					}
					
					if(map[i][j] == 3) {
						end = new Point(i, j);
					}
				}
			}
			
			bfs(start.x, start.y);
			
			if(visited[end.x][end.y]) {
				sb.append("#" + tc + " ").append(1).append("\n");
			} else {
				sb.append("#" + tc + " ").append(0).append("\n");
			}
		}
		
		System.out.println(sb);
	}

	private static void bfs(int sX, int sY) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(sX, sY));
		visited[sX][sY] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int x = current.x;
			int y = current.y;
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= 100 || ny < 0 || ny >= 100) continue;
				
				if(map[nx][ny] == 1) continue;
				
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				q.offer(new Point(nx, ny));
			}
		}
	}

	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
