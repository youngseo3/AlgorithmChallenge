import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<City>> list = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new City(b, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.offer(new City(start, 0));

        costs[start] = 0;

        while (!pq.isEmpty()) {
            City current = pq.poll();
            int v = current.v;
            int cost = current.cost;

            if (cost > costs[v]) continue;

            for (City next: list.get(v)) {
                if (costs[v] + next.cost < costs[next.v]) {
                    costs[next.v] = costs[v] + next.cost;
                    pq.offer(new City(next.v, costs[next.v]));
                }
            }
        }

        System.out.println(costs[end]);
    }

    static class City implements Comparable<City> {
        int v;
        int cost;

        public City(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
