import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] check = new boolean[N + 1][N + 1];
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			check[a][b] = true;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(check[i][k] && check[k][j]) {
						check[i][j] = true;
					}
				}
			}
		}
		
		int[] cnt = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(check[i][j] || check[j][i]) {
					cnt[i]++;
				}
			}
		}
		
		int ans = 0;
		for(int num: cnt) {
			if(num == N - 1) ans++;
		}
		
		System.out.println(ans);
	}

}
