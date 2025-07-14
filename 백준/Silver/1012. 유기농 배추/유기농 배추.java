import java.io.*;
import java.util.*;

public class Main {

	static int M, N, K, result;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] farm;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			farm = new int[M][N];
			visited = new boolean[M][N];
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				farm[X][Y] = 1;
			}
			
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(farm[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
					}
				}
			}
			
			System.out.println(result);
			result = 0;
		}
		
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextX = dx[i] + p[0];
				int nextY = dy[i] + p[1];
				
				if(nextX >= 0 && nextX < M && 
						nextY >= 0 && nextY < N && 
						farm[nextX][nextY] == 1 && !visited[nextX][nextY]) {
					q.add(new int[] {nextX, nextY});
					visited[nextX][nextY] = true;
				}
			}
		}
		
		result++;
	}

}
