import java.io.*;
import java.util.*;

public class Solution {

	static int N, maxMove, maxRoom;
	static int[] dx = {-1, 1, 0 ,0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] rooms;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			rooms = new int[N][N];
			maxRoom = 0;
			maxMove = 0;

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int currentMove = backTrack(i, j, 1); // DFS로 이동 거리 계산

					// 최대 이동 거리 업데이트 로직
					if (currentMove > maxMove) {
						maxMove = currentMove;
						maxRoom = rooms[i][j]; // 시작 방 번호 저장
					} else if (currentMove == maxMove) {
						if (rooms[i][j] < maxRoom) { // 방 번호가 더 작으면 업데이트
							maxRoom = rooms[i][j];
						}
					}
				}
			}

			sb.append("#" + tc + " ").append(maxRoom + " ").append(maxMove + "\n");
		}

		System.out.println(sb);
	}

	private static int backTrack(int x, int y, int cnt) {
		for(int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

			if(rooms[nx][ny] == rooms[x][y] + 1) {
				return backTrack(nx, ny, cnt + 1);
			}
		}

		return cnt;
	}


}
