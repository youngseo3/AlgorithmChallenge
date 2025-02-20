import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] A = new int[N + 1];
        for(int i = 1; i < N + 1; i++) {
        	A[i] = Integer.parseInt(st.nextToken());
        }
       
        int[] dp = new int[N + 1];
        int lis = 1;
        for(int i = 1; i < N + 1; i++) {
        	dp[i] = 1;
        	
        	for(int j = 1; j < i; j++) {
        		if(A[i] > A[j]) {
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        			lis = Math.max(lis, dp[i]);
        		}
        	}
        }
        
		sb.append(lis + "\n");
		Stack<Integer> stack = new Stack<>();
		for(int i = N; i >= 1; i--) {
			if(dp[i] == lis) {
				stack.push(A[i]);
				lis--;
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb);
	}

}
