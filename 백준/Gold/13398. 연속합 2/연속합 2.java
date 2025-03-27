import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = -1001;

		int[] ary = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			ary[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp1 = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			dp1[i] = Math.max(dp1[i - 1] + ary[i], ary[i]);
			ans = Math.max(ans, dp1[i]);
		}
		
		int[] dp2 = new int[n + 1];
		dp2[n] = ary[n];
		for(int i = n - 1; i >= 1; i--) {
			dp2[i] = Math.max(dp2[i + 1] + ary[i], ary[i]);
		}
		
		for(int i = 2; i < n; i++) {
			int temp = dp1[i - 1] + dp2[i + 1];
			
			ans = Math.max(ans, temp);
		}
		
		System.out.println(ans);
		
	}
}
