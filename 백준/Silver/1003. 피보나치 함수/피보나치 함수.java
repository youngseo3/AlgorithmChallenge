import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			
			int N = sc.nextInt();
			
			int[] dp0 = new int[41];
			dp0[0] = 1;
			dp0[1] = 0;
			for(int i = 2; i <= N; i++) {
				dp0[i] = dp0[i - 1] + dp0[i - 2];
			}
			
			int[] dp1 = new int[41];
			dp1[0] = 0;
			dp1[1] = 1;
			for(int i = 2; i <= N; i++) {
				dp1[i] = dp1[i - 1] + dp1[i - 2];
			}
			
			System.out.println(dp0[N] + " " + dp1[N]);
		}
	}
}
