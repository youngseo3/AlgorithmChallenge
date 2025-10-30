import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] plan = new int[N + 1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int pasta = Integer.parseInt(st.nextToken());
			plan[day] = pasta;
		}
		
		// dp[day][pasta_type][consecutive_count]
		int[][][] dp = new int[N + 1][4][3];
		
		// 1. 초기값 설정 (Base Case)
		if (plan[1] != 0) { // 1일차 계획이 정해져 있다면
			dp[1][plan[1]][1] = 1;
		} else { // 정해져 있지 않다면
			dp[1][1][1] = 1;
			dp[1][2][1] = 1;
			dp[1][3][1] = 1;
		}
		
		// 2. DP 테이블 채우기 (점화식)
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= 3; j++) { // 오늘 먹을 파스타 j
				
				// 만약 i일차 계획이 있는데 j 파스타가 아니면 skip
				if (plan[i] != 0 && plan[i] != j) {
					continue;
				}

				// Case 1: j번 파스타를 처음 먹는 경우 (k=1)
				// 어제 다른 파스타로 끝난 모든 경우의 수를 더한다.
				int sum = 0;
				for (int p = 1; p <= 3; p++) {
					if (p != j) {
						sum = (sum + dp[i-1][p][1] + dp[i-1][p][2]) % 10000;
					}
				}
				dp[i][j][1] = sum;
				
				// Case 2: j번 파스타를 연속 2일째 먹는 경우 (k=2)
				// 어제 j번 파스타를 1일째 먹은 경우의 수를 가져온다.
				dp[i][j][2] = dp[i-1][j][1];
			}
		}
		
		// 3. 최종 결과 계산
		int total = 0;
		for (int j = 1; j <= 3; j++) {
			for (int k = 1; k <= 2; k++) {
				total = (total + dp[N][j][k]) % 10000;
			}
		}
		
		System.out.println(total);
	}
}