import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken()); 
        long DIV = 1_000_000_000;
        
        long[][] dp = new long[K + 1][N + 1];
        
        for(int i = 1; i <= K; i++) {
        	dp[i][0] = 1;
        }
        
        for(int i = 1; i <= K; i++) {
        	for(int j = 1; j<= N; j++) {
        		dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % DIV;
        	}
        }
        
        System.out.println(dp[K][N]);
        
        
	}

}
