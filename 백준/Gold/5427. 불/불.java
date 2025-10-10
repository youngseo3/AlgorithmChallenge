import java.io.*;
import java.util.*;

public class Main {
	
	static int h, w, result;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static Queue<Point> fire;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			fire = new ArrayDeque<>();

			int startX = -1;
			int startY = -1;
			
			for(int i = 0; i < h; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					
					if(map[i][j] == '@') {
						startX = i;
						startY = j;
					} else if(map[i][j] == '*') {
						fire.offer(new Point(i, j, 0));
					}
				}
			}
			
			if(solve(startX, startY)) {
				sb.append(result + "\n");
			} else {
				sb.append("IMPOSSIBLE" + "\n");
			}
			
		}
		
		System.out.println(sb);
	}

	private static boolean solve(int startX, int startY) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(startX, startY, 1));
		boolean[][] visited = new boolean[h][w];
	    visited[startX][startY] = true;
	    
	    // 만약 시작점이 가장자리라면 바로 1을 리턴
	    if (startX == 0 || startX == h - 1 || startY == 0 || startY == w - 1) {
	        result = 1;
	        return true;
	    }

		while(!q.isEmpty()) {
			int fireSize = fire.size();
			for(int s = 0; s < fireSize; s++) {
				Point current = fire.poll();
				int x = current.x;
				int y = current.y;
				int time = current.time;
				
				if(x < 0 || x >= h || y < 0 || y >= w) {
					return true;
				}
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;

					if(map[nx][ny] == '.') {
						map[nx][ny] = '*';
						fire.offer(new Point(nx, ny, time + 1));
					}
				}
			}
			
			int qSize = q.size();
			for(int s = 0; s < qSize; s++) {
				Point current = q.poll();
				int x = current.x;
				int y = current.y;
				int time = current.time;
				
				if(x == 0 || x == h - 1 || y == 0 || y == w - 1) {
					result = time;
					return true;
				}
				
				for(int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
					
					if(!visited[nx][ny] && map[nx][ny] == '.') {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, time + 1));
					}
					
				}
			}
		}
		
		return false;
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
