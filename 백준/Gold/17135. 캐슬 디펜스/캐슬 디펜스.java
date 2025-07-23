import java.io.*;
import java.util.*;

public class Main {
    static int N, M, D, maxEnemy;
    static int[][] map;
    static int[][] movingMap;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M];
        movingMap = new int[N + 1][M];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                movingMap[i][j] = value;
            }
        }
        
        setUp(0, 0);
        System.out.println(maxEnemy);
    }
    
    static void setUp(int archer, int start) {
        if(archer == 3) {
            gameStart();
            return;
        }
        
        for(int i = start; i < M; i++) {
            movingMap[N][i] = 2;
            setUp(archer + 1, i + 1);
            movingMap[N][i] = 0;
        }
    }
    
    static void gameStart() {
        int enemy = 0;
        
        for(int i = 0; i < N; i++) {
            enemy += userTurn();
            computerTurn();
        }
        
        maxEnemy = Math.max(maxEnemy, enemy);
        
        // movingMap 초기화
        for(int i = 0; i < N; i++) {            
            for(int j = 0; j < M; j++) {
                movingMap[i][j] = map[i][j];
            }
        }
    }
    
    static int userTurn() {        
        int enemy = 0;
        boolean[][] deadEnemy = new boolean[N][M];
        
        for(int i = 0; i < M; i++) {
            if(movingMap[N][i] == 2) { // 궁수가 있는 위치
                int minDistance = Integer.MAX_VALUE;
                int targetRow = -1, targetCol = -1;
                
                // 1단계: 가장 가까운 거리 찾기
                for(int j = 0; j < N; j++) {
                    for(int k = 0; k < M; k++) {
                        if(movingMap[j][k] == 1) {
                            int distance = getDistance(N, i, j, k);
                            if(distance <= D) {
                                minDistance = Math.min(minDistance, distance);
                            }
                        }
                    }
                }
                
                // 2단계: 같은 거리의 적 중 가장 왼쪽 적 찾기
                if(minDistance != Integer.MAX_VALUE) {
                    for(int k = 0; k < M; k++) { // 열 우선 탐색
                        for(int j = 0; j < N; j++) {
                            if(movingMap[j][k] == 1) {
                                int distance = getDistance(N, i, j, k);
                                if(distance == minDistance) {
                                    targetRow = j;
                                    targetCol = k;
                                    break; // 가장 왼쪽 적을 찾았으므로 종료
                                }
                            }
                        }
                        if(targetRow != -1) break; // 적을 찾았으므로 종료
                    }
                    
                    if(targetRow != -1) {
                        deadEnemy[targetRow][targetCol] = true;
                    }
                }
            }
        }
        
        // 죽은 적들 제거 및 카운트
        for(int j = 0; j < N; j++) {
            for(int k = 0; k < M; k++) {
                if(deadEnemy[j][k]) {
                    movingMap[j][k] = 0;
                    enemy++;
                }
            }
        }
        
        return enemy;
    }
    
    static void computerTurn() {
        for(int i = N - 2; i >= 0; i--) {            
            for(int j = 0; j < M; j++) {
                movingMap[i + 1][j] = movingMap[i][j];
            }
        }
        
        for(int i = 0; i < M; i++) {            
            movingMap[0][i] = 0;
        }
    }
    
    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}