import java.io.*;
import java.util.*;

public class Main {

	static int K, W, H;
	static int[] dx = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1 ,1 ,2};
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visited = new boolean[H][W][K + 1];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			int x = p.x;
			int y = p.y;
			int k = p.k;
			int cnt = p.cnt;
			
			if(x == H - 1 && y == W - 1) {
				return cnt;
			}
			
			for(int i = 0; i < (k == K ? 4 : 12); i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
				
				if(map[nx][ny] == 1) continue;
				
				int nextK = (i >= 4) ? k + 1 : k;
                if(visited[nx][ny][nextK]) continue;
                
                visited[nx][ny][nextK] = true;
                q.offer(new Point(nx, ny, nextK, cnt + 1));
			}
			
		}
		
		return -1;
	}

	static class Point {
		int x;
		int y;
		int k;
		int cnt;
		
		public Point(int x, int y, int k, int cnt) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.cnt = cnt;
		}
	}
}
