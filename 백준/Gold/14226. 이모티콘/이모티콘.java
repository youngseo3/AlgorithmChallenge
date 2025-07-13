import java.io.*;
import java.util.*;

public class Main {

	static int S;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());

		// 화면의 이모티콘 수, 클립보드의 이모티콘 수 
		time = new int[1001][1001];
		
		bfs();
		
		int result = Integer.MAX_VALUE;
		for(int clipboard = 0; clipboard <= 1000; clipboard++) {
			if(time[S][clipboard] > 0) {
				result = Math.min(result, time[S][clipboard]);
			}
		}
		
		System.out.println(result);
	}

	static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {1, 0});
		time[1][0] = 0;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			int screen = current[0];
			int clipboard = current[1];
			
			if(screen == S) break;

            // 1. 복사하기: (screen, clipboard) -> (screen, screen)
			if(screen <= 1000 && time[screen][screen] == 0) {
				q.add(new int[] {screen, screen});
				time[screen][screen] = time[screen][clipboard] + 1;
			}
			
			// 2. 붙여넣기: (screen, clipboard) -> (screen + clipboard, clipboard)
            if(clipboard > 0 && screen + clipboard <= 1000 && time[screen + clipboard][clipboard] == 0) {
                q.add(new int[]{screen + clipboard, clipboard});
                time[screen + clipboard][clipboard] = time[screen][clipboard] + 1;
            }

            // 3. 삭제하기: (screen, clipboard) -> (screen - 1, clipboard)
            if(screen - 1 >= 1 && time[screen - 1][clipboard] == 0) {
                q.add(new int[]{screen - 1, clipboard});
                time[screen - 1][clipboard] = time[screen][clipboard] + 1;
            }
		}
	}

}
