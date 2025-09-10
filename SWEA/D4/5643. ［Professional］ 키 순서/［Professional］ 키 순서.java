import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			int[][] dist = new int[N + 1][N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				dist[a][b] = -1;
				dist[b][a] = 1;
			}
			
			for(int k = 1; k <= N; k++) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						if(dist[i][k] == 1 && dist[k][j] == 1) {
							dist[i][j] = 1;
						}
						else if(dist[i][k] == -1 && dist[k][j] == -1) {
							dist[i][j] = -1;
						}
					}
				}
			}
			
			int ans = 0;
			for(int i = 1; i <= N; i++) {
				int cnt = 0;
				
				for(int j = 1; j <= N; j++) {
					if(dist[i][j] == 0) {
						cnt++;
					}
					
					if(cnt > 1) {
						cnt = 0;
						break;
					}
				}
				
				if(cnt == 1) {
					ans++;
				}
			}
			
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		
		System.out.println(sb);
	}

}
