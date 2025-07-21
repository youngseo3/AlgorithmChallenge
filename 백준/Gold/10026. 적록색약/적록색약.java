import java.io.*;
import java.util.*;

public class Main {

	static int N, cnt;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static char[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j, map[i][j]);
				}
			}
		}
		System.out.print(cnt + " ");

		cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				visited[i][j] = false;
				
				if(map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		System.out.println(cnt);

	}

	static void bfs(int x, int y, char firstColor) {
		Queue<int[]> q = new LinkedList<>();
		visited[x][y] = true;
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = current[0] + dx[i];
				int ny = current[1] + dy[i];
				
				if(nx >= 0 && nx < N && 
						ny >= 0 && ny < N &&
						!visited[nx][ny] &&
						map[nx][ny] == firstColor) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		cnt++;
	}
}
