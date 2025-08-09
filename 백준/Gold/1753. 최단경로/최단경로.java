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
		List<List<Node>> graph = new ArrayList<>();
		
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, w));
		}
		
		// 가중치 저장 배열
		int[] weights = new int[V + 1];
		Arrays.fill(weights, Integer.MAX_VALUE);
		
		// 우선순위 큐 (가중치 기준 오름차순) - Comparable 사용하므로 Comparator 불필요
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 시작 정점 초기화
		weights[K] = 0;
		pq.offer(new Node(K, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			int currentVertex = current.to;
			int currentWeight = current.weight;
			
			// 이미 처리된 정점이거나 더 짧은 경로가 있다면 스킵
			if(currentWeight > weights[currentVertex]) {
				continue;
			}
			
			// 현재 정점에 인접한 정점들 확인
			for(Node next : graph.get(currentVertex)) {
				int nextVertex = next.to;
				int nextWeight = currentWeight + next.weight;
				
				// 더 짧은 경로를 발견했다면 업데이트
				if(nextWeight < weights[nextVertex]) {
					weights[nextVertex] = nextWeight;
					pq.offer(new Node(nextVertex, nextWeight));
				}
			}
		}
		
		// 결과 출력
		for(int i = 1; i <= V; i++) {
			if(weights[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(weights[i]);
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
        
        @Override
        public int compareTo(Node other) {
            return this.weight - other.weight;
        }
	}
}