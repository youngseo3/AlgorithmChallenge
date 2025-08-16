import java.io.*;
import java.util.*;

public class Main {

    static int a, b, c, N;
    static boolean[] leafNode;
    static List<List<Integer>> edges = new ArrayList<>();
    static int[] distFromB, distFromC;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }
        
        int[] count = new int[N + 1];
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            count[u]++;
            count[v]++;
            
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        leafNode = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            if(count[i] == 1) {
                leafNode[i] = true;
            }
        }
        
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        // 도둑들로부터 모든 노드까지의 거리를 미리 계산
        distFromB = getDistances(b);
        distFromC = getDistances(c);
        
        // 윤이가 이미 리프 노드에 있다면 바로 탈출 가능
        if(leafNode[a]) {
            System.out.println("YES");
            return;
        }
        
        // DFS로 탈출 가능한지 확인
        boolean[] visited = new boolean[N + 1];
        visited[a] = true;
        
        boolean canEscape = dfs(a, visited, 0);
        System.out.println(canEscape ? "YES" : "NO");
    }

    // DFS로 윤이가 리프노드에 도달할 수 있는지 확인
    private static boolean dfs(int yunPos, boolean[] visited, int time) {
        // 리프 노드에 도착했다면 탈출 성공
        if(leafNode[yunPos]) {
            return true;
        }
        
        // 윤이가 갈 수 있는 다음 노드들을 탐색
        for(int next : edges.get(yunPos)) {
            if(!visited[next]) {
                // 도둑들이 윤이보다 먼저 또는 동시에 도착할 수 있다면 이 경로는 불가능
                if(distFromB[next] <= time + 1 || distFromC[next] <= time + 1) {
                    continue;
                }
                
                visited[next] = true;
                if(dfs(next, visited, time + 1)) {
                    return true;
                }
                visited[next] = false;
            }
        }
        
        return false;
    }
    
    // BFS를 이용해 한 노드로부터 모든 노드까지의 거리를 구하는 함수
    private static int[] getDistances(int start) {
        int[] distances = new int[N + 1];
        Arrays.fill(distances, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        distances[start] = 0;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            
            for(int next : edges.get(current)) {
                if(distances[next] == -1) {
                    distances[next] = distances[current] + 1;
                    queue.offer(next);
                }
            }
        }
        
        return distances;
    }
}