import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine()); 
		
		// 1번부터  k까지 경유할 때 i에서 j로 가는데 최단 거리
		int[][][] dp = new int[n + 1][n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
				if(i == j) {
					Arrays.fill(dp[i][j], 0);
				}
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dp[a][b][0] = Math.min(dp[a][b][0], c);
		}
		
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(dp[i][k][k - 1] != Integer.MAX_VALUE && dp[k][j][k - 1] != Integer.MAX_VALUE) {
						dp[i][j][k] = Math.min(dp[i][j][k - 1], dp[i][k][k - 1] + dp[k][j][k - 1]);
					}
					else {
						dp[i][j][k] = dp[i][j][k - 1];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(dp[i][j][n] == Integer.MAX_VALUE) {
                    sb.append("0 ");
                } else {
                    sb.append(dp[i][j][n] + " ");
                }
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
	}

}
