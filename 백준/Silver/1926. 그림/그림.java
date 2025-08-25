import java.io.*;
import java.util.*;

public class Main {

	static int n, m, max, pic;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(pic);
		System.out.println(max);
	}

	private static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		
		int cnt = 1;
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				
				if(visited[nx][ny]) continue;
				
				if(map[nx][ny] == 0) continue;
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
				cnt++;
			}
		}

		max = Math.max(max, cnt);
		pic++;
	}
	
}
