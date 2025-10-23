import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = {-1, -1, 0, 1 ,1 ,1, 0, -1}; // 0~7 (↑, ↖, ←, ↙, ↓, ↘, →, ↗)
	static int[] dy = {0, -1 ,-1 ,-1, 0, 1 ,1 ,1};
	
	static Fish[][] fishes;
	static int maxScore = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		fishes = new Fish[4][4];
		
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken()); // num
				int b = Integer.parseInt(st.nextToken()); // dir
				
				fishes[i][j] = new Fish(a, b - 1); // dir: 0~7
			}
		}
        
		solve();
		
		System.out.println(maxScore); // 최종 답안 출력
	}

	private static void solve() {
		// 처음에 상어가 0, 0에 들어가기
		Fish firstFish = fishes[0][0];
		int initialScore = firstFish.num;
		int initialDir = firstFish.dir;
		fishes[0][0] = null;
		
		// dfs(상어x, 상어y, 상어dir, 현재점수)
		dfs(0, 0, initialDir, initialScore);
	}
	
	/**
	 * 상어의 이동과 물고기의 이동을 재귀적으로 처리하는 핵심 함수
	 * @param sharkX 상어 현재 X
	 * @param sharkY 상어 현재 Y
	 * @param sharkDir 상어 현재 방향
	 * @param score 현재까지 먹은 점수
	 */
	private static void dfs(int sharkX, int sharkY, int sharkDir, int score) {
		
		// 1. 현재 점수로 maxScore 갱신
		maxScore = Math.max(maxScore, score);
		
		// 2. 현재 맵 상태를 완벽하게 복사 (백트래킹을 위함)
		Fish[][] mapBackup = new Fish[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(fishes[i][j] != null) {
					// 객체를 새로 생성해서 (Deep Copy)
					mapBackup[i][j] = new Fish(fishes[i][j].num, fishes[i][j].dir);
				}
			}
		}

		// 3. 물고기 이동 (static fishes 맵을 직접 수정)
		moveFishes(sharkX, sharkY);
		
		// 4. 상어 이동 (1~3칸)
		for(int i = 1; i <= 3; i++) {
			int nx = sharkX + dx[sharkDir] * i;
			int ny = sharkY + dy[sharkDir] * i;
			
			// 맵 경계를 벗어나지 않고
			if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
				// 물고기가 있는 칸이라면
				if(fishes[nx][ny] != null) {
					// 4-1. (시뮬레이션) 물고기를 먹는다
					Fish targetFish = fishes[nx][ny];
					fishes[nx][ny] = null; // 맵에서 제거
					
					// 4-2. (재귀 호출) 다음 상태로 넘어감
					dfs(nx, ny, targetFish.dir, score + targetFish.num);
					
					// 4-3. (백트래킹) 먹었던 물고기를 다시 맵에 복원
					fishes[nx][ny] = targetFish;
				}
			}
		}
		
		// 5. 맵 상태를 물고기 이동 전으로 완벽하게 복원
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				fishes[i][j] = mapBackup[i][j];
			}
		}
	}

	/**
	 * 1번~16번 물고기를 순서대로 이동시킴 (static fishes 맵을 직접 수정)
	 * @param sharkX 현재 상어 X (이동 불가 지역)
	 * @param sharkY 현재 상어 Y (이동 불가 지역)
	 */
	private static void moveFishes(int sharkX, int sharkY) {		
		// 1번부터 16번 물고기까지 순서대로
		for(int num = 1; num <= 16; num++) {
			boolean found = false;
			for(int x = 0; x < 4; x++) {
				for(int y = 0; y < 4; y++) {
					// 해당 번호의 물고기를 맵에서 찾으면
					if(fishes[x][y] != null && fishes[x][y].num == num) {
						int dir = fishes[x][y].dir;
						
						// 8방향 탐색
						for(int d = 0; d < 8; d++) {
							int currentDir = (dir + d) % 8; // 현재 방향 (회전)
							int nx = x + dx[currentDir];
							int ny = y + dy[currentDir];
							
							// 이동 가능한 위치인지 확인
							if(isMovingLoc(nx, ny, sharkX, sharkY)) {
								// 이동할 칸의 물고기(null일 수도 있음)
								Fish target = fishes[nx][ny];
								
								// 현재 물고기 이동
								fishes[nx][ny] = fishes[x][y];
								fishes[nx][ny].dir = currentDir; // 방향 갱신
								
								// 원래 칸에는 (nx, ny)에 있던 물고기를 둠 (자리 바꾸기)
								fishes[x][y] = target;
								
								found = true;
								break; // 이 물고기는 이동 완료
							}
						}
						
						found = true;
						break; // 이 물고기는 이동 완료
					}
				}
				if(found) break; // 다음 번호 물고기 찾으러
			}
		}
	}

	/**
	 * 물고기가 이동할 수 있는 칸인지 확인
	 * @param nx 이동할 X
	 * @param ny 이동할 Y
	 * @param sharkX 상어 X
	 * @param sharkY 상어 Y
	 * @return true/false
	 */
	private static boolean isMovingLoc(int nx, int ny, int sharkX, int sharkY) {
		// 1. 맵 경계 안
		if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) return false;
		// 2. 상어가 있는 칸
		if(nx == sharkX && ny == sharkY) return false;
		
		return true;
	}
	
	static class Fish implements Comparable<Fish> {
		int num;
		int dir;

		public Fish(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Fish [num=" + num + ", dir=" + dir + "]";
		}

		@Override
		public int compareTo(Fish o) {
			return Integer.compare(this.num, o.num);
		}
	}
}