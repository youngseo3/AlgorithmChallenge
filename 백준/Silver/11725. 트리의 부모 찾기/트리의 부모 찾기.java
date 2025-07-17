import java.io.*;
import java.util.*;
	
public class Main {

	static List<Integer>[] list;
	static int[] parent;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		parent = new int[N + 1];
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		bfs();
		
		for(int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		visited[1] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: list[current]) {
				if(!visited[next]) {
					visited[next] = true;
					q.add(next);
					parent[next] = current;
				}
			}
		}
	}

}
