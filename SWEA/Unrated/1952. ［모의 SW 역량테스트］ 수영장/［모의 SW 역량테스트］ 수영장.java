import java.io.*;
import java.util.*;

public class Solution {

	static int min;
	static int[] price, month;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			price = new int[4];
			for(int i = 0; i < price.length; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			month = new int[12];
			
			min = Integer.MAX_VALUE;
			for(int i = 0; i < month.length; i++) {
				month[i] = Integer.parseInt(st.nextToken());
			}
			
			backTrack(0, 0);
			
			min = Math.min(price[3], min);
			
			sb.append("#" + tc + " ").append(min + "\n");
		}
		
		System.out.println(sb);
	}
	
	static void backTrack(int depth, int sum) {
		if(depth >= 12) {
			min = Math.min(sum, min);
			return;
		}
		
		// 1일
		backTrack(depth + 1, sum + month[depth] * price[0]);
		
		// 1달
		backTrack(depth + 1, sum + price[1]);

		// 3달
		backTrack(depth + 3, sum + price[2]);
	}

}
