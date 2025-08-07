import java.io.*;
import java.util.*;

public class Main {

	static int N, M, max;
	static int[] cnt;
	static boolean[] visited;
	static List<Integer>[] computer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cnt = new int[N + 1];
		computer = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			computer[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			computer[b].add(a);
		}
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			bfs(i);
		}
		
		for(int i = 1; i <= N; i++) {
			if(cnt[i] == max) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			int B = q.poll();
			
			for(int A: computer[B]) {
				if(!visited[A]) {
					visited[A] = true;
					q.add(A);
					cnt[start]++;
				}
			}
		}
		
		max = Math.max(max, cnt[start]);
	}

}
