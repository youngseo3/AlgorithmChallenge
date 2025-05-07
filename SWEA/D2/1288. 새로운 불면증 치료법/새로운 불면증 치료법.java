import java.io.*;
public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			boolean[] visited = new boolean[10];
			boolean sleep = false;
			int k = 1;
			while(!sleep) {
				int temp = N * k++;
				while(temp != 0) {
					int remain = temp % 10;
					visited[remain] = true;
					temp /= 10;
				}
				
				sleep = true;
				for(int i = 0; i < 10; i++) {
					if(!visited[i]) {
						sleep = false;
						break;
					}
				}
			}
			
			sb.append("#" + tc + " ").append((k - 1) * N + "\n");
		}
		
		System.out.println(sb);
	}

}
