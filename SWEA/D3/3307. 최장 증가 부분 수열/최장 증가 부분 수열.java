import java.io.*;
import java.util.*;

public class Solution {

	static List<Integer> lis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			lis = new ArrayList<>();
			
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(lis.size() == 0 || lis.get(lis.size() - 1) < num) {
					lis.add(num);
				}
				else {
					lis.set(lowerBound(num), num);
				}
			}
			
			sb.append("#" + tc + " ").append(lis.size() + "\n");
		}
		
		System.out.println(sb);
	}

	private static int lowerBound(int target) {
		int left = 0;
		int right = lis.size();
		
		while(left < right) {
			int mid = (left + right) / 2;
			
			if(lis.get(mid) < target) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		
		return right;
	}

}
