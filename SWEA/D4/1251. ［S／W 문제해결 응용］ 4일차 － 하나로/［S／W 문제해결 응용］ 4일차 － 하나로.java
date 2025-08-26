import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static double E;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[] setX = new int[N];
			int[] setY = new int[N];
			Edge[] edges = new Edge[(N * (N - 1)) / 2];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				setX[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				setY[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			parents = new int[N];
			for(int i = 0; i < N; i++) {
				parents[i] = i;
			}
			
			int idx = 0;
			for(int i = 0; i < N - 1; i++) {
				for(int j = i + 1; j < N; j++) {
					int x1 = setX[i];
					int y1 = setY[i];
					
					int x2 = setX[j];
					int y2 = setY[j];
					
					long dist = getDistance(x1, y1, x2, y2);
					
					edges[idx++] = new Edge(i, j, dist); 
				}
			}

			Arrays.sort(edges);

			int cnt = 0;
			long totalWeight = 0;
			for(int i = 0; i < edges.length; i++) {
				Edge edge = edges[i];
				int a = edge.a;
				int b = edge.b;
				long dist = edge.dist;
				
				if(union(a, b)) {
					cnt++;
					totalWeight += dist;
				}
				
				if(cnt == N - 1) {
					break;
				}
			}
			
			sb.append("#" + tc + " ").append(Math.round(totalWeight * E) + "\n");
		}
		
		System.out.println(sb);
	}

	private static long getDistance(int x1, int y1, int x2, int y2) {
		return (long) (Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2));
	}
	
	private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
 
    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
    
	static class Edge implements Comparable<Edge> {
		int a;
		int b;
		long dist;
		
		public Edge(int a, int b, long dist) {
			this.a = a;
			this.b = b;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.dist, o.dist);
		}
	}
}
