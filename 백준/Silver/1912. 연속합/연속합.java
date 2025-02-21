import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] A = new int[N + 1];
        for(int i = 1; i < N + 1; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] dp = new int[N + 1];
        int max = -1001;
        
        for(int i = 1; i < N + 1; i++) {
        	dp[i] = Math.max(dp[i - 1] + A[i], A[i]);
        	max = Math.max(max, dp[i]);
        }
        
        System.out.println(max);
        
        
	}

}
