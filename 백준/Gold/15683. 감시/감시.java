import java.io.*;
import java.util.*;

public class Main {

	static int N, M, min = Integer.MAX_VALUE;
	// 상, 하, 좌, 우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	// dddxy[cctv타입][회전방향][감시방향]
	static int[][][] cctvDirs = {
			{}, // 0번 인덱스 비우기
			{{0}, {1}, {2}, {3}}, // 1번 CCTV
			{{2, 3}, {0, 1}}, // 2번 CCTV
			{{0, 3}, {0, 2}, {1, 3}, {1, 2}}, // 3번 CCTV (상우, 상좌, 하우, 하좌)
			{{0, 2, 3}, {0, 1, 3}, {0, 1, 2}, {1, 2, 3}}, // 4번 CCTV
			{{0, 1, 2, 3}} // 5번 CCTV
	};
	static List<CCTV> cctvs = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6) {
					cctvs.add(new CCTV(i, j, map[i][j]));
				}
			}
		}

		// 재귀 시작
		solve(0, map);
		System.out.println(min);
	}

	// depth: 현재 다루고 있는 cctv의 인덱스
	// currentMap: 이전 cctv까지 감시 적용된 맵 상태
	static void solve(int depth, int[][] currentMap) {
		// 모든 CCTV의 방향을 결정했을 때
		if (depth == cctvs.size()) {
			int blindSpot = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (currentMap[i][j] == 0) {
						blindSpot++;
					}
				}
			}
			min = Math.min(min, blindSpot);
			return;
		}

		CCTV cctv = cctvs.get(depth);
		int[][] directions = cctvDirs[cctv.num];

		// 현재 CCTV의 모든 회전 방향을 시도
		for (int i = 0; i < directions.length; i++) {
			// 다음 재귀로 넘겨줄 맵을 복사
			int[][] tempMap = new int[N][M];
			for(int r = 0; r < N; r++) {
				tempMap[r] = Arrays.copyOf(currentMap[r], M);
			}

			// 현재 회전 방향으로 감시 영역 표시
			int[] currentDirs = directions[i];
			for (int dir : currentDirs) {
				int nx = cctv.x;
				int ny = cctv.y;

				while (true) {
					nx += dx[dir];
					ny += dy[dir];

					if (nx < 0 || nx >= N || ny < 0 || ny >= M || tempMap[nx][ny] == 6) {
						break;
					}

					// 다른 CCTV나 빈 공간을 감시 영역으로 변경
					if (tempMap[nx][ny] == 0) {
						tempMap[nx][ny] = -1; // # 대신 -1 사용
					}
				}
			}

			// 다음 CCTV로 재귀 호출
			solve(depth + 1, tempMap);
		}
	}

	static class CCTV {
		int x, y, num;
		public CCTV(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
	}
}