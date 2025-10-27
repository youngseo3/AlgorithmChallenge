import java.io.*;
import java.util.*;

public class Main {

	static int M, N, K;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[M][N];

		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for(int i = y1; i < y2; i++) {
				for(int j = x1; j < x2; j++) {
					visited[i][j] = true;
				}
			}
		}
		
		List<Integer> areas = new ArrayList<>();
		int cnt = 0;
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					cnt++;
					areas.add(bfs(i, j));
				}
			}
		}
		
		Collections.sort(areas); // 넓이를 오름차순으로 정렬
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		for(int area : areas) {
			sb.append(area).append(" ");
		}
		
		System.out.println(sb);
	}

	private static int bfs(int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startX, startY});
		visited[startX][startY] = true;
		int size = 1;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int y = current[0];
			int x = current[1];
			
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				
				if(ny >= 0 && ny < M && nx >= 0 && nx < N && !visited[ny][nx]) {
					visited[ny][nx] = true; // 방문 처리
					q.offer(new int[]{ny, nx}); // 큐에 추가
					size++; // 넓이 증가
				}
			}
		}
		
		return size;
	}
	
}
