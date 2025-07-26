import java.io.*;
import java.util.*;
public class Main {
	static int N, ans;
	static int[][] inning;
	static int[] players;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inning = new int[N][9];
		players = new int[9];
		visited = new boolean[9];
		players[3] = 0; // 4번 타자를 1번 선수로 결정
		visited[3] = true;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		batchPlayer(1);
		System.out.println(ans);
	}
	static void batchPlayer(int depth) {
		if(depth == 9) {
			gameStart();
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(visited[i]) {
				continue;
			}
			visited[i] = true;
			players[i] = depth;
			batchPlayer(depth + 1);
			visited[i] = false;
		}
	}
	static void gameStart() {
	    int out = 0;
	    int pre = 0;
	    int result = 0;
	    boolean[] base = new boolean[4]; // 1루, 2루, 3루 (인덱스 1,2,3 사용)
	    
	    for(int i = 0; i < N; i++) {
	        out = 0;
	        Arrays.fill(base, false); // 베이스 초기화
	        
	        while(out < 3) {
	            int currentBatter = pre % 9;
	            int hit = inning[i][players[currentBatter]];
	            
	            switch(hit) {
	                case 0: // 아웃
	                    out++;
	                    break;
	                case 1: // 안타
	                    if(base[3]) result++; // 3루 주자 득점
	                    base[3] = base[2];
	                    base[2] = base[1];
	                    base[1] = true; // 타자 1루 진출
	                    break;
	                case 2: // 2루타
	                    if(base[3]) result++; // 3루 주자 득점
	                    if(base[2]) result++; // 2루 주자 득점
	                    base[3] = base[1];
	                    base[2] = true; // 타자 2루 진출
	                    base[1] = false;
	                    break;
	                case 3: // 3루타
	                    if(base[3]) result++; // 3루 주자 득점
	                    if(base[2]) result++; // 2루 주자 득점
	                    if(base[1]) result++; // 1루 주자 득점
	                    base[3] = true; // 타자 3루 진출
	                    base[2] = false;
	                    base[1] = false;
	                    break;
	                case 4: // 홈런
	                    if(base[3]) result++; // 3루 주자 득점
	                    if(base[2]) result++; // 2루 주자 득점
	                    if(base[1]) result++; // 1루 주자 득점
	                    result++; // 타자 득점
	                    Arrays.fill(base, false);
	                    break;
	            }
	            
	            pre++; // 다음 타자로 이동
	        }
	    }
	    
	    ans = Math.max(ans, result);
	}
}