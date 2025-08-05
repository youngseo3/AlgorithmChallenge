import java.io.*;
import java.util.*;

public class Main {

	static int R, C, MAX;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] arr;
	static List<Character> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
		list.add(arr[0][0]);
		dfs(0, 0, 1);
		
		System.out.println(MAX);
	}

private static void dfs(int x, int y, int cnt) {
    MAX = Math.max(MAX, cnt);  // 현재 위치에서 MAX 갱신
    
    for(int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        
        if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
        
        if(check(nx, ny)) { // 갈 수 있는 경우만
            list.add(arr[nx][ny]);
            dfs(nx, ny, cnt + 1);
            list.remove(list.size() - 1);  // 백트래킹
        }
    }
}

	private static boolean check(int x, int y) {
		for(char c: list) {
			if(c == arr[x][y]) {
				return false;
			}
		}
		
		return true;
	}
	
	
}
