import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		parents = new int[n + 1];
		
		for(int i = 0; i <= n; i++) {
			parents[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int instruction = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(instruction == 0) {
				union(a, b);
			} else {
				if(find(a) == find(b)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}

	private static int find(int a) {
		if(a != parents[a]) {
			parents[a] = find(parents[a]);
		}
		return parents[a];
	}

	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parents[rootB] = rootA;
	}
}
