import java.io.*;
import java.util.*;

public class Main {

    static int N, M, T, D;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c >= 'a' && c <= 'z') {
                    map[i][j] = c - 'a' + 26;
                } else {
                    map[i][j] = c - 'A';
                }
            }
        }

        // 1. (0,0)에서 다른 모든 지점으로 가는 최소 시간 계산 (올라가는 길 기준)
        int[][] timeTo = dijkstra(true);

        // 2. 다른 모든 지점에서 (0,0)으로 돌아오는 최소 시간 계산 (내려오는 길 기준)
        int[][] timeFrom = dijkstra(false);

        int maxAltitude = 0;

        // 3. 모든 지점을 순회하며 왕복 시간이 D 이하인 곳을 찾는다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 해당 지점까지 갈 수 있는지 확인 (timeTo[i][j]가 MAX_VALUE가 아니어야 함)
                if (timeTo[i][j] == Integer.MAX_VALUE || timeFrom[i][j] == Integer.MAX_VALUE) continue;
                
                long totalTime = (long)timeTo[i][j] + timeFrom[i][j];

                if (totalTime <= D) {
                    // 왕복 시간이 D 이하면, 해당 지점의 높이를 최대 높이와 비교하여 갱신
                    maxAltitude = Math.max(maxAltitude, map[i][j]);
                }
            }
        }

        System.out.println(maxAltitude);
    }

    // 다익스트라 알고리즘을 수행하는 함수
    // isAscending 파라미터로 올라가는 길인지, 내려오는 길인지 구분
    static int[][] dijkstra(boolean isAscending) {
        int[][] times = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(times[i], Integer.MAX_VALUE);
        }

        // 우선순위 큐는 시간을 기준으로 오름차순 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>();

        times[0][0] = 0;
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int x = current.x;
            int y = current.y;
            int time = current.time;

            if (time > times[x][y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                int heightDiff = Math.abs(map[x][y] - map[nx][ny]);
                if (heightDiff > T) continue;

                int nextTime;
                
                // isAscending 값에 따라 시간 계산 로직을 다르게 적용
                if (isAscending) { // 올라가는 길
                    if (map[x][y] >= map[nx][ny]) {
                        nextTime = 1;
                    } else {
                        nextTime = heightDiff * heightDiff;
                    }
                } else { // 내려오는 길
                    if (map[x][y] <= map[nx][ny]) {
                        nextTime = 1;
                    } else {
                        nextTime = heightDiff * heightDiff;
                    }
                }
                
                if (times[x][y] + nextTime < times[nx][ny]) {
                    times[nx][ny] = times[x][y] + nextTime;
                    pq.offer(new Node(nx, ny, times[nx][ny]));
                }
            }
        }
        return times;
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            // 시간을 기준으로 오름차순 정렬
            return Integer.compare(this.time, o.time);
        }
    }
}