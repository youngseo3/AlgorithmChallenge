import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] A = new int[N];
			for(int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			int max = 1;
			int[] dp = new int[N];
			for(int i = 0; i < N; i++) {
				dp[i] = 1;
				
				for(int j = 0; j < i; j++) {
					if(A[i] > A[j]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
						max = Math.max(dp[i], max);
					}
				}
			}
			
			sb.append("#" + tc + " ").append(max + "\n");
		}
		
		System.out.println(sb);
	}

}
