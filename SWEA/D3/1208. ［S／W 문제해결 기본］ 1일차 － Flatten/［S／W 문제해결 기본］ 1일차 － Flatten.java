import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] height;
	static int dumpCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
				
		for(int tc = 1; tc <= 10; tc++) {
			dumpCount = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			height = new int[100];
			for(int i = 0; i < 100; i++) {
				height[i] = Integer.parseInt(st.nextToken());;
			}
			
			for(int i = 0; i < dumpCount; i++) {
				Arrays.sort(height);
				height[0]++;
				height[99]--;
			}
			
			Arrays.sort(height);
			int result = height[99] - height[0];
			sb.append("#" + tc + " ").append(result + "\n");
		}
		
		System.out.println(sb);

	}

}
