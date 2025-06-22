import java.util.*;
import java.io.*;

public class Main {

	static int cnt;
	static boolean[] virus;
	static List<Integer>[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		int network = Integer.parseInt(br.readLine());
		
		virus = new boolean[C + 1];
		list = new ArrayList[C + 1];
		for(int i = 1; i <= C; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= network; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
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
		queue.add(start);
		virus[start] = true;
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for(int next: list[current]) {
				if(!virus[next]) {
					queue.add(next);
					virus[next] = true;
					
					cnt++;
				}
			}
		}
	}

}
