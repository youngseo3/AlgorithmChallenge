import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static final long MOD = 1_000_000_000;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long cnt = 0;
      
        long[][] dp = new long[N + 1][10]; // 자릿 수, 0 ~ 9에 해당하는 경우의 수 
        for(int i = 1; i < 10; i++) {
        	dp[1][i] = 1; // 1의 자릿수에 해당하는 수는 0~9이므로 각각 경우의 수는 1로 초기화 
        }
        
        for(int i = 2; i < N + 1; i++) { // 두 번째 자리부터 
        	for(int j = 0; j < 10; j++) {
        		if(j == 0) {
        			dp[i][0] = dp[i - 1][1] % MOD;
        		} else if(j == 9) {
        			dp[i][9] = dp[i - 1][8] % MOD;
        		}
        		else {
        			dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
        		}
        	}
        }
        
        for(int i = 0; i < 10; i++) {
            cnt += dp[N][i];
        }
        
        System.out.println(cnt % MOD);
	}
}
