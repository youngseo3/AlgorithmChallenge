import java.io.*;
import java.util.*;

public class Solution {

    static int N, maxCoreCount, minWireLength, totalCores;
    static int[][] map;
    static ArrayList<Point> cores;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    // 3. 가장자리가 아닌 코어만 리스트에 추가
                    if (map[i][j] == 1) {
                        if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
                            cores.add(new Point(i, j));
                        }
                    }
                }
            }

            totalCores = cores.size();
            maxCoreCount = 0;
            minWireLength = Integer.MAX_VALUE;

            dfs(0, 0, 0);

            sb.append("#").append(tc).append(" ").append(minWireLength).append("\n");
        }
        System.out.print(sb);
    }

    // index: 현재 다룰 코어 인덱스, coreCnt: 연결된 코어 수, len: 총 전선 길이
    static void dfs(int index, int coreCnt, int len) {
        // 기저 조건: 모든 코어를 다 확인했을 때
        if (index == totalCores) {
            // 1. 최대 코어 수, 최소 전선 길이 갱신
            if (coreCnt > maxCoreCount) {
                maxCoreCount = coreCnt;
                minWireLength = len;
            } else if (coreCnt == maxCoreCount) {
                minWireLength = Math.min(minWireLength, len);
            }
            return;
        }

        Point currentCore = cores.get(index);
        int x = currentCore.x;
        int y = currentCore.y;

        // 4방향으로 연결 시도
        for (int i = 0; i < 4; i++) {
            if (isConnectable(x, y, i)) {
                // 2. setStatus가 길이를 반환하므로 cnt 변수 필요 없음
                int wireLen = setStatus(x, y, i, 2);
                dfs(index + 1, coreCnt + 1, len + wireLen);
                setStatus(x, y, i, 0); // 상태 복원
            }
        }

        // 4. 현재 코어를 연결하지 않는 경우
        dfs(index + 1, coreCnt, len);
    }

    static boolean isConnectable(int x, int y, int dir) {
        int nx = x, ny = y;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
            if (map[nx][ny] != 0) return false;
        }
        return true;
    }

    static int setStatus(int x, int y, int dir, int status) {
        int nx = x, ny = y;
        int count = 0;
        while (true) {
            nx += dx[dir];
            ny += dy[dir];
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
            map[nx][ny] = status;
            count++;
        }
        return count;
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}