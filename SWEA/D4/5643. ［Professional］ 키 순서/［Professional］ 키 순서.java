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
			boolean[][] visited = new boolean[N + 1][N + 1];
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				visited[a][b] = true;
			}
			
			for(int k = 1; k <= N; k++) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						if(visited[i][k] && visited[k][j]) {
							visited[i][j] = true;
						}
					}
				}
			}
            
			int ans = 0;
			for(int i = 1; i <= N; i++) {
				int cnt = 0;
				
				for(int j = 1; j <= N; j++) {
					if(i != j && (visited[i][j] || visited[j][i])) {
						cnt++;
					}
				}
				
				if(cnt == N - 1) {
					ans++;
				}
			}
			
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		
		System.out.println(sb);
	}

}
