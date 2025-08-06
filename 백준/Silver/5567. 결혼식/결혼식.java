import java.io.*;
import java.util.*;

public class Main {

	static int n, m, invite;
	static List<Integer>[] friends;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		friends = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		
		for(int i = 1; i <= n; i++) {
			friends[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			friends[a].add(b);
			friends[b].add(a);
		}
		
		bfs();
		
		System.out.println(invite);
	}

	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		visited[1] = true;

		for(int nextFriend: friends[1]) {
			visited[nextFriend] = true;
			q.add(nextFriend);
			invite++;
		}
		
		if(invite == 0) return;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int nextFriend: friends[current]) {
				if(!visited[nextFriend]) {
					visited[nextFriend] = true;
					invite++;
				}
				
			}
			
		}
	}

}
