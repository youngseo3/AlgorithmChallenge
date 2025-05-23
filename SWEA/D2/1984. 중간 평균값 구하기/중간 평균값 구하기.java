import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int sum = 0;
			
			int[] arr = new int[10];
			for(int i = 0; i < 10; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				max = Math.max(max, arr[i]);
				min = Math.min(min, arr[i]);
			}
			
			for(int i = 0; i < 10; i++) {
				if(max != arr[i] && min != arr[i]) {
					sum += arr[i];
				}
			}
			
			double result = (double)sum / 8.0;
			
			sb.append("#" + tc + " ").append(String.format("%.0f", result) + "\n");
		}
		
		System.out.println(sb);
	}

}
