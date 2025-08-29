import java.io.*;
import java.util.*;

public class Solution {

	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static Queue<Point> q;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			visited = new boolean[N][M];
			q = new ArrayDeque<>(); 
			
			int xS = 0, yS = 0;
			for(int i = 0; i < N; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					
					// 수연이 위치 저장
					if(map[i][j] == 'S') {
						xS = i;
						yS = j;
					}
					
					// 악마의 위치 저장
					if(map[i][j] == '*') {
						q.offer(new Point(i, j, 0, true));
					}
				}
			}
			
			q.offer(new Point(xS, yS, 0, false));
			visited[xS][yS] = true;
			
			// 매초마다 수연이와 악마가 움직인다
			int result = gameStart();
			
			if (result == -1) {
                sb.append("#").append(tc).append(" GAME OVER\n");
            } else {
                sb.append("#").append(tc).append(" ").append(result).append("\n");
            }
		}
		
		System.out.println(sb);
	}

	private static int gameStart() {
		while(!q.isEmpty()) {
			
			// 1. 현재 턴에 처리해야 할 큐의 크기를 미리 저장
			int size = q.size();
			
			// 2. 저장된 크기만큼만 정확히 반복 (이번 턴에 해당하는 움직임만 처리)
	        for (int i = 0; i < size; i++) {
	            Point p = q.poll();

	            // 만약 현재 poll한 대상이 수연이라면,
	            // 다른 악마가 퍼지기 전에 먼저 여신에게 도달할 수 있는지 체크
	            // (악마와 수연이가 같은 턴에 같은 곳으로 이동하면 수연이가 잡히므로)
	            if (!p.isDevil) {
	                for (int d = 0; d < 4; d++) {
	                    int nx = p.x + dx[d];
	                    int ny = p.y + dy[d];
	                    if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 'D') {
	                        return p.time + 1;
	                    }
	                }
	            }

	            // 악마와 수연이의 이동/확산 처리
	            if (p.isDevil) {
	                moveDevil(p);
	            } else {
	                // moveSoo는 이제 boolean을 반환하여 이동 가능 여부만 체크
	                moveSoo(p); 
	            }
	        }
		}
		
		return -1;
	}

	private static void moveDevil(Point p) {
		for(int i = 0; i < 4; i++) {
			int nx = p.x + dx[i];
			int ny = p.y + dy[i];
			
			if(check(nx, ny) || map[nx][ny] == 'D' || map[nx][ny] == '*') continue;
			
			if(map[nx][ny] == '.' || map[nx][ny] == 'S') {
				map[nx][ny] = '*';
				q.offer(new Point(nx, ny, 0, true));
			}
		}
	}

	private static int moveSoo(Point p) {
		for(int i = 0; i < 4; i++) {
			int nx = p.x + dx[i];
			int ny = p.y + dy[i];
			
			if(check(nx, ny) || map[nx][ny] == '*') continue;
			
			// 여신에게 도착하면 성공
            if (map[nx][ny] == 'D') {
                return p.time + 1;
            }
            
			if(map[nx][ny] == '.' && !visited[nx][ny]) {
				visited[nx][ny] = true;
                q.offer(new Point(nx, ny, p.time + 1, false));
			}
		}
		
		return -1;
	}
	
	
	private static boolean check(int nx, int ny) {
		return nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 'X';
	}
	
	static class Point {
		int x;
		int y;
		int time;
		boolean isDevil;
		
		public Point(int x, int y, int time, boolean isDevil) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isDevil = isDevil;
        }
	}
}
