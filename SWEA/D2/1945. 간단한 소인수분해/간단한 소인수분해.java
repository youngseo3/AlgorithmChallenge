import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int[] arguments = new int[5];
			int N = Integer.parseInt(br.readLine());
			
			while(N != 1) {
				if(N % 2 == 0) {
					N /= 2;
					arguments[0]++;
				} 
				if(N % 3 == 0) {
					N /= 3;
					arguments[1]++;
				}  
				if(N % 5 == 0) {
					N /= 5;
					arguments[2]++;
				} 
				if(N % 7 == 0) {
					N /= 7;
					arguments[3]++;
				} 
				if(N % 11 == 0) {
					N /= 11;
					arguments[4]++;
				}
			}
			
			sb.append("#" + tc + " ");
			for(int i = 0; i < arguments.length; i++) {
				sb.append(arguments[i] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
