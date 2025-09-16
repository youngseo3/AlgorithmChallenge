import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] dx = {0, 0, -1, -1 ,-1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static List<Cloud> clouds = new ArrayList<>();
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		clouds.add(new Cloud(N, 1));
		clouds.add(new Cloud(N, 2));
		clouds.add(new Cloud(N - 1, 1));
		clouds.add(new Cloud(N - 1, 2));
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int dir = Integer.parseInt(st.nextToken());
			int move = Integer.parseInt(st.nextToken());
			
			solve(dir, move);
		}
		
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				cnt += map[i][j];
			}
		}
		
		System.out.println(cnt);
	}

	private static void solve(int dir, int move) {
		moveClouds(dir, move);
		
		boolean[][] visited = new boolean[N + 1][N + 1];
		for(Cloud cloud: clouds) {
			map[cloud.x][cloud.y]++;
			visited[cloud.x][cloud.y] = true;
		}
		
		for(Cloud cloud: clouds) {
			int cnt = 0;
			
			for(int i = 2; i <= 8; i += 2) {
				int nx = cloud.x + dx[i];
				int ny = cloud.y + dy[i];
				
				if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
				
				if(map[nx][ny] > 0) {
					cnt++;
				}
			}
			
			map[cloud.x][cloud.y] += cnt;
		}
		
//		for(int i = 1; i <= N; i++) {
//			for(int j = 1; j <= N; j++) {
//				System.out.print(map[i][j] + " ");
//			}	
//			
//			System.out.println();
//		}
		
		while(!clouds.isEmpty()) {
			clouds.remove(0);
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(visited[i][j]) continue;
				
				if(map[i][j] >= 2) {
					clouds.add(new Cloud(i, j));
					map[i][j] -= 2;
//					System.out.println(i + " " + j);
				}
			}
		}
	}

	private static void moveClouds(int dir, int move) {
		for(int i = 0; i < clouds.size(); i++) {
			Cloud cloud = clouds.get(i);
			int moveXDir = (dx[dir] * move) % N;
			int moveYDir = (dy[dir] * move) % N;
			
			int nx = cloud.x + moveXDir;
			int ny = cloud.y + moveYDir;
			
			if(nx < 1) {
				nx = N - Math.abs(nx);
			}
			else if(nx > N) {
				nx = nx - N;
			}
			
			if(ny < 1) {
				ny = N - Math.abs(ny);
			}
			else if(ny > N) {
				ny = ny - N;
			}
			
			Cloud nextCloud = new Cloud(nx, ny);
			clouds.set(i, nextCloud);
		}
	}

	static class Cloud {
		int x;
		int y;
		
		public Cloud(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Cloud [x=" + x + ", y=" + y + "]";
		}
		
	}
}
