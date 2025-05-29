import java.io.*;
import java.util.*;

public class Main {
		
	static int V, E;
	static List<Integer>[] list;
	static int[] color;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= K; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[V + 1];
			for(int i = 1; i <= V; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				list[b].add(a);
			}
			color = new int[V + 1];
			bfs();
		}
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i = 1; i < V + 1; i++) {
			if(color[i] == 0) {
				color[i] = 1;
				queue.add(i);
			}
			
			while(!queue.isEmpty()) {
				int pos = queue.poll();
				
				for(int next: list[pos]) {
					if(color[next] == color[pos]) {
						System.out.println("NO");
						return;
					}
					
					if(color[next] == 0) {
						queue.add(next);
						
						if(color[pos] == 1) {
							color[next] = 2;
						} else {
							color[next] = 1;
						}
					}
				}
			}
		}
        
        System.out.println("YES");

	}

}
