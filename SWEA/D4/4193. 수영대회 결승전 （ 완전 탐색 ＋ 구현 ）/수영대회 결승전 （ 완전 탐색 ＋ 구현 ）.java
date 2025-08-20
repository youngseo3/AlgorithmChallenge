import java.io.*;
import java.util.*;

public class Solution {

	static int N;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			int time = bfs(new Point(startX, startY, 0), new Point(endX, endY, 0));
			if(time == 0) time = -1;
			sb.append("#" + tc + " ").append(time + "\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs(Point start, Point end) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(start);
		visited[start.x][start.y] = true;
		
		int ans = 0;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int x = current.x;
			int y = current.y;
			int time = current.time;
			
			if(x == end.x && y == end.y) {
				ans = time;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(map[nx][ny] == 1) continue;
				
				if(visited[nx][ny]) continue;
				
				if(map[nx][ny] == 2) {
					if(time % 3 != 2) {
						visited[x][y] = true;
						q.offer(new Point(x, y, time + 1));
					} else {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, time + 1));
					}
				} else {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny, time + 1));
				}
			}
		}
		
		return ans;
	}

	static class Point {
		int x;
		int y;
		int time;
		
		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
