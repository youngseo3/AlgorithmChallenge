import java.io.*;
import java.util.*;

public class Main {

	static int h, w, time;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static int[][] times;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		times = new int[h][w];
		visited = new boolean[h][w];
		
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
		
		int cnt = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(times[i][j] == time) cnt++;
			}
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}

	private static void solve() {
//		while(!checkCheese()) {
//			for(int i = 0; i < h; i++) {
//				for(int j = 0; j < w; j++) {
//					if(map[i][j] == 0) {
//						bfs(i, j);
//					}
//				}
//			}
//		}
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(map[i][j] == 0) {
					bfs(i, j);
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		boolean isStart = false;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
			
			if(map[nx][ny] == 1) {
				isStart = true;
			}
		}
		
		if(isStart) {
			time++;

			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {x, y});
			
			boolean[][] isZero = new boolean[h][w];
					
			while(!q.isEmpty()) {
				int[] cxy = q.poll();
				int cx = cxy[0];
				int cy = cxy[1];
				
				isZero[cx][cy] = true;
				
				for(int i = 0; i < 4; i++) {
					int nx = cx + dx[i];
					int ny = cy + dy[i];
					
					if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
					
					if(!visited[nx][ny] && map[nx][ny] == 1) {
						visited[nx][ny] = true;
					}
					else if(!isZero[nx][ny] && map[nx][ny] == 0) {
						q.offer(new int[] {nx, ny});
                        isZero[nx][ny] = true;  // 또는 visited[nx][ny] = true;
					}
				}
			}
			
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(visited[i][j]) {
						visited[i][j] = false;
						map[i][j] = 0;
						times[i][j] = time;
					}
				}
			}
		}
	}

	private static boolean checkCheese() {
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(map[i][j] == 1) {
					return false;
				}
			}
		}
		
		return true;
	}

}
