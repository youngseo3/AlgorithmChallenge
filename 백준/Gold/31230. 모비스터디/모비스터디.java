import java.io.*;
import java.util.*;

public class Main {
    static List<List<City>> cities = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i <= N; i++) {
            cities.add(new ArrayList<>());
        }
        
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());  // long으로 변경!
            cities.get(a).add(new City(b, c));
            cities.get(b).add(new City(a, c));
        }
        
        long[] timeFromA = dijkstra(N, A);
        long[] timeFromB = dijkstra(N, B);
        long timeFromAToB = timeFromA[B];
        
        List<Integer> studyMembers = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            // 정확히 같아야 함 (<=가 아니라 ==)
            if (timeFromA[i] + timeFromB[i] == timeFromAToB) {
                studyMembers.add(i);
            }
        }
        
        System.out.println(studyMembers.size());
        StringBuilder sb = new StringBuilder();
        for (int member : studyMembers) {
            sb.append(member).append(" ");
        }
        System.out.println(sb);
    }
    
    static long[] dijkstra(int N, int A) {  // long 배열 반환
        long[] times = new long[N + 1];
        Arrays.fill(times, Long.MAX_VALUE);
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(A, 0));
        times[A] = 0;
        
        while(!pq.isEmpty()) {
            City current = pq.poll();
            if(current.time > times[current.num]) continue;
            
            for(City next: cities.get(current.num)) {
                if(times[current.num] + next.time < times[next.num]) {
                    times[next.num] = times[current.num] + next.time;
                    pq.offer(new City(next.num, times[next.num]));
                } 
            }
        }
        return times;
    }
    
    static class City implements Comparable<City> {
        int num;
        long time;  // long으로 변경!
        
        public City(int num, long time) {
            this.num = num;
            this.time = time;
        }
        
        @Override
        public int compareTo(City o) {
            return Long.compare(this.time, o.time);  // Long.compare 사용
        }
    }
}