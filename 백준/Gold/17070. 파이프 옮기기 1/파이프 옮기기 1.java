import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] home;
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		home = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N + 1][N + 1][3];
		dp[1][2][0] = 1;
		
		for(int r = 1; r <= N; r++) {
			for(int c = 3; c <= N; c++) {		
				if(home[r][c] == 0) {
					dp[r][c][0] = dp[r][c - 1][0] + dp[r][c - 1][2];
					dp[r][c][1] = dp[r - 1][c][1] + dp[r - 1][c][2];

					if(home[r][c] == 0 && home[r - 1][c] == 0 && home[r][c - 1] == 0) {
						dp[r][c][2] = dp[r - 1][c - 1][2] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][0];
					}
				}
			}
		}
		
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }
}
