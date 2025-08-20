import java.io.*;
import java.util.*;
	
public class Solution {

	static int L, start, maxLevel;
	static boolean[] visited;
	static int[] level;
	static List<List<Integer>> integers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for(int tc = 1; tc <= 10; tc++) {
			integers = new ArrayList<>();
			for(int i = 0; i <= 100; i++) {
				integers.add(new ArrayList<>());
			}
			
			visited = new boolean[101];
			level = new int[101];
			maxLevel = 0;
			
			st = new StringTokenizer(br.readLine());
			
			int L = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < L / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				integers.get(from).add(to);
			}
			
			bfs(start);
			int answer = 0;
			for(int i = 0; i < level.length; i++) {
				if(maxLevel == level[i]) {
					answer = Math.max(answer, i);
				}
			}
			
			sb.append("#" + tc + " ").append(answer + "\n");
		}
		System.out.println(sb);
		
	}

	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		visited[start] = true;
		level[start] = 0;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			 
			for(int next: integers.get(current)) {
				if(!visited[next]) {
					level[next] = level[current] + 1;
					visited[next] = true;
					q.offer(next);
					
					maxLevel = level[next];
				}
			}
		}
	}
}
