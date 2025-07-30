import java.io.*;
import java.util.*;
	
public class Main {

	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		char[] coins = br.readLine().toCharArray();
		
		// T의 개수 구하기
		int T = 0;
		for(char c: coins) {
			if(c == 'T') {
				T++;
			}
		}
		
		if(N == T) {
			System.out.println(0);
		} else {
			System.out.println(bfs(T));
		}
	}

	static int bfs(int T) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {T, 0});
		boolean[] visited = new boolean[N + 1]; 
		visited[T] = true;
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int t = p[0];  // T의 개수
			int depth = p[1]; // 몇 번 뒤집는지 
			int h = N - t; // H의 개수
			
			if(t == N) return depth;
			
			for(int i = 0; i <= K; i++) {
				int a = i; // 뒷면에서 앞면으로 뒤집을 개수
				int b = K - i;// 앞면에서 뒷면으로 뒤집을 개수
				
				if(a > t || b > h) continue;
				
				if(visited[t - a + b]) continue;
				
				visited[t - a + b] = true;
				q.offer(new int[] {t - a + b, depth + 1});
			}
		}
		
		return -1;
	}
}
