import java.io.*;
import java.util.*;

public class Main {

    static int N, E;
    static List<List<Node>> nodes = new ArrayList<>();
    static final int INF = 200000000; // 충분히 큰 값 (800 * 1000 * 200 + 여유분)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes.get(a).add(new Node(b, c));
            nodes.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1에서 모든 노드까지의 최단거리
        int[] distancesFrom1 = dijkstra(1);
        // v1에서 모든 노드까지의 최단거리  
        int[] distancesFromV1 = dijkstra(v1);
        // v2에서 모든 노드까지의 최단거리
        int[] distancesFromV2 = dijkstra(v2);

        // v1과 v2 사이의 거리
        int distFromV1ToV2 = distancesFromV1[v2];

        // 경로 1: 1 -> v1 -> v2 -> N
        long path1 = (long)distancesFrom1[v1] + distFromV1ToV2 + distancesFromV2[N];
        
        // 경로 2: 1 -> v2 -> v1 -> N  
        long path2 = (long)distancesFrom1[v2] + distFromV1ToV2 + distancesFromV1[N];

        long result = Math.min(path1, path2);
        
        // 경로가 존재하지 않는 경우
        if(result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static int[] dijkstra(int start) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);

        distances[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0)); // start로 수정!

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int v = current.v;
            int dist = current.dist;

            // 이미 처리된 노드라면 스킵
            if(dist > distances[v]) continue;

            for(Node next: nodes.get(v)) {
                int newDist = distances[v] + next.dist;
                if(newDist < distances[next.v]) {
                    distances[next.v] = newDist;
                    pq.offer(new Node(next.v, newDist)); // 우선순위 큐에 추가
                }
            }
        }

        return distances;
    }

    static class Node implements Comparable<Node> {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
}