import java.io.*;
import java.util.*;
public class Main {
    static int N, M, R;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        solve(); // 배열 회전 실행
        printMap(); // 최종 결과를 출력하는 함수 호출
    }
    
    private static void solve() {
        // 모든 테두리를 독립적으로 회전
        int layers = Math.min(N, M) / 2; // 총 테두리 개수
        
        for (int layer = 0; layer < layers; layer++) {
            // 현재 테두리의 둘레 계산
            int height = N - (layer * 2);
            int width = M - (layer * 2);
            int perimeter = (height + width - 2) * 2;
            
            if (perimeter == 0) continue;
            
            // 실제 회전 횟수 (최적화)
            int rotateCnt = R % perimeter;
            
            // 계산된 횟수만큼 현재 테두리를 시계방향 회전
            for (int r = 0; r < rotateCnt; r++) {
                rotateLayer(layer);
            }
        }
    }
    
    private static void rotateLayer(int layer) {
        // 시계방향 회전: 왼쪽 위 모서리 값을 임시 저장
        int temp = map[layer][layer];
        
        // 위쪽 변 (왼쪽 <- 오른쪽)
        for (int k = layer; k < M - 1 - layer; k++) {
            map[layer][k] = map[layer][k + 1];
        }
        
        // 오른쪽 변 (위 <- 아래)  
        for (int k = layer; k < N - 1 - layer; k++) {
            map[k][M - 1 - layer] = map[k + 1][M - 1 - layer];
        }
        
        // 아래쪽 변 (오른쪽 <- 왼쪽)
        for (int k = M - 1 - layer; k > layer; k--) {
            map[N - 1 - layer][k] = map[N - 1 - layer][k - 1];
        }
        
        // 왼쪽 변 (아래 <- 위)
        for (int k = N - 1 - layer; k > layer; k--) {
            map[k][layer] = map[k - 1][layer];
        }
        
        // 임시 저장한 값을 왼쪽 변의 두 번째 위치에 배치
        map[layer + 1][layer] = temp;
    }
    
    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}