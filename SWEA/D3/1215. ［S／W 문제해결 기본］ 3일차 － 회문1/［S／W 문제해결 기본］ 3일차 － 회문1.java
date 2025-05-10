import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static int length;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int tc = 1; tc <= 10; tc++) {
			length = Integer.parseInt(br.readLine());
			cnt = 0;
			String[] map = new String[8];

			for(int i = 0; i < 8; i++) {
				String str = br.readLine();
				map[i] = str;
				
				getResult(str);
			}
			
			for(int i = 0; i < 8; i++) {
				String temp = "";
				
				for(int j = 0; j < 8; j++) {
					temp += map[j].charAt(i);
				}
				
				getResult(temp);
			}
			
			sb.append("#" + tc + " ").append(cnt + "\n");
		}
		
		System.out.println(sb);
	}

	private static void getResult(String str) {
		for(int j = 0; j <= 8 - length; j++) {
			String subString = str.substring(j, j + length);
			StringBuilder sbSubString = new StringBuilder(subString);

			if(subString.equals(sbSubString.reverse().toString())) {
				cnt++;
			}
		}
	}

}
