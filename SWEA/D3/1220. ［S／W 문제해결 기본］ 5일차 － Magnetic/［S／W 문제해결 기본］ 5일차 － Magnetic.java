import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 10;
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int cnt = 0;
			// N은 윗부분, S는 아래부분이고 1은 N, 2는 S
			for(int i = 0; i < N; i++) {		
				for(int j = 0; j < N; j++) {
					if(map[j][i] == 1) {
						while(j < N - 1) {
							j++;
							if(map[j][i] == 2) {
								cnt++;
								break;
							}
						}
					}
				}
			}
			
			sb.append("#" + tc + " ").append(cnt + "\n");
		}
		
		System.out.println(sb);
	}

}
