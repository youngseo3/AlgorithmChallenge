import java.io.*;
import java.util.*;

public class Solution {

	static int N, max;
	static int[] dx = {1, 1, -1, -1};
	static int[] dy = {1, -1, -1, 1};
	static int[][] cafe;
	static boolean[][] visited;
	static boolean[] dessert;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			max = 0;
			cafe = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					cafe[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N; j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					visited[i][j] = true;
					dessert[cafe[i][j]] = true;
					dfs(1, i, j, i, j, 0);
				}
			}
			
			if(max == 0) max = - 1;
			sb.append("#" + tc + " ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int cnt, int x, int y, int initX, int initY, int dir) {
		for(int i = dir; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
			
			if(nx == initX && ny == initY && cnt >= 4) {
				max = Math.max(max, cnt);
				return;
			}
			
			if(!visited[nx][ny] && !dessert[cafe[nx][ny]]) {
				visited[nx][ny] = true;
				dessert[cafe[nx][ny]] = true;
				
				dfs(cnt + 1, nx, ny, initX, initY, i);
				
				visited[nx][ny] = false;
				dessert[cafe[nx][ny]] = false;
			}
		}
	}

}
