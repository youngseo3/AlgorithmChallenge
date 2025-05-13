import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int tc = 1; tc <= 10; tc++) {
			int cnt = 0;
			br.readLine();
			
			String search = br.readLine();
			String str = br.readLine();
			
			int len = search.length();
			for(int i = 0; i + len <= str.length(); i++) {
				if(search.equals(str.substring(i, i + len))) {
					cnt++;
				}
			}
			
			sb.append("#" + tc + " ").append(cnt + "\n");
		}
		
		System.out.println(sb);

	}

}
