import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int tc = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());

            if(N == 0) break;
            
            int[][] rupees = new int[N][N];
            
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                
                for(int j = 0; j < N; j++) {
                    rupees[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int ans = dijkstra(rupees, N);
            
            sb.append("Problem " + tc++ + ": ").append(ans + "\n");
        }
        
        System.out.println(sb);
    }

    private static int dijkstra(int[][] rupees, int N) {
        int[][] dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = rupees[0][0];
        
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, rupees[0][0]));
        
        while(!pq.isEmpty()) {
        	Point current = pq.poll();
        	int x = current.x;
        	int y = current.y;
        	
        	for(int i = 0; i < 4; i++) {
        		int nx = x + dx[i];
        		int ny = y + dy[i];
        		
        		if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
        		
        		if(dist[x][y] + rupees[nx][ny] < dist[nx][ny]) {
        			dist[nx][ny] = dist[x][y] + rupees[nx][ny];
        			pq.offer(new Point(nx, ny, dist[nx][ny]));
        		}
        	}
        }
        
        return dist[N -1][N - 1];
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int rupee;
        
        public Point(int x, int y, int rupee) {
        	this.x = x;
        	this.y = y;
        	this.rupee = rupee;
        }

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.rupee, o.rupee);
		}
        
        
    }
}

