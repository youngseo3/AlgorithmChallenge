import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] selected;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        
        N = Integer.parseInt(br.readLine());
        selected = new int[N];
        visited = new boolean[N];
        
        dfs(0);
        
        System.out.println(sb);
	}
	
	static void dfs(int depth) {
		if(depth == N) {
			for(int i = 0; i < N; i++) {
				sb.append(selected[i]).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true; 
				selected[depth] = i + 1;
				
				dfs(depth + 1);
				
				visited[i] = false; 
			}

		}
	}

}
