import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][][] visited; // visited[x][y][wall] - wall: 0(벽 안부숨), 1(벽 부숨)
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];
        
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        int result = bfs();
        System.out.println(result);
    }
    
    static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0, 1}); // {x, y, wall, distance}
        visited[0][0][0] = true;
        
        while(!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            int wall = current[2];
            int distance = current[3];
            
            // 목적지에 도달한 경우
            if(x == N-1 && y == M-1) {
                return distance;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    // 빈 공간인 경우
                    if(map[nx][ny] == 0) {
                        if(!visited[nx][ny][wall]) {
                            visited[nx][ny][wall] = true;
                            q.offer(new int[]{nx, ny, wall, distance + 1});
                        }
                    }
                    // 벽인 경우 - 아직 벽을 부수지 않았다면 부술 수 있음
                    else if(map[nx][ny] == 1 && wall == 0) {
                        if(!visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            q.offer(new int[]{nx, ny, 1, distance + 1});
                        }
                    }
                }
            }
        }
        
        return -1; // 목적지에 도달할 수 없는 경우
    }
}