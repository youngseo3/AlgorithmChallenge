import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int k;
	static int[] arr;
	static int[] selected;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            
            if(k == 0) {
            	break;
            }
            
            arr = new int[k];
            visited = new boolean[6];
            selected = new int[6];
            
            for(int j = 0; j < k; j++) {
            	arr[j] = Integer.parseInt(st.nextToken());
            }
            
            dfs(0, 0);
            sb.append("\n");
        }
        
        System.out.println(sb);
	}
	
	static void dfs(int at, int depth) {
		if(depth == 6) {
			for (int i = 0; i < depth; i++) {
				sb.append(selected[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = at; i < k; i++) {
			if(!visited[depth]) {
				visited[depth] = true;
				selected[depth] = arr[i];
				
				dfs(i + 1, depth + 1);
				
				visited[depth] = false;
			}
		}
	}

}
