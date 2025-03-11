import java.io.BufferedReader;import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    
        int DIV = 9901;
        
        int[][] dp = new int[N + 1][3];
        dp[0][0] = 1;
       
        for(int i = 1; i <= N; i++) {
        	dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
        	dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
        	dp[i][2] = dp[i - 1][0] + dp[i - 1][1];
        	for(int j = 0; j < 3; j++) {
        		dp[i][j] %= DIV;
        	}
        }
        
        System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % DIV);
	}

}
