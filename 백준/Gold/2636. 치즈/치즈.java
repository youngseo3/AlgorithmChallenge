import java.io.*;
import java.util.*;

public class Main {
	static int h, w, time, lastCheeseCount;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visited = new boolean[h][w];
		
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		solve();
		
		System.out.println(time);
		System.out.println(lastCheeseCount);
	}
	
	private static void solve() {
		while(!checkCheese()) {
			visited = new boolean[h][w];
			bfs(0, 0);
		}
	}
	
	private static void bfs(int x, int y) {
		time++;
		
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		
		List<int[]> toMelt = new ArrayList<>();
				
		while(!q.isEmpty()) {
			int[] cxy = q.poll();
			int cx = cxy[0];
			int cy = cxy[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				
				if(map[nx][ny] == 0) {
					q.offer(new int[] {nx, ny});
				} else {
					toMelt.add(new int[] {nx, ny});
				}
			}
		}
		
		for(int[] cheese : toMelt) {
			map[cheese[0]][cheese[1]] = 0;
		}
		
		lastCheeseCount = toMelt.size();
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