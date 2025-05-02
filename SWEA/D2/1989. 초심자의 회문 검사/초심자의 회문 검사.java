import java.io.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 1; i <= T; i++) {
			String word = br.readLine();
			String reverse = "";
			int tf = 0;
			
			for(int c = word.length() - 1; c >= 0; c--) {
				reverse += word.charAt(c);
			}
			
			
			if(word.equals(reverse)) {
				tf = 1;
			}
			
			sb.append("#" + i + " ").append(tf + "\n");
		}
		
		System.out.println(sb);
	}

}
