import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static List<Point> virusLoc = new ArrayList<>();
    static Point[] selected;
    static int min = Integer.MAX_VALUE;
    static int emptySpaces = 0; // 빈 공간의 개수 체크용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        selected = new Point[M]; // 크기는 M

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) virusLoc.add(new Point(i, j));
                if (arr[i][j] != 1) emptySpaces++; // 벽이 아닌 모든 칸의 수
            }
        }

        solve(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void solve(int depth, int start) {
        if (depth == M) {
            bfs();
            return;
        }
        for (int i = start; i < virusLoc.size(); i++) {
            selected[depth] = virusLoc.get(i);
            solve(depth + 1, i + 1);
        }
    }

    private static void bfs() {
        Queue<Point> q = new ArrayDeque<>();
        int[][] times = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(times[i], -1);

        int count = 0; // 바이러스가 퍼진 칸 수
        int maxTime = 0;

        for (Point p : selected) {
            q.add(p);
            times[p.x][p.y] = 0;
            count++;
        }

        while (!q.isEmpty()) {
            Point p = q.poll();
            maxTime = Math.max(maxTime, times[p.x][p.y]);

            for (int j = 0; j < 4; j++) {
                int nx = p.x + dx[j];
                int ny = p.y + dy[j];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (times[nx][ny] == -1 && arr[nx][ny] != 1) {
                        times[nx][ny] = times[p.x][p.y] + 1;
                        q.add(new Point(nx, ny));
                        count++;
                    }
                }
            }
        }

        // 모든 빈 공간(emptySpaces)에 바이러스가 퍼졌는지 확인
        if (count == emptySpaces) {
            min = Math.min(min, maxTime);
        }
    }

    static class Point {
        int x, y;
        public Point(int x, int y) { this.x = x; this.y = y; }
    }
}
