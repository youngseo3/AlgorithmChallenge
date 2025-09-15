import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node>{
		int edge, w;
		Node(int edge, int w){
			this.edge = edge;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w-o.w;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //도시의 개수 
		int M = Integer.parseInt(br.readLine()); //버스의 개수
		
		List<Node>[] graph = new ArrayList[N+1];
		for(int i = 0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Node(e, w));
		}
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st2.nextToken());//출발 도시 번호
		int d = Integer.parseInt(st2.nextToken());//도착 도시 번호
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] v = new boolean[N+1];
		
		pq.offer(new Node(s, 0));
		dist[s] = 0;
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(v[now.edge]) continue;
			v[now.edge] = true;
			if(now.edge == d) break;
			for(Node newNode: graph[now.edge]) {
				int newCost = dist[now.edge] + newNode.w;
				if(newCost<dist[newNode.edge]) {
					dist[newNode.edge] = newCost;
					pq.offer(new Node(newNode.edge, dist[newNode.edge]));
				}
			}
		}
		System.out.println(dist[d]);
	}

}
