import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 1, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int cnt = cur.cnt;
			int isBreak = cur.isBreak;
			
			if(x == N - 1 && y == M - 1) {
				return cnt;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if(map[nx][ny] == 0 && !visited[nx][ny][isBreak]) {
					visited[nx][ny][isBreak] = true;
					q.offer(new Point(nx, ny, cnt + 1, isBreak));
				}
                else if(map[nx][ny] == 1 && isBreak == 0 && !visited[nx][ny][1]) {
					visited[nx][ny][1] = true;
					q.offer(new Point(nx, ny, cnt + 1, 1));
				}
			}
		}
		
		return - 1;
	}

	static class Point {
		int x;
		int y;
		int cnt;
		int isBreak;
		
		public Point(int x, int y, int cnt, int isBreak) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.isBreak = isBreak;
		}
	}
}
