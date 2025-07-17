import java.io.*;
import java.util.*;

public class Main {

	static int N, maxHeight;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] zone;
	static boolean[][] visited; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		zone = new int[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				zone[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, zone[i][j]);
			}
		}

		// 장마철 내리는 비는 최대 높이까지만 오는 비로 설정
		int maxSafetyZone = 1;
		for(int rain = 1; rain < maxHeight; rain++) {
			visited = new boolean[N][N];
			int safetyZoneCount = 0;
			
			for(int i = 0; i < N; i++) {				
				for(int j = 0; j < N; j++) {
					if(rain < zone[i][j] && !visited[i][j]) {
						bfs(i, j, rain);
						safetyZoneCount++;
					}
				}
			}
			
			maxSafetyZone = Math.max(maxSafetyZone, safetyZoneCount);
		}
		
		System.out.println(maxSafetyZone);
	}
	

	// 안전 지역 구하기
	static void bfs(int startX, int startY, int rain) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startX, startY});
		visited[startX][startY] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextX = dx[i] + p[0];
				int nextY = dy[i] + p[1];
				
				if(0 <= nextX && nextX < N &&
						0 <= nextY && nextY < N &&
						!visited[nextX][nextY] &&
						zone[nextX][nextY] > rain) {
					visited[nextX][nextY] = true;
					q.add(new int[] {nextX, nextY});
				}
			}
		}
	}
	
}