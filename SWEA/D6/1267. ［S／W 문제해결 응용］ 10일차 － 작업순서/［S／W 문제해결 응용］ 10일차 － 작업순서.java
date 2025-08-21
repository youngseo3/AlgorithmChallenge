import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			int[] inDegree = new int[V + 1];
			boolean[] visited = new boolean[V + 1];
			Queue<Integer> q = new ArrayDeque<>();
			
			List<List<Integer>> edges = new ArrayList<>();
			for(int i = 0; i <= V; i++) {
				edges.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				edges.get(a).add(b);
				inDegree[b]++;
			}
			
//			System.out.println(Arrays.toString(inDegree));
			
			for(int i = 1; i <= V; i++) {
				if(inDegree[i] == 0) {
					visited[i] = true;
					q.offer(i);
				}
			}
			
			Queue<Integer> T = new ArrayDeque<>();
			
			while(!q.isEmpty()) {
				int current = q.poll();
				T.offer(current);
				
				for(int next: edges.get(current)) {
					if(!visited[next]) {
						inDegree[next]--;
					}
					
					if(inDegree[next] == 0) {
						visited[next] = true;
						q.offer(next);
					}
				}
			}
			
			sb.append("#" + tc + " ");
			while(!T.isEmpty()) {
				sb.append(T.poll() + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
