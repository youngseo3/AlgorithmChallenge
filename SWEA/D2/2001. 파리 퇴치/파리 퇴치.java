import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++) {
        	int max = 0;
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
    		int[][] area = new int[N][N];

        	for(int r = 0; r < N; r++) {
        		st = new StringTokenizer(br.readLine());
        		
        		for(int c = 0; c < N; c++) {
            		area[r][c] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	for(int r = 0; r + M - 1 < N; r++) {        		
        		for(int c = 0; c + M - 1 < N; c++) {
        			int sum = 0;
            		
        			for(int j = 0; j < M; j++) {
        				for(int k = 0; k < M; k++) {
        					sum += area[r + j][c + k];
            			}
        			}
        			
        			max = Math.max(max, sum);
        		}
        	}

        	sb.append("#" + i + " ").append(max + "\n");
        }
        
        System.out.println(sb);
	}
}
