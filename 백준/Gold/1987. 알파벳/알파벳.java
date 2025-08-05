import java.io.*;
import java.util.*;

public class Main {

	static int R, C, MAX;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] arr;
	static boolean[] alphabet;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		alphabet = new boolean[26];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
		alphabet[arr[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		
		System.out.println(MAX);
	}

	private static void dfs(int x, int y, int cnt) {
		MAX = Math.max(MAX, cnt);
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			
			int idx = arr[nx][ny] - 'A';
			
			// 갈 수 있는 경우
			if(!alphabet[idx]) {
				alphabet[idx] = true;
				dfs(nx, ny, cnt + 1);
				alphabet[idx] = false;
			}
			
		}
		
	}
	
}
