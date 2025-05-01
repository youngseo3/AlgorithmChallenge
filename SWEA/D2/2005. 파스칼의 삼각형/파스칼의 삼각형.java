import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for(int i = 1; i <= T; i++) {
        	sb.append("#" + i + "\n");
        	
        	int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[N + 1][N + 1];
            dp[1][1] = 1;
            
        	for(int j = 2; j <= N; j++) {
        		for(int k = 1; k <= N; k++) {
        			dp[j][k] = dp[j - 1][k - 1] + dp[j - 1][k];
        		}
        	}
        	
        	for(int j = 1; j <= N; j++) {
        		for(int k = 1; k <= N; k++) {
        			if(dp[j][k] != 0) {
            			sb.append(dp[j][k]).append(" ");
        			}
        		}
        		sb.append("\n");
        	}
        }
        
        System.out.println(sb);
	}

}
