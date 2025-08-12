import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<List<City>> cities = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            cities.add(new ArrayList<>());
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            cities.get(u).add(new City(v, c));
        }
        
        // K개의 최단거리를 저장할 우선순위큐 배열 (최대힙)
        PriorityQueue<Integer>[] distQueue = new PriorityQueue[N + 1];
        for(int i = 0; i <= N; i++) {
            distQueue[i] = new PriorityQueue<>(Collections.reverseOrder());
        }
        
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(1, 0)); // 시작점은 1번 도시
        distQueue[1].offer(0);
        
        while(!pq.isEmpty()) {
            City current = pq.poll();
            
            // 현재 거리가 이미 저장된 K번째보다 크면 무시
            if(!distQueue[current.num].isEmpty() && 
               distQueue[current.num].size() == K && 
               current.cost > distQueue[current.num].peek()) {
                continue;
            }
            
            for(City next : cities.get(current.num)) {
                int newCost = current.cost + next.cost;
                
                // K개 미만이면 무조건 추가
                if(distQueue[next.num].size() < K) {
                    distQueue[next.num].offer(newCost);
                    pq.offer(new City(next.num, newCost));
                }
                // K개이지만 현재 최대값보다 작으면 교체
                else if(newCost < distQueue[next.num].peek()) {
                    distQueue[next.num].poll(); // 최대값 제거
                    distQueue[next.num].offer(newCost);
                    pq.offer(new City(next.num, newCost));
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            if(distQueue[i].size() == K) {
                sb.append(distQueue[i].peek()).append("\n"); // K번째 최단거리
            } else {
                sb.append(-1).append("\n");
            }
        }
        
        System.out.print(sb.toString());
    }
    
    static class City implements Comparable<City> {
        int num;
        int cost;
        
        public City(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(City o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}