import java.io.*;
import java.util.*;

public class Main {
	
	static int w, h;
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static int[][] island;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) break;

			island = new int[h][w];
			visited = new boolean[h][w];
			
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < w; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(island[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
			
			sb.append(cnt + "\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= h || ny < 0 || ny >= w) continue;
			
			if(visited[nx][ny]) continue;
			
			if(island[nx][ny] == 0) continue;
			
			dfs(nx, ny);
		}
		
	}

}
