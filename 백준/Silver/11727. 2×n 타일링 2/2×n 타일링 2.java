import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2xn_타일링2_11727
public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
       
        int[] dp = new int[n + 2];
        dp[1] = 1;
        dp[2] = 3;
        
        for(int i = 3; i <= n; i++) {
        	dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 2]) % 10007;
        }
        
        System.out.println(dp[n]);
        
	}

}
