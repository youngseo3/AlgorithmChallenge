import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<List<Vertex>> graph = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 양방향 간선
            graph.get(a).add(new Vertex(b, c));
            graph.get(b).add(new Vertex(a, c));
        }
        
        // 프림 알고리즘 시작
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        
        // 임의의 시작점 (1번) 0의 비용으로 시작
        pq.offer(new Vertex(1, 0));
        
        int totalCost = 0; // MST 전체 비용
        int maxCost = 0;   // MST에 포함된 간선 중 가장 큰 비용
        
        while(!pq.isEmpty()) {
            Vertex current = pq.poll();
            
            // 이미 방문한 정점이면 패스 (이 부분이 중요합니다)
            if(visited[current.n]) continue;
            
            visited[current.n] = true;
            totalCost += current.w;
            
            // 현재까지 연결된 간선 중 가장 비용이 큰 것을 갱신
            maxCost = Math.max(maxCost, current.w);
            
            for(Vertex next : graph.get(current.n)) {
                if(!visited[next.n]) {
                    // 다음 정점을 큐에 추가 (dist 배열을 굳이 안 써도 PQ 특성상 가장 짧은 간선이 먼저 나옵니다)
                    pq.offer(new Vertex(next.n, next.w));
                }
            }
        }
        
        // 전체 MST 비용에서 가장 비싼 간선 하나를 빼면 두 개의 마을로 분할됨
        System.out.println(totalCost - maxCost);
    }

    static class Vertex implements Comparable<Vertex> {
        int n;
        int w;
        
        public Vertex(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.w, o.w);
        }
    }
}