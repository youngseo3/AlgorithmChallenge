import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][M + 1];
		long[][] dp = new long[1001][1001];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				dp[i][j] = Math.max(dp[i][j], Math.max(Math.max(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + map[i][j]);
			}
		}
		
		System.out.println(dp[N][M]);
	}

}
