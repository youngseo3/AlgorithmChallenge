import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		visited[0][0] = true;
		bfs(0, 0);
		
		System.out.println(map[N - 1][M - 1]);
	}
	
	static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {row, col});
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextRow = current[0] + dy[i];
				int nextCol = current[1] + dx[i];
				
				if((nextRow >= 0 && nextRow < N) && (nextCol >= 0 && nextCol < M) && map[nextRow][nextCol] == 1 && !visited[nextRow][nextCol]) {
					q.add(new int[] {nextRow, nextCol});
					map[nextRow][nextCol] = map[current[0]][current[1]] + 1;
					visited[nextRow][nextCol] = true;
				}
			}
		}
	}

}
