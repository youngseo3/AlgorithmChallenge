import java.io.*;
import java.util.*;

public class Main {

	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parents = new int[n];
		for(int i = 0; i < n; i++) {
			parents[i] = i;
		}
		
		int cnt = 0;
		boolean isCycle = false;

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
						
			if(union(a, b)) {
				cnt++;
			}
			else {
				cnt++;
				isCycle = true;
				break;
			}

		}
		
		if(isCycle) {
			System.out.println(cnt);
		}
		else {
			System.out.println(0);
		}
	}

	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) return false;
		
		parents[rootB] = rootA;
		return true;
	}

	private static int find(int a) {
		if(a != parents[a]) {
			parents[a] = find(parents[a]);
		}
		
		return parents[a];
	}
}
