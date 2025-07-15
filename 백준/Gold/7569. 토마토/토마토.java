import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[][][] box;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[M][N][H];
        
        Queue<Point> queue = new LinkedList<>();
        int unripeCount = 0;
        
        // 입력 받기
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++) {
                    box[k][j][i] = Integer.parseInt(st.nextToken());
                    
                    if(box[k][j][i] == 1) {
                        // 모든 익은 토마토를 큐에 한 번에 추가
                        queue.add(new Point(k, j, i));
                    } else if(box[k][j][i] == 0) {
                        unripeCount++;
                    }
                }
            }
        }
        
        // 이미 모든 토마토가 익었다면
        if(unripeCount == 0) {
            System.out.println(0);
            return;
        }
        
        int day = bfs(queue);
        
        // 익지 못한 토마토가 남았는지 확인
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < M; k++) {
                    if(box[k][j][i] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        
        System.out.println(day);
    }
    
    static int bfs(Queue<Point> queue) {
        int day = 0;
        
        while(!queue.isEmpty()) {
            int size = queue.size(); // 현재 레벨의 토마토 개수
            
            // 같은 날에 익는 토마토들을 한 번에 처리
            for(int i = 0; i < size; i++) {
                Point p = queue.poll();
                
                for(int dir = 0; dir < 6; dir++) {
                    int nextX = dx[dir] + p.x;
                    int nextY = dy[dir] + p.y;
                    int nextZ = dz[dir] + p.z;
                    
                    if(nextX >= 0 && nextX < M &&
                       nextY >= 0 && nextY < N &&
                       nextZ >= 0 && nextZ < H &&
                       box[nextX][nextY][nextZ] == 0) {
                        
                        box[nextX][nextY][nextZ] = 1;
                        queue.add(new Point(nextX, nextY, nextZ));
                    }
                }
            }
            
            // 이번 라운드에서 새로 익힌 토마토가 있다면 하루 증가
            if(!queue.isEmpty()) {
                day++;
            }
        }
        
        return day;
    }
    
    static class Point {
        int x, y, z;
        
        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}