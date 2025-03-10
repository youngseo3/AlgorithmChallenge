import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        int n = 1_000_000;
        long DIV = 1_000_000_009;
        
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        
        for(int i = 4; i <= n; i++) {
        	dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % DIV;
        }
        
        while(T-- > 0) {
        	int a = Integer.parseInt(br.readLine());
        	sb.append(dp[a] + "\n");
        }
        
        System.out.println(sb);
	}

}
