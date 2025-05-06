import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] time = new int[4];
			
			for(int i = 0; i < 4; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			int result1 = time[0] + time[2];
			int result2 = time[1] + time[3];
			
			if(result2 > 59) {
				result2 %= 60;
				result1 += 1;
			}
			
			if(result1 > 12) {
				result1 %= 12;
			}
			
            if(result1 == 0) {
				result1 = 12;
			}
            
			sb.append("#" + tc + " ").append(result1 + " " + result2 + "\n");
		}
		
		System.out.println(sb);
	}

}
