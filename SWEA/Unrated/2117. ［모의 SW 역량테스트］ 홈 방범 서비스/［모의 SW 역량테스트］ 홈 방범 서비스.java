import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, cost, ans;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] cities;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			ans = 0;
			cities = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					cities[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int k = 1; k < 22; k++) {
				cost = k * k + (k - 1) * (k - 1);
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						solve(i, j, k);
					}
				}
//				if(k * k + (k - 1) * (k - 1) > N * N + 400) break;
			}
			
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		
		System.out.println(sb);
	}

	private static void solve(int x, int y, int k) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {x, y});
		
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		
		int cnt = 0;
		if(cities[x][y] == 1) {
			cnt++;
		}
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int cx = current[0];
			int cy = current[1];
			
			for(int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				
				if(visited[nx][ny] || getDistance(x, y, nx, ny) >= k) continue;
				
				if(cities[nx][ny] == 1) {
					cnt++;
				}
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
			
		if(cnt * M >= cost) {
			ans = Math.max(ans, cnt);
		}
	}

	private static int getDistance(int x, int y, int nx, int ny) {
		return Math.abs(x - nx) + Math.abs(y - ny);
	}

}
