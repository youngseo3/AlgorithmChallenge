import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N + 1];

        int[] p = new int[N + 1];
        for(int i = 1; i < N + 1; i++) {
        	p[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 1; i < N + 1; i++) {
        	dp[i] = p[i];
        	
        	for(int j = 1; j < i; j++) {
        		dp[i] = Math.min(p[j] + dp[i - j], dp[i]);        	
        	}
        }
        
        System.out.println(dp[N]);
	}
	

}
