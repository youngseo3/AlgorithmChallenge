import java.io.*;
import java.util.*;

public class Solution {

	static int N, M, cost, ans, homeSize;
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
			homeSize = 0;
			cities = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					cities[i][j] = Integer.parseInt(st.nextToken());
					if(cities[i][j] == 1) homeSize++;
				}
			}
			
			boolean check = false;
			for(int k = 1; ; k++) {
				cost = k * k + (k - 1) * (k - 1);
				
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						solve(i, j, k);
					}
				}
				
				if(check) break;
				if(cost > homeSize * M) {
					check = true;
					continue;
				}
			}
			
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		
		System.out.println(sb);
	}

	private static void solve(int x, int y, int k) {
//		Queue<int[]> q = new ArrayDeque<int[]>();
//		q.add(new int[] {x, y});
//		
//		boolean[][] visited = new boolean[N][N];
//		visited[x][y] = true;
//		
//		int cnt = 0;
//		if(cities[x][y] == 1) {
//			cnt++;
//		}
//		
//		while(!q.isEmpty()) {
//			int[] current = q.poll();
//			int cx = current[0];
//			int cy = current[1];
//			
//			for(int i = 0; i < 4; i++) {
//				int nx = cx + dx[i];
//				int ny = cy + dy[i];
//				
//				if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
//				
//				if(visited[nx][ny] || getDistance(x, y, nx, ny) >= k) continue;
//				
//				if(cities[nx][ny] == 1) {
//					cnt++;
//				}
//				visited[nx][ny] = true;
//				q.add(new int[] {nx, ny});
//			}
//		}
		
//		System.out.println(cnt);
		
		int cnt = 0;

	    // 도시의 모든 좌표 (i, j)를 순회합니다.
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            // 현재 좌표 (i, j)가 중심 (x, y)의 서비스 범위 내에 있는지 확인합니다.
	            if (getDistance(x, y, i, j) < k) {
	                // 범위 내에 있고, 그곳에 집이 있다면 카운트합니다.
	                if (cities[i][j] == 1) {
	                    cnt++;
	                }
	            }
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
