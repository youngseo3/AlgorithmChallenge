import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	// 거리가 매우 커질 수 있으므로 long 타입으로 변경하는 것이 안전합니다.
	static long[] distances;
	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 그래프 및 거리 배열 초기화
		distances = new long[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			distances[i] = Long.MAX_VALUE;
		}

		// 간선 정보를 "뒤집어서" 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(v).add(new Node(u, c));
		}

		st = new StringTokenizer(br.readLine());
		PriorityQueue<Node> pq = new PriorityQueue<>();

		// 모든 면접장을 시작점으로 설정
		for (int i = 0; i < K; i++) {
			int interviewCity = Integer.parseInt(st.nextToken());
			distances[interviewCity] = 0;
			pq.offer(new Node(interviewCity, 0));
		}

		// 다익스트라 알고리즘 실행 (한 번만!)
		dijkstra(pq);

		// 가장 먼 도시 찾기
		int resultCity = 0;
		long maxDist = 0;

		for (int i = 1; i <= N; i++) {
			if (distances[i] > maxDist) {
				maxDist = distances[i];
				resultCity = i;
			}
		}

		System.out.println(resultCity);
		System.out.println(maxDist);
	}

	// 모든 면접장에서 동시에 출발하는 다익스트라
	private static void dijkstra(PriorityQueue<Node> pq) {
		while (!pq.isEmpty()) {
			Node current = pq.poll();

			// long 타입으로 변경
			if (current.dist > distances[current.v]) {
				continue;
			}

			for (Node next : graph.get(current.v)) {
				if (distances[current.v] + next.dist < distances[next.v]) {
					distances[next.v] = distances[current.v] + next.dist;
					pq.offer(new Node(next.v, distances[next.v]));
				}
			}
		}
	}

	// Node 클래스의 dist를 long으로 변경
	static class Node implements Comparable<Node> {
		int v;
		long dist;

		public Node(int v, long dist) {
			this.v = v;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.dist, o.dist);
		}
	}
}