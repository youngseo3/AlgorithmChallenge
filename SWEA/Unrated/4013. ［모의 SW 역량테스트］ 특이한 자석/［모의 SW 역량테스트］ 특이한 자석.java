import java.io.*;
import java.util.*;

public class Solution {

	static int K;
	static int[] dir;
	static boolean[] visited;
	static int[][] magnetics;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		/*
		 * 회전 시키려는 자석의 번호부터 시작하여 
		 * 회전 하기 전 상태 확인한다.
		 * 
		 * 0번 자석의 날 2를 확인
		 * 1, 2번 자석의 날 2, 6을 확인
		 * 3번 자석의 날 6을 확인
		 * 
		 * 서로 붙어있는 (0, 1), (1, 2), (2, 3) 자석의 N, S가 서로 다른 지를 확인한다
		 * 
		 * 
		 */
		
		// 자석 회전하는 함수 필요
		
		// 모든 회전이 끝났을 때 점수 계산 필요
		for(int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());
			magnetics = new int[4][8];
			
			// 자석의 정보 입력 받기, 2차원 배열
			for(int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < 8; j++) {
					magnetics[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 회전 시키려는 자석의 번호, 회전 방향 입력 받기
			for(int i = 0; i < K; i++) {
				dir = new int[4];
				visited = new boolean[4];
				
				st = new StringTokenizer(br.readLine());
				
				int rotateMagnetic = Integer.parseInt(st.nextToken());
				int direction = Integer.parseInt(st.nextToken()); // 시계 1, 반시계 -1
				
				saveMagneticsDir(rotateMagnetic - 1, direction);
				rotate();
				
//				System.out.println(Arrays.toString(dir));
//				for(int j = 0; j < 4; j++) {
//					System.out.println(Arrays.toString(magnetics[j]));
//				}
			}
			
			int ans = 0;
			for(int j = 0; j < 4; j++) {
				if(magnetics[j][0] == 1) {
					ans += Math.pow(2, j);
				}
			}
			
			sb.append("#" + tc + " ").append(ans + "\n");
		}
		
		System.out.println(sb);
	}

	private static void rotate() {
		int[][] copyMagnetics = new int[4][8];
		
		for(int i = 0; i < 4; i++) {
			copyMagnetics[i] = Arrays.copyOf(magnetics[i], 8);
		}
		
		for(int i = 0; i < 4; i++) {
			if(dir[i] == 1) {
				for(int j = 0; j < 8; j++) {
					magnetics[i][j] = copyMagnetics[i][(j - 1 + 8) % 8];
				}
			}
			else if(dir[i] == -1) {
				for(int j = 0; j < 8; j++) {
					magnetics[i][j] = copyMagnetics[i][(j + 1) % 8];
				}
			}
		}
	}

	private static void saveMagneticsDir(int rotateMagnetic, int direction) {
		if(visited[rotateMagnetic]) return;
		
		dir[rotateMagnetic] = direction;
		visited[rotateMagnetic] = true;
		
		if(rotateMagnetic == 0) {
			if(magnetics[rotateMagnetic][2] != magnetics[rotateMagnetic + 1][6]) {
				saveMagneticsDir(rotateMagnetic + 1, direction * -1);
			}
		} 
		else if(rotateMagnetic == 3) {
			if(magnetics[rotateMagnetic][6] != magnetics[rotateMagnetic - 1][2]) {
				saveMagneticsDir(rotateMagnetic - 1, direction * -1);
			}
		} 
		else if(rotateMagnetic == 1 || rotateMagnetic == 2){
			if(magnetics[rotateMagnetic][2] != magnetics[rotateMagnetic + 1][6]) {
				saveMagneticsDir(rotateMagnetic + 1, direction * -1);
			}
			if(magnetics[rotateMagnetic][6] != magnetics[rotateMagnetic - 1][2]) {
				saveMagneticsDir(rotateMagnetic - 1, direction * -1);
			}
		}
	}

}
