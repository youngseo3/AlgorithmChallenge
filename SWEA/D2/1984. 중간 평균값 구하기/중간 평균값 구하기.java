import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 1; i <= T; i++) {
			int max = Integer.MIN_VALUE;  // 최소값으로 초기화
            int min = Integer.MAX_VALUE;  // 최대값으로 초기화
			int sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 10; j++) {
				int temp = Integer.parseInt(st.nextToken());
				sum += temp;
				max = Math.max(max, temp);
				min = Math.min(min, temp);
			}
			
			sum = sum - max - min;
			double result = (double)sum / 8;
			
            sb.append("#").append(i).append(" ").append(Math.round(result)).append("\n");
		}
		
		System.out.println(sb);
	}

}
