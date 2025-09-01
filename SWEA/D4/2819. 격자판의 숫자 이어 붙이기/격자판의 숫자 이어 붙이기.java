import java.io.*;
import java.util.*;

public class Solution {

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map;
	static HashSet<String> hs;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		
		for(int tc = 1; tc <= T; tc++) {
			map = new char[4][4];
			hs = new HashSet<>();
			
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < 4; j++) {
					map[i][j] = (char)(st.nextToken().charAt(0) - '0');
				}
			}
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					dfs(i, j, 0, String.valueOf(map[i][j]));
				}
			}
			
			sb.append("#" + tc + " ").append(hs.size() + "\n");
		}
		
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int depth, String num) {
		if(depth == 6) {
			hs.add(num);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) continue;
			
			dfs(nx, ny, depth + 1, num + map[nx][ny]);
		}
	}

}
