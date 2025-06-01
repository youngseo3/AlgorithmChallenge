import java.io.*;
import java.util.*;

public class Main {

	static int w, h;
	static int result;
	static int[][] map;
	static int[] r = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] c = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String str = "";
		while(!(str = br.readLine()).equals("0 0")) {
			st = new StringTokenizer(str);
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			result = 0;
			map = new int[h][w];
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < h; i++) {			
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1) {
						bfs(j, i);
						result++;
					}
				}
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		
		map[y][x] = 0;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int i = 0; i < 8; i++) {
				int a = p.y + r[i];
				int b = p.x + c[i];
				
				if((a >= 0 && a < h) && (b >= 0 && b < w) && map[a][b] == 1) {
					q.add(new Point(b, a));
					map[a][b] = 0;
				}
			}
		}
		
	}

}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
}
