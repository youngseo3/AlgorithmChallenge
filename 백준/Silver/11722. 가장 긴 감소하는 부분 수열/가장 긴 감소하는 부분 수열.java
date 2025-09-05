import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];

		for(int i = 0; i < N; i++) {
			dp[i] = 1;

			for(int j = 0; j < i; j++) {
				if(nums[j] > nums[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		// dp 배열의 값들 중 최댓값을 찾아야 함
		int max = 0;
		for (int length : dp) {
			if (length > max) {
				max = length;
			}
		}

		System.out.println(max);
	}

}
