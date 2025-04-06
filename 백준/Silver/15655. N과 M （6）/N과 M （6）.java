import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr;
	static int[] selected;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
	
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new int[N];
        selected = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        
        dfs(0, 0);
        
        System.out.println(sb);
	}
	
	static void dfs(int at, int depth) {
		if(depth == M) {
			for(int val: selected) {
				sb.append(val).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		for(int i = at; i < N; i++) {
			selected[depth] = arr[i];
			dfs(i + 1, depth + 1);
		}
	}

}
