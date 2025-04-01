import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int max = 0;
    
    // 상하좌우 이동을 위한 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 모든 칸에서 시작해보기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]); // DFS로 테트로미노 찾기
                visited[i][j] = false;
                
                checkExceptionShape(i, j); // 'ㅗ' 모양 체크 (DFS로 표현 불가능)
            }
        }
        
        System.out.println(max);
    }
    
    // DFS로 깊이 4인 경로 찾기 (I, L, J, S, Z, T의 일부 모양)
    static void dfs(int x, int y, int depth, int sum) {
        // 테트로미노 완성 (4개의 정사각형)
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }
        
        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 범위 내에 있고 아직 방문하지 않은 칸이면
            if (isValid(nx, ny) && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }
    
    // 'ㅗ', 'ㅜ', 'ㅓ', 'ㅏ' 모양 체크 (별도 처리 필요)
    static void checkExceptionShape(int x, int y) {
        // 'ㅗ' 모양
        if (isValid(x-1, y) && isValid(x, y-1) && isValid(x, y+1)) {
            max = Math.max(max, map[x][y] + map[x-1][y] + map[x][y-1] + map[x][y+1]);
        }
        
        // 'ㅜ' 모양
        if (isValid(x+1, y) && isValid(x, y-1) && isValid(x, y+1)) {
            max = Math.max(max, map[x][y] + map[x+1][y] + map[x][y-1] + map[x][y+1]);
        }
        
        // 'ㅓ' 모양
        if (isValid(x-1, y) && isValid(x+1, y) && isValid(x, y-1)) {
            max = Math.max(max, map[x][y] + map[x-1][y] + map[x+1][y] + map[x][y-1]);
        }
        
        // 'ㅏ' 모양
        if (isValid(x-1, y) && isValid(x+1, y) && isValid(x, y+1)) {
            max = Math.max(max, map[x][y] + map[x-1][y] + map[x+1][y] + map[x][y+1]);
        }
    }
    
    // 좌표가 배열 범위 내에 있는지 확인
    static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}