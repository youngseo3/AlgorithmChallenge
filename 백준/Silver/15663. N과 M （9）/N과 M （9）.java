import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
	
	static int N, M;
	static int[] arr;
	static int[] selected;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		selected = new int[M];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(0);
		
		System.out.println(sb);
	}
	
	static void dfs(int depth) {
		if(depth == M) {
			for(int val: selected) {
				sb.append(val).append(" ");
			}
			
			sb.append("\n");
			return;
		}
		
		int before = 0;
		for(int i = 0; i < N; i++) {
			if(visited[i] || before == arr[i]) {
				continue;
			}
			
			visited[i] = true;
			selected[depth] = arr[i];
			before = arr[i];
			
			dfs(depth + 1);
			
			visited[i] = false;	
		}
	}
}
