import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backTrack(0, 0);

        System.out.println(min);
    }

    private static void backTrack(int depth, int sum) {
        if (depth == 3) {
            min = Math.min(min, sum);
            return;
        }

        if (sum >= min) return;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (isPlanted(i, j)) {

                    int cost = plant(i, j, true);

                    backTrack(depth + 1, sum + cost);

                    plant(i, j, false);
                }
            }
        }
    }

    private static boolean isPlanted(int r, int c) {
        if (visited[r][c]) return false;

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (visited[nx][ny]) return false;
        }
        return true;
    }

    private static int plant(int r, int c, boolean state) {
        int cost = map[r][c];
        visited[r][c] = state;

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];
            visited[nx][ny] = state;
            cost += map[nx][ny];
        }
        return cost;
    }
}