import java.io.*;
import java.util.*;

public class Main {
	static int N, M, T;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[N][M];	
		visited = new boolean[N][M][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = bfs();
		if(result == -1) System.out.println("Fail");
		else System.out.println(result);
	}

	private static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0, 0, 0, false));
		
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int x = current.x;
			int y = current.y;
			int t = current.t;
			boolean gram = current.gram;
			
			if(t > T) break;
			
			if(x == N - 1 && y == M - 1) {
				return t;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int nt = t + 1;
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				int hasGramIdx = gram ? 1 : 0;
				
				if(visited[nx][ny][hasGramIdx]) continue;
				
				if(!gram) {
					if(map[nx][ny] == 0) {
						visited[nx][ny][0] = true;
                        q.add(new Point(nx, ny, nt, false));
					} else if(map[nx][ny] == 2) {
						visited[nx][ny][1] = true;
						q.add(new Point(nx, ny, nt, true));
					}
				} else {
					visited[nx][ny][1] = true;
					q.add(new Point(nx, ny, nt, true));
				}
				
			}
		}
		
		return -1;
	}

	static class Point {
		int x;
		int y;
		int t;
		boolean gram;
		
		Point(int x, int y, int t, boolean gram) {
			this.x = x;
			this.y = y;
			this.t = t;
			this.gram = gram;
		}
	}
}
