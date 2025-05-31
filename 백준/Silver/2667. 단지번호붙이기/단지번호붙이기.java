import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static char[][] map;
	static boolean[][] visited;

	static List<Integer> list = new ArrayList<>();
	static int[] r = {-1, 1, 0, 0};
	static int[] c = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			 map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == '1' && !visited[i][j]) {
                    visited[i][j] = true;
					bfs(i, j);
				}
			}
		}
		
        Collections.sort(list);

		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	static void bfs(int row, int col) {
		int result = 1;
		
		Queue<Integer> q = new LinkedList<>();
		q.add(row);
		q.add(col);
		
		while(!q.isEmpty()) {
			int r1 = q.poll();
			int c1 = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int addedRow = r1 + r[i];
				int addedCol = c1 + c[i];
				
				if((addedRow >= 0 && addedRow < N) && (addedCol >= 0 && addedCol < N)) {
					if(map[addedRow][addedCol] == '1' && !visited[addedRow][addedCol]) {
						visited[addedRow][addedCol] = true;
						result++;
						
						q.add(addedRow);
						q.add(addedCol);	
					}
				}
			}	
		}
		
		list.add(result);	
	}

}
