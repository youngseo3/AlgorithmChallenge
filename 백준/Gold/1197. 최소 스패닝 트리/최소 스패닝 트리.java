import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		// 그래프 구현
		List<List<Dest>> integers = new ArrayList<>();
		
		for(int i = 0; i <= V; i++) {
			integers.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			integers.get(a).add(new Dest(b, weight));
			integers.get(b).add(new Dest(a, weight));
		}
		
		// 최소 거리 배열
		int[] distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		// 기준 정점 방문 여부
		boolean[] visited = new boolean[V + 1];
		
		distance[1] = 0;
	
		for(int cnt = 1; cnt <= V; cnt++) {
			// 기준 정점
			int minIdx = -1;
			int minD = Integer.MAX_VALUE;
			
			// 기준 정점이 아닌 것들 중에서 기준 정점을 잡고자 최소 거리를 가진 정점을 선택
			for(int i = 1; i <= V; i++) {
				if(!visited[i] && distance[i] < minD) {
					minIdx = i;
					minD = distance[i];
				}
			}
			
			visited[minIdx] = true;
			
			for(Dest d: integers.get(minIdx)) {
				int to = d.to;
				int dist = d.dist;
				
				if(!visited[to] && dist < distance[to]) {
					distance[to] = dist;
				}
			}
		}
		
		int sum = 0;
		for(int i = 1; i <= V; i++) {
			sum += distance[i];
		}
		System.out.println(sum);
	}

	static class Dest {
		int to;
		int dist;
		
		Dest(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}
	}
}
