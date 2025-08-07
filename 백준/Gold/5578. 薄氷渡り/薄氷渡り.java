import java.io.*;
import java.util.*;

public class Main {

	static int n, m, max;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		m = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					max = Math.max(max, dfs(i, j));
				}
			}
		}
		
		System.out.println(max);
	}

	private static int dfs(int x, int y) {
		int cnt = 0;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
			
			if(map[nx][ny] == 0) continue;
			
			map[nx][ny] = 0;
			cnt = Math.max(cnt, dfs(nx, ny) + 1);
			map[nx][ny] = 1;
		}
		
		return cnt;
	}

}
