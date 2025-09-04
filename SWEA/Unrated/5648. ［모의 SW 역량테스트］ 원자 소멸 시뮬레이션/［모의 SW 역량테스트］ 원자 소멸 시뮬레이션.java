import java.io.*;
import java.util.*;

public class Solution {

	static int N, MAX = 4001;
	static final int dx[] = {0, 0, -1, 1}; // dir 0,1: 좌우이동 없음
	static final int dy[] = {1, -1, 0, 0}; // dir 0: 상, dir 1: 하
	static Point[] points;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			map = new int[MAX][MAX];
			N = Integer.parseInt(br.readLine());
			points = new Point[N];		
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());

				x = (x + 1000) * 2;
				y = (y + 1000) * 2;
				points[i] = new Point(x, y, dir, k);
				map[y][x]++;
			}
			
			// N == 1
			
			int ans = 0;
			
			while(!isAllDead()) {
				// 이동
				for(int i = 0; i < points.length; i++) {
					Point p = points[i];
					if(p.isDead) continue;
					int x = p.x;
					int y = p.y;
					int dir = p.dir;
					
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if(nx >= 0 && nx < MAX && ny >= 0 && ny < MAX) {
						p.x = nx;
						p.y = ny;
						map[y][x]--;
						map[ny][nx]++;
					}
					else {
						map[y][x]--;
						p.isDead = true;
					}
				}
				
				// 소멸
				for(int i = 0; i < points.length; i++) {
					Point p = points[i];
					if(p.isDead) continue;
					
					int x = p.x;
					int y = p.y;
					if(map[y][x] <= 1) continue;
					p.isDead = true;
					map[y][x]--;
					ans += p.energy;

					for(int j = 0; j < points.length; j++) {
						if(i == j) continue;
						Point next = points[j];
						
						if(next.isDead) continue;
						if(p.x != next.x || p.y != next.y) continue;
						map[next.y][next.x]--;
						next.isDead = true;
						ans += next.energy;
					}
				}
			}
			sb.append("#" + tc + " ").append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean isAllDead() {
		for (int i = 0; i < points.length; i++) {
            if(!points[i].isDead) return false;
        }
        return true;
	}

	static class Point {
		int x;
		int y;
		int dir;
		int energy;
		boolean isDead;
		
		public Point(int x, int y, int dir, int energy) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
			this.isDead = false;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", dir=" + dir + ", energy=" + energy + "]";
		}
	}
}
