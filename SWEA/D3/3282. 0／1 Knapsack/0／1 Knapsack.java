import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N + 1][K + 1];
			
			int[] V = new int[N + 1];
			int[] C = new int[N + 1];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				V[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= N; i++) {
				for(int v = 1; v <= K; v++) {
					if(V[i] > v) {
						dp[i][v] = dp[i - 1][v];
					}
					else {
						dp[i][v] = Math.max(C[i] + dp[i - 1][v - V[i]], dp[i - 1][v]);
					}
				}
			}
			
			sb.append("#" + tc + " ").append(dp[N][K] + "\n");
		}
		
		System.out.println(sb);
	}

}
