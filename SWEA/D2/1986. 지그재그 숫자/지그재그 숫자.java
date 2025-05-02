
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 1; i <= T; i++) {
			int N = Integer.parseInt(br.readLine());
			int temp = 0;
			
			for(int num = 1; num <= N; num++) {
				if(num % 2 == 0) {
					temp -= num;
				} else {
					temp += num;
				}
			}
			sb.append("#" + i + " ").append(temp + "\n");
		}

		System.out.println(sb);
	}

}
