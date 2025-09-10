import java.io.*;
import java.util.*;
	
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] dist = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dist[a][b] = Math.min(dist[a][b], c);
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
						dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(dist[i][j] == Integer.MAX_VALUE) {
					dist[i][j] = 0;
				}
				
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
