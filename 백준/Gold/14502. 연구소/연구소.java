import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int result = 0;
    static int[][] area;
    static int[][] tempArea;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동을 위한 방향 배열
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        area = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 벽 3개를 세우는 모든 경우의 수를 시도
        dfs(0);

        System.out.println(result);
    }

    // 벽을 3개 세우는 모든 조합을 시도하는 DFS
    static void dfs(int depth) {
        if(depth == 3) {
            // 임시 영역에 깊은 복사
            tempArea = new int[N][M];
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    tempArea[i][j] = area[i][j];
                }
            }
            
            // 바이러스 확산 후 안전 영역 계산
            spreadVirus();
            countSafeArea();
            return;
        }

        // 벽 세우기
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(area[i][j] == 0) {
                    area[i][j] = 1; // 벽 설치
                    dfs(depth + 1);
                    area[i][j] = 0; // 원상복구
                }
            }
        }
    }

    // BFS로 바이러스 확산
    static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();
        
        // 초기 바이러스 위치를 큐에 추가
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(tempArea[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        
        // BFS로 바이러스 확산
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            
            for(int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                // 범위 체크 및 빈 공간인지 확인
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && tempArea[nx][ny] == 0) {
                    tempArea[nx][ny] = 2; // 바이러스 확산
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
    
    // 안전 영역 계산
    static void countSafeArea() {
        int safeCount = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(tempArea[i][j] == 0) {
                    safeCount++;
                }
            }
        }
        
        // 최대값 갱신
        result = Math.max(result, safeCount);
    }
}