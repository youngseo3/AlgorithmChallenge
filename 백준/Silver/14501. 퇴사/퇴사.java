import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, result;
	static int[] T;
	static int[] P;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        T = new int[N];
        P = new int[N];
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        backTracking(0, 0);
        
        System.out.println(result);
	}
    
    static void backTracking(int start, int sum) {
    	if(start > N) {
    		return;
    	} else {
    		result = Math.max(result, sum);
    	}
    	
    	for(int i = start; i < N; i++) {
    		backTracking(i + T[i], sum + P[i]);
    	}
    }

}
