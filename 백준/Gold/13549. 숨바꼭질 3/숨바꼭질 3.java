import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[] time;

	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		time = new int[100001];

		if(N == K) { 
			System.out.println("0");
			return;
		}
		
		bfs();

		System.out.println(time[K] - 1);
	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(N);
		time[N] = 1;
		
		while(!q.isEmpty()) {
			int x = q.poll();

			if(x == K) break;
            
			if(x * 2 < time.length && time[x * 2] == 0) {
				q.add(x * 2);
				time[x * 2] = time[x];
			}
            
			if(x - 1 >= 0 && time[x - 1] == 0) {
				q.add(x - 1);
				time[x - 1] = time[x] + 1;
			}

			if(x + 1 < time.length && time[x + 1] == 0) {
				q.add(x + 1);
				time[x + 1] = time[x] + 1;
			}

		}
	}
}
