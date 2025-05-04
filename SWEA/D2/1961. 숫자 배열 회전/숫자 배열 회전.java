import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[][] matrix = new String[N][N];
			
			sb.append("#" + tc + "\n");
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					matrix[i][j] = st.nextToken();
				}
			}
			
			for(int i = 0; i < N; i++) {				
				String str1 = "";
				String str2 = "";
				String str3 = "";

				for(int j = N - 1; j >= 0; j--) {
					str1 += matrix[j][i];
					str2 += matrix[N - i - 1][j];
				}
				
				for(int j = 0; j < N; j++) {
					str3 += matrix[j][N - i - 1];
				}
				
				sb.append(str1 + " " + str2 + " " + str3).append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
