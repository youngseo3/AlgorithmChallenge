import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			long[] cost = new long[N];
			
			for(int i = 0; i < N; i++) {
				cost[i] = Long.parseLong(st.nextToken());
			}
			
			long current = cost[N - 1];
			long max = 0;
			
			for(int i = N - 1; i >= 0; i--) {
				if(current < cost[i]) {
					current = cost[i];
				} else {
					max += current - cost[i];
				}
			}
			
			System.out.println("#" + tc + " " + max);
		}
	}

}
