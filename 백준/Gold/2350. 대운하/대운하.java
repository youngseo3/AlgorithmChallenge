import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] parents;
    // MST의 정보를 저장할 인접 리스트. 쿼리마다 재사용됩니다.
    static List<List<int[]>> mstGraph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1. 모든 간선 정보를 입력받아 내림차순으로 정렬 (한 번만 수행)
        List<Edge> allEdges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            allEdges.add(new Edge(a, b, c));
        }
        Collections.sort(allEdges);

        // 2. MST 구축 (한 번만 수행)
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
            mstGraph.add(new ArrayList<>());
        }

        int cnt = 0;
        for (Edge next : allEdges) {
            if (union(next.u, next.v)) {
                // MST의 간선 정보를 양방향으로 인접 리스트에 저장
                mstGraph.get(next.u).add(new int[]{next.v, next.w});
                mstGraph.get(next.v).add(new int[]{next.u, next.w});
                cnt++;
            }
            if (cnt == N - 1) break;
        }

        // 3. K개의 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < K; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 미리 만들어둔 MST 위에서 BFS로 경로의 최소 너비를 찾음
            sb.append(findMaxBoatWidth(start, end)).append("\n");
        }
        System.out.print(sb);
    }

    /**
     * BFS를 사용하여 start에서 end까지의 경로상 최소 너비(병목)를 찾습니다.
     * @param start 출발 도시
     * @param end 도착 도시
     * @return 최대 운행 가능 보트 너비
     */
    private static int findMaxBoatWidth(int start, int end) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        // Queue에는 {현재 도시, 현재까지 경로의 최소 너비} 를 저장
        queue.offer(new int[]{start, Integer.MAX_VALUE});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currCity = current[0];
            int minWidthSoFar = current[1];

            // 목적지에 도착했다면, 지금까지의 경로상 최소 너비가 정답
            if (currCity == end) {
                return minWidthSoFar;
            }

            // 현재 도시와 연결된 다른 도시들을 탐색 (MST 기준)
            for (int[] edge : mstGraph.get(currCity)) {
                int nextCity = edge[0];
                int canalWidth = edge[1];

                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    // 다음 경로의 최소 너비는 (지금까지의 최소 너비)와 (새 운하의 너비) 중 더 작은 값
                    int newMinWidth = Math.min(minWidthSoFar, canalWidth);
                    queue.offer(new int[]{nextCity, newMinWidth});
                }
            }
        }
        return 0; // 경로가 없는 경우 (문제 조건상 발생하지 않음)
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parents[rootB] = rootA;
        return true;
    }

    private static int find(int a) {
        if (a != parents[a]) {
            parents[a] = find(parents[a]);
        }
        return parents[a];
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u; this.v = v; this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(o.w, this.w); // 내림차순 정렬 (Maximum Spanning Tree)
        }
    }
}