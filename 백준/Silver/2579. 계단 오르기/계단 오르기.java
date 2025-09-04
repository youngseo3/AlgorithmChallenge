import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] input = new int[301];
		int[] dp = new int[301];

//		0
//		1 => 1 // 10
//		2 => 1 2, 2 // 10 + 20, 20
//		
//		2 => 1 3, 2 3 // 10 + 15, 20 + 15
//		3 => 1 2 4, 1 3 4, 2 4 // dp[2] + dp[3]의 중복 한 개 제거
//		4 => 1 3 5, 1 2 4 5, 2 4 5, 2 3 5 
//		5 => 1 2 4 6, 1 3 4 6, 2 4 6, 1 3 5 6, 2 3 5 6
		for(int i = 1; i <= N; i++) {
			input[i] = sc.nextInt();
		}
		
		dp[1] = input[1];
		dp[2] = input[1] + input[2];
		
		for(int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 2], dp[i - 3] + input[i - 1]) + input[i];
		}
		
		System.out.println(dp[N]);
	}
}
