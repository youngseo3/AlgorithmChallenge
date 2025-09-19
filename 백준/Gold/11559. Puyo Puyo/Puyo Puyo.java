import java.io.*;
import java.util.*;

public class Main {

	static int pop;
	static boolean isPop;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static char[][] map = new char[12][6];
	static char[][] originMap = new char[12][6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		while(true) {
			isPop = false;

			bfs(); // 연쇄가 가능한 뿌요들을 다 터트린 후 종료
			onFloor(); // 연쇄가 끝난 후 뿌요들을 맨 아래까지 내림

			// 더 이상 연쇄가 발생할 것이 없다면 탈출
			if(isPop == false) {
				break;
			}

			pop++; // 한 타이밍에 연쇄가 여러 번 일어나도 한 번으로 카운트 함.
		}

		System.out.println(pop);
	}

	private static void bfs() {
		Queue<Puyo> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[12][6];
		
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 6; j++) {
				if(map[i][j] != '.' && !visited[i][j]) {
					ArrayList<int[]> list = new ArrayList<>();
					
					q.offer(new Puyo(i, j, map[i][j]));
					list.add(new int[] {i, j});
					visited[i][j] = true;
					
					while(!q.isEmpty()) {
						Puyo cur = q.poll();
						
						for(int k = 0; k < 4; k++) {
							int nx = cur.x + dx[k];
							int ny = cur.y + dy[k];
							
							if(nx < 0 || nx >= 12 || ny < 0 || ny >= 6) continue;
							
							if(!visited[nx][ny] && map[nx][ny] == cur.color) {
								q.offer(new Puyo(nx, ny, map[nx][ny]));
								list.add(new int[] {nx, ny});
								visited[nx][ny] = true;
							}
						}
					}
					
					if(list.size() >= 4) {
						for(int k = 0; k < list.size(); k++) {
							int x = list.get(k)[0];
							int y = list.get(k)[1];
							
							map[x][y] = '.';
							
							isPop = true;
						}
					}
				}
			}
		}
	}

	private static void onFloor() {
		for(int j = 0; j < 6; j++) {
			Queue<Puyo> q = new ArrayDeque<>();
			int idx = 11;
			
			for(int i = 11; i >= 0; i--) {
				if(map[i][j] != '.') {
					q.offer(new Puyo(i, j, map[i][j]));
					map[i][j] = '.';
				}
			}
			
			while(!q.isEmpty()) {
				Puyo p = q.poll();
				
				map[idx][j] = p.color;
				idx--;
			}
		}
	}

	static class Puyo {
		int x;
		int y;
		char color;
		
		public Puyo(int x, int y, char color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}
}
