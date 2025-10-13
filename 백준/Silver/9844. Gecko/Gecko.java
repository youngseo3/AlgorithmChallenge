import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int[][] arr = new int[h][w];
		int[][] dp = new int[h][w];
		
		for(int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < w; i++) {
			dp[0][i] = arr[0][i];
		}
		
		for(int i = 1; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if (w == 1) {
		            dp[i][j] = dp[i - 1][j] + arr[i][j];
		            continue; // 아래 로직은 실행할 필요 없으므로 다음 j로 넘어감 (사실상 루프 종료)
		        }
				
				if(j == 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j + 1]) + arr[i][j];
				} else if(j == w - 1) {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
				} else {
					dp[i][j] = Math.max(Math.max(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1]) + arr[i][j];
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				max = Math.max(dp[i][j], max);
			}
		}
		
		System.out.println(max);
	}

}
