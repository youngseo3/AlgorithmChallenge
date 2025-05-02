import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] grade = {"D0", "C-", "C0", "C+", "B-", "B0", "B+", "A-", "A0", "A+"};
				
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] result = new int[N];
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				int middleTest = Integer.parseInt(st.nextToken()) * 35;
				int finalTest = Integer.parseInt(st.nextToken()) * 45;
				int homework = Integer.parseInt(st.nextToken()) * 20;
				
				result[j] = middleTest + finalTest + homework;
			}
			
			int temp = result[K - 1];
			
			Arrays.sort(result);
			for(int j = 0; j < N; j++) {
				if(result[j] == temp) {
					int div = N / 10;
					
					sb.append("#" + i + " ").append(grade[j / div] + "\n");
					break;
				}
			}
		}
		
		System.out.println(sb);

	}

}
