import java.io.*;
import java.util.*;

public class Main {

	static int l, x, y, targetX, targetY;
	static int[] dx = {2, 2, 1, 1, -1, -1, -2, -2};
	static int[] dy = {1, -1, 2, -2, 2, -2, 1, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			l = Integer.parseInt(br.readLine());
			visited = new boolean[l][l];
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			targetX = Integer.parseInt(st.nextToken());
			targetY = Integer.parseInt(st.nextToken());
			
			System.out.println(bfs());
		}
		
	}
	
	static int bfs() {
		if(x == targetX && y == targetY) {
			return 0;
		}
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y, 0}); // x, y, 이동횟
		visited[x][y] = true;
		
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			int currentX = p[0];
            int currentY = p[1];
            int moves = p[2];
			
			for(int i = 0; i < 8; i++) {
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				
				if(nextX >= 0 && nextX < l && nextY >= 0 && nextY < l && !visited[nextX][nextY]) {
					if(nextX == targetX && nextY == targetY) {
						return moves + 1;
					}
					
					visited[nextX][nextY] = true;
					queue.add(new int[] {nextX, nextY, moves + 1});
				}
			}
		}
		
		return -1;
	}

}
