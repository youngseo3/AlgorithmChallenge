import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int cnt = 0;
	static List<Integer>[] list;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		check = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		bfs(1);
		
		System.out.println(cnt);
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		check[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next : list[current]) {
				if(!check[next]) {
					check[next] = true;
					queue.offer(next);
				}
			}
		}
		
		cnt++;
		
		for(int i = 1; i <= N; i++) {
			if(!check[i]) {	
				bfs(i);
			}
		}
	}

}
