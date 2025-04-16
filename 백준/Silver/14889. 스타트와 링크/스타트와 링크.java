import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[] check;
	static int[][] board;
	
	static int MIN_RESULT = Integer.MAX_VALUE;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        
        board = new int[N][N];
        check = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
            	board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        combination(0, 0);
        System.out.println(MIN_RESULT);
    }
    
    static void combination(int start, int depth) {
    	if(depth == N / 2) {
    		MIN_RESULT = Math.min(MIN_RESULT, getResult());
    		return;
    	}
    	
    	for(int i = start; i < N; i++) {

            check[i] = true;
            combination(i + 1, depth + 1);
            check[i] = false;    	
        }
    }
    
    static int getResult() {
        int start = 0;
        int link = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i == j) continue;

                if(check[i] && check[j]) start += board[i][j];
                if(!check[i] && !check[j]) link += board[i][j];
            }
        }
        return Math.abs(start - link);
    }    
    

}
