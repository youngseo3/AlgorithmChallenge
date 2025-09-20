import java.io.*;
import java.util.*;

public class Main {
    static int N, time;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static class Shark {
        int x, y, size, eatCount;
        
        public Shark(int x, int y, int size, int eatCount) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eatCount = eatCount;
        }
    }
    
    static class Fish implements Comparable<Fish> {
        int x, y, distance;
        
        public Fish(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
        
        @Override
        public int compareTo(Fish o) {
            if(this.distance != o.distance) {
                return Integer.compare(this.distance, o.distance);
            }
            if(this.x != o.x) {
                return Integer.compare(this.x, o.x);  // 위쪽 우선
            }
            return Integer.compare(this.y, o.y);     // 왼쪽 우선
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        Shark shark = null;
        
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                    map[i][j] = 0;  // 상어 위치는 빈 공간으로
                }
            }
        }
        
        time = 0;
        
        while(true) {
            // BFS로 먹을 수 있는 물고기 찾기
            Fish targetFish = findNearestFish(shark);
            
            if(targetFish == null) {
                break;  // 더 이상 먹을 수 있는 물고기가 없음
            }
            
            // 물고기를 먹으러 이동
            time += targetFish.distance;
            shark.x = targetFish.x;
            shark.y = targetFish.y;
            shark.eatCount++;
            
            // 해당 위치의 물고기 제거
            map[targetFish.x][targetFish.y] = 0;
            
            // 상어 크기 증가 체크
            if(shark.eatCount == shark.size) {
                shark.size++;
                shark.eatCount = 0;
            }
        }
        
        System.out.println(time);
    }
    
    /**
     * BFS로 먹을 수 있는 가장 가까운 물고기 찾기
     */
    private static Fish findNearestFish(Shark shark) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();
        List<Fish> candidates = new ArrayList<>();
        
        queue.offer(new int[]{shark.x, shark.y, 0}); // x, y, distance
        visited[shark.x][shark.y] = true;
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int dist = current[2];
            
            // 현재 위치에 먹을 수 있는 물고기가 있는지 확인
            if(map[x][y] != 0 && map[x][y] < shark.size) {
                candidates.add(new Fish(x, y, dist));
            }
            
            // 4방향 탐색
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] > shark.size) continue;  // 큰 물고기는 지나갈 수 없음
                
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny, dist + 1});
            }
        }
        
        if(candidates.isEmpty()) {
            return null;
        }
        
        // 조건에 맞는 물고기 선택 (거리 → 위쪽 → 왼쪽 순)
        Collections.sort(candidates);
        return candidates.get(0);
    }
}