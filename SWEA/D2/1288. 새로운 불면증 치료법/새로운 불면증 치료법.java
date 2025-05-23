import java.io.*;
import java.util.*;

public class Solution {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int temp = 0;
			boolean[] visited = new boolean[10];

			while(true) {
				int cnt = 0;
				temp += N;
				
				String str = String.valueOf(temp);
				for(int i = 0; i < str.length(); i++) {
					visited[Integer.parseInt(String.valueOf(str.charAt(i)))] = true;
				}
				
				for(int i = 0; i < 10; i++) {
					if(visited[i]) {
						cnt++;
					}
				}
				
				if(cnt == 10) {
					break;
				}
			}
			
			sb.append("#" + tc + " ").append(temp + "\n");
		}
		System.out.println(sb);
	}

}
