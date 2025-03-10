import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());      
        
        int[][] A = new int[N][3];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < 3; j++) {
            	A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int[][] dp = new int[N][3];
        for(int i = 0; i < 3; i++) {
        	dp[0][i] = A[0][i];
        }
        
        for(int i = 1; i < N; i++) {
        	dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + A[i][0];
        	dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + A[i][1];
        	dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + A[i][2];
        }
        
        int temp = Math.min(dp[N - 1][0], dp[N - 1][1]);
        int result = Math.min(dp[N - 1][2], temp);
        
        System.out.println(result);
	}

}
