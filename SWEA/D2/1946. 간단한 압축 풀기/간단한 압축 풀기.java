import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String temp = "";
			String result = "";
			int idx = 0;
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String C = st.nextToken();
				int K = Integer.parseInt(st.nextToken());
				
				for(int j = 0; j < K; j++) {
					temp += C;
				}
			}
			
			for(int i = 0; i + 10 < temp.length(); i += 10) {
				result += temp.substring(i, i + 10);
				result += "\n";
				idx = i + 10;
			}
			
			result += temp.substring(idx, temp.length());
			sb.append("#" + tc + "\n").append(result + "\n");
		}
		
		System.out.println(sb);
	}

}
