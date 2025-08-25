import java.io.*;
import java.util.*;

public class Solution {
    static int N, K, maxHeight, ans;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            visited = new boolean[N][N];
            maxHeight = 0;
            ans = 0;

            // 맵 입력 및 최대 높이 찾기
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    maxHeight = Math.max(maxHeight, map[i][j]);
                }
            }

            // 최대 높이인 모든 지점에서 DFS 시작
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == maxHeight) {
                        visited[i][j] = true;
                        dfs(i, j, 1, false); // (x, y, 현재길이, 공사여부)
                        visited[i][j] = false;
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        
        System.out.print(sb);
    }

    private static void dfs(int x, int y, int length, boolean constructed) {
        ans = Math.max(ans, length);
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 범위 체크 및 방문 체크
            if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) {
                continue;
            }
            
            // 현재 높이보다 낮은 곳으로 이동 가능
            if(map[x][y] > map[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, length + 1, constructed);
                visited[nx][ny] = false;
            }
            // 현재 높이보다 높거나 같은 곳 -> 공사 가능한지 확인
            else if(!constructed) {
                // K만큼 깎아서 현재 높이보다 낮게 만들 수 있는가?
                int needToCut = map[nx][ny] - map[x][y] + 1;
                if(needToCut <= K) {
                    // 공사 실행
                    int original = map[nx][ny];
                    map[nx][ny] = map[x][y] - 1;
                    visited[nx][ny] = true;
                    
                    dfs(nx, ny, length + 1, true);
                    
                    // 백트래킹 (원복)
                    map[nx][ny] = original;
                    visited[nx][ny] = false;
                }
            }
        }
    }
}