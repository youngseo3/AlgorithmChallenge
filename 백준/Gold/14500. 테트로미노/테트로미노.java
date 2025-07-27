import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;

	// 상하좌우 이동을 위한 배열
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;

				checkExceptionShape(i, j);
			}
		}


		System.out.println(max);
	}

	static void dfs(int x, int y, int depth, int sum) {
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}

		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(isValid(nx, ny) && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(nx, ny, depth + 1, sum + map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}

	static void checkExceptionShape(int x, int y) {
		// 'ㅗ'
		if(isValid(x - 1, y) && isValid(x, y - 1) && isValid(x, y + 1)) {
			max = Math.max(max, map[x][y] + map[x - 1][y] + map[x][y - 1] + map[x][y + 1]);
		}

		// 'ㅜ'
		if(isValid(x + 1, y) && isValid(x, y - 1) && isValid(x, y + 1)) {
			max = Math.max(max, map[x][y] + map[x + 1][y] + map[x][y - 1] + map[x][y + 1]);
		}
		
		// 'ㅏ'
		if(isValid(x - 1, y) && isValid(x + 1, y) && isValid(x, y + 1)) {
			max = Math.max(max, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y + 1]);
		}
		
		// 'ㅓ'
		if(isValid(x - 1, y) && isValid(x + 1, y) && isValid(x, y - 1)) {
			max = Math.max(max, map[x][y] + map[x - 1][y] + map[x + 1][y] + map[x][y - 1]);
		}
	}
	// 좌표가 배열 범위 내에 있는지 확인
	static boolean isValid(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
