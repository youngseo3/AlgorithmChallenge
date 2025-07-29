import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line[j] - '0';
			}
		}
		
		bfs();
		
		System.out.println(map[N - 1][M - 1]);
	}
	
	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();

			for(int i = 0; i < 4; i++) {
				int nx = p[0] + dx[i];
				int ny = p[1] + dy[i];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < M && 
						!visited[nx][ny] && map[nx][ny] == 1) {
					visited[nx][ny] = true;
					map[nx][ny] = map[p[0]][p[1]] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
			
		}
	}

}
