import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());
		
		// 그래프 구현
		List<List<Node>> integers = new ArrayList<>();
		
		for(int i = 0; i <= V; i++) {
			integers.add(new ArrayList<>());
		}

		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			integers.get(u).add(new Node(v, w));
		}
		
		// 가중치 저장 배열
		int[] weights = new int[V + 1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		
		// 기준 정점 방문 여부
		boolean[] visited = new boolean[V + 1];
		
		// 시작 정점 초기화 
		weights[K] = 0;
		
		for(int cnt = 1; cnt <= V - 1; cnt++) {
			int minIdx = -1;
			int minW = Integer.MAX_VALUE;
			
			// 기준 정점 찾기
			for(int i = 1; i <= V; i++) {
				if(!visited[i] && weights[i] < minW) {
					minIdx = i;
					minW = weights[i];
				}
			}
			
			if(minIdx == -1) continue;
			
			visited[minIdx] = true;
			
			// 기준 정점에 인접한 정점들과의 가중치를 업데이트
			for(Node n: integers.get(minIdx)) {
				int to = n.to;
				int weight = n.weight;
				
				if(!visited[to] && weights[minIdx] + weight < weights[to]) {
					weights[to] = weights[minIdx] + weight;
				}
			}
		}
		
		for(int i = 1; i < weights.length; i++) {
			if(weights[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(weights[i]);
		}
	}
	
	static class Node {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

}
