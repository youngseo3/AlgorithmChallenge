import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, t;
	static boolean[] check;
	static int[][] board;
	
	static int MIN_RESULT = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = 0;
        N = Integer.parseInt(br.readLine());
        
        check = new boolean[N];
        board = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	for(int j = 0; j < N; j++) {
            	board[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        for(t = 1; t < N; t++) {
            combination(0, 0);
        }
        
        System.out.println(MIN_RESULT);
	}
    
    static void combination(int start, int depth) {
    	if(depth == t) {
        	MIN_RESULT = Math.min(MIN_RESULT, getResult());
        	
        	if(MIN_RESULT == 0) {
        		System.out.println(MIN_RESULT);		
        		System.exit(0);			
        	}			
        	return;
    	} 
    	
    	for(int i = start; i < N; i++) {
    		if(!check[i]) {
    			check[i] = true;
    			combination(i + 1, depth + 1);
    			check[i] = false;
    		}
    	}
    }
    
    static int getResult() {
    	int start = 0;
    	int link = 0;
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(check[i] && check[j]) {
    				start += board[i][j];
    			} else if(!check[i] && !check[j]) {
    				link += board[i][j];
    			}
    		}
    	}
    	
        return Math.abs(start - link);
    }

}
