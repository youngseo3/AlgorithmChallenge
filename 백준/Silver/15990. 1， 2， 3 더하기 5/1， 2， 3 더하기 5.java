import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MAX_NUM = 1_000_01;
	static final int MOD = 1_000_000_009;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
        long[][] dp = new long[MAX_NUM][4]; // 행은 어떤 수인지, 열은 1,2,3으로 끝나는 
        dp[1][1] = 1; // 1
		dp[2][2] = 1; // 2
		dp[3][1] = 1; // 2+1
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1; // 3 
		
		for(int i = 4; i < MAX_NUM; i++) {
			dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % MOD; 
			dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % MOD; 
			dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % MOD; 
		}
		
        while(T-- > 0) {
        	int n = Integer.parseInt(br.readLine());
        	sb.append((dp[n][1] + dp[n][2] + dp[n][3]) % MOD + "\n");
        }
        
        System.out.println(sb);
	}

}
