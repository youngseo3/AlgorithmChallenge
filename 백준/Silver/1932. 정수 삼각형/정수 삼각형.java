import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][n + 1];
		int[][] dp = new int[n + 1][n + 1];
		int max = 0;
        
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = arr[1][1];
		for(int i = 2; i <= n; i++) {		
			for(int j = 1; j <= i; j++) {
				for(int k = j - 1; k <= j; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + arr[i][j]);
				}
			}
		}
		
		for(int i = 1; i <= n; i++) {
			max = Math.max(max, dp[n][i]);
		}
		
		System.out.println(max);
	}

}
