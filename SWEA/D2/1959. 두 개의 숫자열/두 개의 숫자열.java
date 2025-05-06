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
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	
        	int max = Integer.MIN_VALUE;
        	
        	int[] A = new int[N];
        	int[] B = new int[M];
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		A[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < M; i++) {
        		B[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int abs = Math.abs(M - N);
        	if(abs == 0) {
        		for(int i = 0; i < M; i++) {
        			max += A[i] * B[i];
    			}
        	}
        	
        	for(int i = 0; i <= abs; i++) {
        		int temp = 0;
        		
        		if(N > M) {
        			for(int j = 0; j < M; j++) {
        				temp += A[j + i] * B[j];
        			}
        		} else if(N < M) {
        			for(int j = 0; j < N; j++) {
        				temp += A[j] * B[j + i];
        			}
        		}
        		
        		max = Math.max(max, temp);
        	}
        	
        	sb.append("#" + tc + " ").append(max + "\n");
        }
        
        System.out.println(sb);

	}
}
