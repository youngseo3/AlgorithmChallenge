import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static boolean[][] visited;
    static boolean isCycle = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < N && !isCycle; i++) {
            for(int j = 0; j < M && !isCycle; j++) {
                if(!visited[i][j]) {
                    dfs(i, j, -1, -1); // 부모 좌표를 -1, -1로 초기화
                }
            }
        }
        
        System.out.println(isCycle ? "Yes" : "No");
	}

	private static void dfs(int x, int y, int parentX, int parentY) {
		visited[x][y] = true;
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
			
			if(map[x][y] != map[nx][ny]) continue;
			
			if(nx == parentX && ny == parentY) continue;
			
			if(visited[nx][ny]) {
				isCycle = true;
				return;
			}
			
			dfs(nx, ny, x, y);
			
			if(isCycle) return;
		}
	}

}
