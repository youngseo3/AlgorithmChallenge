import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] A = new int[n + 1];
		int[] dp = new int[n + 1];

		for(int i = 1; i <= n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = 1;
		for(int i = 2; i <= n; i++) {	
			dp[i] = 1;
			
			for(int j = 1; j < i; j++) {
				if(A[i] < A[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}	
		}
		
		int max = 0;

        for(int i = 1; i <= n; i++) {
            max = Math.max(dp[i], max);
        }
        
        System.out.println(max);
	}

}
