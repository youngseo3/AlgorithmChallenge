import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int max;
	static boolean[] visited;
	static int[] arr;
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        visited = new boolean[N];
        arr = new int[N];
        selected = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
       
        dfs(0);
        
        System.out.println(max);
	}
	
	static void dfs(int depth) {
		if(depth == N) {
			calculateMax();
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				selected[depth] = arr[i];
				visited[i] = true;
				
				dfs(depth + 1);
				
				visited[i] = false;
			}

		}
	}
	
	static void calculateMax() {
		int temp = 0;
		
		for(int i = 1; i < N; i++) {
			int abs = Math.abs(selected[i - 1] - selected[i]);
			temp += abs;
		}
		
		max = Math.max(max, temp);
	}
}
