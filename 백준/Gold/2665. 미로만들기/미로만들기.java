import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int[][] changeRoom; // 각 방까지의 최소 벽 파괴 횟수 (거리 테이블)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        changeRoom = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            // changeRoom 배열을 충분히 큰 값으로 초기화
            Arrays.fill(changeRoom[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        dijkstra();

        // 최종 결과 출력
        System.out.println(changeRoom[n - 1][n - 1]);
    }

    private static void dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        // 시작점 설정
        changeRoom[0][0] = 0;
        pq.offer(new Point(0, 0, 0)); // (0, 0)까지 0개의 벽을 부숨

        while (!pq.isEmpty()) {
            Point current = pq.poll();
            int x = current.x;
            int y = current.y;
            int wallsBroken = current.wallsBroken;

            // 큐에서 꺼낸 경로가 이미 알려진 최단 경로보다 비효율적이면 무시
            if (wallsBroken > changeRoom[x][y]) {
                continue;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 맵 범위를 벗어나는 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                // 다음 방으로 가기 위해 부숴야 하는 벽의 총 개수 계산
                int newWallsBroken = wallsBroken;
                if (map[nx][ny] == 0) { // 다음 방이 검은 방(벽)인 경우
                    newWallsBroken++;
                }

                // 새로 발견한 경로가 기존 경로보다 더 효율적일 때만 갱신
                if (newWallsBroken < changeRoom[nx][ny]) {
                    changeRoom[nx][ny] = newWallsBroken;
                    pq.offer(new Point(nx, ny, newWallsBroken));
                }
            }
        }
    }

    // Point 클래스의 변수명을 좀 더 명확하게 변경 (blackToWhite -> wallsBroken)
    static class Point implements Comparable<Point> {
        int x;
        int y;
        int wallsBroken; // 부순 벽의 개수

        public Point(int x, int y, int wallsBroken) {
            this.x = x;
            this.y = y;
            this.wallsBroken = wallsBroken;
        }

        // 부순 벽의 개수가 적은 것을 우선으로 함
        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.wallsBroken, o.wallsBroken);
        }
    }
}