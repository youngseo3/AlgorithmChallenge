import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[][] board = new int[9][9];

		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			boolean isAnswer = true;

			for(int i = 0; i < 9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());

				for(int j = 0; j < 9; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 1 ~ 9 숫자가 겹치지 않는지 확인 
			for(int i = 0; i < 9; i++) {
				boolean[] col = new boolean[10];
				boolean[] row = new boolean[10];

				for(int j = 0; j < 9; j++) {
					// 가로 방향으로 검증 
					if(!col[board[i][j]]) { 
						col[board[i][j]] = true;
					} else if(col[board[i][j]]){
						isAnswer = false;
						break;
					}
					
					// 세로 방향으로 검증 
					if(!row[board[j][i]]) {
						row[board[j][i]] = true;
					} else if(row[board[j][i]]){
						isAnswer = false;
						break;
					}
				}
			}
			
			// 3 * 3 격자만큼 확인 
			for(int i = 0; i < 9; i += 3) {
				for(int j = 0; j < 9; j += 3) {
					boolean[] matrix = new boolean[10];

					for(int k = i; k < i + 3; k++) {
						for(int l = j; l < j + 3; l++) {
							if(!matrix[board[k][l]]) {
								matrix[board[k][l]] = true;
							} else if(matrix[board[k][l]]) {
								isAnswer = false;
								break;
							}
						}
					}
				}
			}
            
			sb.append("#" + tc + " ");
			if(!isAnswer) {
				sb.append(0 + "\n");
			} else {
				sb.append(1 + "\n");
			}
		}
		
		System.out.println(sb);
	}

}
