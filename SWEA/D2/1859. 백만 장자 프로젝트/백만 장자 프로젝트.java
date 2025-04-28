import java.io.*;
import java.util.StringTokenizer;

class Solution {
	
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();

	    int T = Integer.parseInt(br.readLine());

	    for (int tc = 1; tc <= T; tc++) {
	        int N = Integer.parseInt(br.readLine());
	        int[] prices = new int[N];

	        StringTokenizer st = new StringTokenizer(br.readLine());
	        for (int i = 0; i < N; i++) {
	            prices[i] = Integer.parseInt(st.nextToken());
	        }

	        // Start from the end and keep track of the maximum
	        int maxPrice = 0;
	        long profit = 0;

	        for (int i = N - 1; i >= 0; i--) {
	            if (prices[i] > maxPrice) {
	                maxPrice = prices[i];
	            } else {
	                profit += maxPrice - prices[i];
	            }
	        }

	        sb.append("#").append(tc).append(" ").append(profit).append("\n");
	    }

	    System.out.println(sb);
	}
}
