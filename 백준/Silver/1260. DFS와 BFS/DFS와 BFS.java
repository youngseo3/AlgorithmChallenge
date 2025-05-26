import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int V;
	static List<Integer>[] list;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		for(int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		for(int i = 1; i < N + 1; i++) {
			Collections.sort(list[i]);
		}
		
		// DFS 실행
        check = new boolean[N + 1];
        dfs(V);
        sb.append("\n");
        
        // BFS 실행
        check = new boolean[N + 1];
        bfs(V);
        
        System.out.println(sb);
	}
	
	static void dfs(int current) {
        check[current] = true;
        sb.append(current + " ");
        
        for(int next : list[current]) {
            if(!check[next]) {
                dfs(next);
            }
        }
    }
	
	static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        check[start] = true;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            sb.append(current + " ");
            
            for(int next : list[current]) {
                if(!check[next]) {
                    check[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

}
