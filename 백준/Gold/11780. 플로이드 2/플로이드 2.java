import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] cost = new int[n + 1][n + 1];
		int[][] path = new int[n + 1][n + 1];
		final int INF = 100_001 * 10; 
		
		for(int i = 1; i <= n; i++) {
			Arrays.fill(cost[i], INF);
			cost[i][i] = 0;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			cost[a][b] = Math.min(cost[a][b], c);
			path[a][b] = b;
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(cost[i][k] + cost[k][j] < cost[i][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
						path[i][j] = path[i][k];
					}
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if (cost[i][j] == INF) {
					System.out.print(0 + " ");
		        } else {
					System.out.print(cost[i][j] + " ");
		        }
			}
			System.out.println();
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(cost[i][j] == 0 || cost[i][j] == INF) {
					System.out.println(0);
					continue;
				}
				
				List<Integer> route = new ArrayList<>();
				int current = i;
				while(current != j) {
					route.add(current);
					current = path[current][j];
				}
				route.add(j); // 마지막 목적지
				
				System.out.print(route.size() + " ");
				for(int city: route) {
					System.out.print(city + " ");
				}
				System.out.println();
			}
		}
	}

}
