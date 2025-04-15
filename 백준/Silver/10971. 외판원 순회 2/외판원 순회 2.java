import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] W;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        visited = new boolean[N];
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            for(int j = 0; j < N; j++) {
            	W[i][j] = Integer.parseInt(st.nextToken());
            }
        }   
        
        visited[0] = true;
        backTracking(0, 0, 1, 0);
        
        System.out.println(result);
	}
	
	static void backTracking(int start, int now, int depth, int cost) {
		if(W[now][start] != 0 && depth == N) {
			result = Math.min(result, cost + W[now][start]);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (W[now][i] > 0 && !visited[i]) {
				visited[i] = true;
				backTracking(start, i, depth + 1, cost + W[now][i]);
				visited[i] = false;
			}
		}
	}

}
