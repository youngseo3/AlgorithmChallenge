import java.io.*;
import java.util.StringTokenizer;
public class Solution {

	static char[] nums;
	static int limitCount;
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nums = st.nextToken().toCharArray();
			limitCount = Integer.parseInt(st.nextToken());
			result = 0;
			dfs(0, 0);
			sb.append("#" + tc + " ").append(result + "\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int start, int depth) {
		if(depth == limitCount) {
			int value = Integer.parseInt(new String(nums));
			result = Math.max(result, value);
			return;
		}
		
		for(int i = start; i < nums.length; i++) {
			for(int j = i + 1; j < nums.length; j++) {
				swap(i, j);
				dfs(i, depth + 1);
				swap(i, j);
			}
		}
	}
	
	static void swap(int i, int j) {
		char tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

}
