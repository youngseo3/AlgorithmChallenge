import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int[] height = new int[N];
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			int sum = 0;
			for(int i = 2; i < N - 2; i++) {
				int leftMax = Math.max(height[i - 2], height[i - 1]);
				int rightMax = Math.max(height[i + 1], height[i + 2]);
				int maxHeight = Math.max(leftMax, rightMax);
				
				if(height[i] > maxHeight) {
					sum += height[i] - maxHeight;
				}
			}
			
			
			
			sb.append("#" + tc + " ").append(sum + "\n");
		}
		
		System.out.println(sb);
	}

}
