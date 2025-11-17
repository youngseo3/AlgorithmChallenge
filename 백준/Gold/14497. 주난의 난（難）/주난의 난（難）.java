import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        // [수정 1] 좌표 0-based 인덱싱 보정 (-1 처리)
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;
        
        char[][] arr = new char[N][M];
        for(int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        
        outer:
        for(int level = 1; ; level++) {
            boolean[][] visited = new boolean[N][M];
            
            Queue<Point> q = new ArrayDeque<>();
            q.add(new Point(x1, y1));
            visited[x1][y1] = true;
            
            while(!q.isEmpty()) {
                Point p = q.poll();
                
                for(int i = 0; i < 4; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    
                    if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                    if(visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true; // 방문 처리
                    
                    if(arr[nx][ny] == '1') {
                        arr[nx][ny] = '0';
                    } else if(arr[nx][ny] == '0'){
                        q.add(new Point(nx, ny));
                    } else if(arr[nx][ny] == '#') {
                        System.out.println(level);
                        break outer;
                    }
                }
            }

        }
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}