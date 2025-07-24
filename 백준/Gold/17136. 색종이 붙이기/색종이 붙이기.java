import java.io.*;
import java.util.*;

public class Main {
    // 각 색종이 크기별 상대 위치 (크기별로 분리)
    static int[][] dx = {
        {0},                                    // 1x1
        {0, 0, 1, 1},                          // 2x2
        {0, 0, 0, 1, 1, 1, 2, 2, 2},          // 3x3
        {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, // 4x4
        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4} // 5x5
    };
    
    static int[][] dy = {
        {0},                                    // 1x1
        {0, 1, 0, 1},                          // 2x2
        {0, 1, 2, 0, 1, 2, 0, 1, 2},          // 3x3
        {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3}, // 4x4
        {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4} // 5x5
    };
    
    static int[][] paper = new int[10][10];
    static int[] cnt = {0, 5, 5, 5, 5, 5};  // 인덱스 1부터 사용 (1x1~5x5)
    static int answer = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        for(int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 0, 0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
    
    static void dfs(int x, int y, int used) {
        // 가지치기
        if(used >= answer) return;
        
        // 다음 1을 찾기
        while(x < 10) {
            while(y < 10 && paper[x][y] == 0) {
                y++;
            }
            if(y < 10) break;
            x++;
            y = 0;
        }
        
        // 모든 1을 덮었으면 답 갱신
        if(x == 10) {
            answer = Math.min(answer, used);
            return;
        }
        
        // 현재 위치 (x, y)에서 가능한 색종이들 시도 (큰 것부터)
        for(int size = 5; size >= 1; size--) {
            if(cnt[size] > 0 && canPlace(x, y, size)) {
                // 색종이 놓기
                place(x, y, size, 0);
                cnt[size]--;
                
                dfs(x, y, used + 1);
                
                // 백트래킹
                place(x, y, size, 1);
                cnt[size]++;
            }
        }
    }
    
    // size 크기 색종이를 (x, y)에 놓을 수 있는지 확인
    static boolean canPlace(int x, int y, int size) {
        for(int i = 0; i < dx[size-1].length; i++) {
            int nx = x + dx[size-1][i];
            int ny = y + dy[size-1][i];
            
            if(nx >= 10 || ny >= 10 || paper[nx][ny] == 0) {
                return false;
            }
        }
        return true;
    }
    
    // size 크기 색종이를 (x, y)에 놓거나 제거 (value: 0=놓기, 1=제거)
    static void place(int x, int y, int size, int value) {
        for(int i = 0; i < dx[size-1].length; i++) {
            int nx = x + dx[size-1][i];
            int ny = y + dy[size-1][i];
            paper[nx][ny] = value;
        }
    }
}