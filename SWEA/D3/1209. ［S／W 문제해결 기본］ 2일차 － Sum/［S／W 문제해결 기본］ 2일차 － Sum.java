import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());

			int[][] board = new int[100][100];
			
			for(int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int[] sumCol = new int[100];
			int[] sumRow = new int[100];
			int sumRight = 0;
			int sumLeft = 0;
			int max = 0;
			
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					sumCol[i] += board[i][j];
					sumRow[i] += board[j][i];
					
					if(i == j) {
						sumRight += board[i][j];
					}
					
					if(i + j == 99) {
						sumLeft += board[i][j];
					}
				}
				
				max = Math.max(max, Math.max(sumCol[i], sumRow[i]));
			}
			
			max = Math.max(max, Math.max(sumRight, sumLeft));
			sb.append("#" + tc + " ").append(max + "\n");
		}
		
		System.out.println(sb);
	}

}
