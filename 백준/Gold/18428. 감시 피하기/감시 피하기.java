import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static boolean exit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] visited;
	static char[][] board;
	static List<int[]> teachers = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		board = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for(int j = 0; j < N; j++) {
				board[i][j] = st.nextToken().charAt(0);
				if(board[i][j] == 'T') {
					teachers.add(new int[] {i, j});
				}
			}
		}

		backTrack(0);
		System.out.println(exit ? "YES" : "NO");
	}

	private static void backTrack(int cnt) {	
		if(exit) return;
		
		// 모든 학생들이 감시로부터 피하는지
		if(cnt == 3) {
			if(avoid()) exit = true;
			
			return;
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && board[i][j] == 'X') {
					visited[i][j] = true;
					backTrack(cnt + 1);
					visited[i][j] = false;
				}
			}
		}
	}

	private static boolean avoid() {
		boolean tf = false;
		
		for(int[] t: teachers) {
			int x = t[0];
			int y = t[1];
			
			for(int i = 0; i < 4; i++) {
				for(int power = 1; power < N; power++) {
					int nx = x + dx[i] * power;
					int ny = y + dy[i] * power;

					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if(visited[nx][ny]) break;
					if(board[nx][ny] == 'S') {
						return false;
					}
				}
			}
		}
	
		return true;
	}

}
