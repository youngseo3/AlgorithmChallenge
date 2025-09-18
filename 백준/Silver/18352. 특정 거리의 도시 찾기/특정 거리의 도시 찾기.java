import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int city, distance;
        
        public Node(int city, int distance) {
            this.city = city;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 도시의 개수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수
        int K = Integer.parseInt(st.nextToken()); // 찾는 거리
        int X = Integer.parseInt(st.nextToken()); // 출발 도시
        
        // 인접 리스트로 그래프 구성
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 간선 정보 입력 (단방향)
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B); // A에서 B로 가는 단방향 도로
        }
        
        // 다익스트라 알고리즘 실행
        int[] distance = dijkstra(graph, N, X);
        
        // 거리 K인 도시들 찾기
        List<Integer> result = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(distance[i] == K) {
                result.add(i);
            }
        }
        
        // 결과 출력
        if(result.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(result); // 오름차순 정렬
            for(int city : result) {
                System.out.println(city);
            }
        }
    }
    
    /**
     * 다익스트라 알고리즘으로 최단 거리 계산
     * @param graph 인접 리스트
     * @param N 도시 개수
     * @param start 시작 도시
     * @return 각 도시까지의 최단 거리 배열
     */
    static int[] dijkstra(List<List<Integer>> graph, int N, int start) {
        final int INF = Integer.MAX_VALUE;
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        
        // 우선순위 큐 (최소 힙)
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 시작점 초기화
        distance[start] = 0;
        pq.offer(new Node(start, 0));
        
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int currentCity = current.city;
            int currentDist = current.distance;
            
            // 이미 처리된 노드라면 무시
            if(currentDist > distance[currentCity]) {
                continue;
            }
            
            // 인접한 모든 도시 탐색
            for(int nextCity : graph.get(currentCity)) {
                int newDist = currentDist + 1; // 모든 도로의 거리는 1
                
                // 더 짧은 거리를 찾았다면 갱신
                if(newDist < distance[nextCity]) {
                    distance[nextCity] = newDist;
                    pq.offer(new Node(nextCity, newDist));
                }
            }
        }
        
        return distance;
    }
}