import java.io.*;
import java.util.*;

public class Main {

	static List<List<Node>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			// n, m, t는 각각 교차로, 도로, 목적지 후보의 개수
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			// s, g, h는 각각 출발지, g와 h사이에 교차로를 지나야함 
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			for(int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(new Node(b, c));
				graph.get(b).add(new Node(a, c));
			}
			
			int[] target = new int[t];
			for(int i = 0; i < t; i++) {
				target[i] = Integer.parseInt(br.readLine());
			}
			
			int[] distFromStart = new int[n + 1];
			Arrays.fill(distFromStart, Integer.MAX_VALUE);
			distFromStart[s] = 0;
			distFromStart = dijkstra(distFromStart, s);
			
			int[] distFromG = new int[n + 1];
			Arrays.fill(distFromG, Integer.MAX_VALUE);
			distFromG[g] = 0;
			distFromG = dijkstra(distFromG, g);
			
			int[] distFromH = new int[n + 1];
			Arrays.fill(distFromH, Integer.MAX_VALUE);
			distFromH[h] = 0;
			distFromH = dijkstra(distFromH, h);
			
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < t; i++) {
				int end = target[i];
				
				// g-h 도로를 지나는 두 가지 경로
			    // 1) s → g → h → end
			    // 2) s → h → g → end
			    int pathViaGH = distFromStart[g] + distFromG[h] + distFromH[end];
			    int pathViaHG = distFromStart[h] + distFromH[g] + distFromG[end];
			    
			    // 둘 중 하나라도 최단거리와 같으면 가능
			    if (distFromStart[end] == pathViaGH || distFromStart[end] == pathViaHG) {
			        list.add(end);
			    }
			}
			
			Collections.sort(list);
			int pre = 0;
			for(int ans: list) {
				if(pre != ans) {
					System.out.print(ans + " ");
				}
				
				pre = ans;
			}
			System.out.println();
		}
		
	}

	private static int[] dijkstra(int[] dist, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();

			if(current.d > dist[current.v]) continue;
			
			for(Node next: graph.get(current.v)) {
				if(dist[current.v] + next.d < dist[next.v]) {
					dist[next.v] = dist[current.v] + next.d;
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		return dist;
	}

	static class Node implements Comparable<Node> {
		int v;
		int d;
		
		public Node(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.d, o.d);
		}
		
	}
}
