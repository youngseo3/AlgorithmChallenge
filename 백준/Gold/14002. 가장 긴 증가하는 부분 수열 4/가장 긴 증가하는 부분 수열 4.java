import java.util.*;

public class Main {

	static List<Integer> lis = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] A = new int[N];
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		int[] dp = new int[N];
		int len = 1;
		for(int i = 0; i < N; i++) {
			dp[i] = 1;

			for(int j = 0; j < i; j++) {
				
				if(A[i] > A[j] && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
					len = Math.max(len, dp[i]);
				}
			}
		}

		System.out.println(len);
		
		Stack<Integer> st = new Stack<>();
		for(int i = N - 1; i >= 0; i--) {
			if(dp[i] == len) {
				st.push(A[i]);
				len--;
			}
		}
		
		while(!st.isEmpty()) {
			System.out.print(st.pop() + " ");
		}
	}
}
