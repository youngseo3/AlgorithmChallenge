import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k + 1];
		dp[0] = 1;
		
		for(int coin: arr) {
			for(int j = coin; j <= k; j++) {
				dp[j] = dp[j] + dp[j - coin];
			}
		}
		
		System.out.println(dp[k]);
	}

}
