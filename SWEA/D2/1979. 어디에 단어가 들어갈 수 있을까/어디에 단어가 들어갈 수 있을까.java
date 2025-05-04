import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int K;
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int result = 0;
			
			board = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 가로 구간 합 구하기
			result += findHorizontalWord();
			
			// 세로 구간 합 구하기
			result += findVerticalWord();
			
			sb.append("#" + tc + " ").append(result + "\n");
		}
		
		System.out.println(sb);
		
	}
	
	static int findHorizontalWord() {
		int word = 0;
		
		for(int i = 0; i < N; i++) {
			int cnt = 0;
			
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 1) {
					cnt++;
				} else {
					cnt = 0;
				}
				
				if(cnt == K) {
					if(j + 1 < N && board[i][j + 1] == 0) {
						word++;
					} else if(j + 1 == N) {
						word++;
					}
				} 
			}
		}
		
		return word;
	}
	
	static int findVerticalWord() {
int word = 0;
		
		for(int i = 0; i < N; i++) {
			int cnt = 0;
			
			for(int j = 0; j < N; j++) {
				if(board[j][i] == 1) {
					cnt++;
				} else {
					cnt = 0;
				}
				
				if(cnt == K) {
					if(j + 1 < N && board[j + 1][i] == 0) {
						word++;
					} else if(j + 1 == N) {
						word++;
					}
				} 
			}
		}
		
		return word;
	}

}
