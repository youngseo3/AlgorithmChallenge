import java.io.*;
import java.util.*;

public class Main {

	static int K, x, y;
	static int[][] map;
	static int tileNum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		int size = (int)Math.pow(2, K);
		map = new int[size + 1][size + 1];

		map[y][x] = -1;

		solve(size, 1, 1);

		printMap(size);
	}

	private static void solve(int size, int x, int y) {
		// 기저 사례 (Base Case): 정사각형 크기가 2x2 이면 타일을 채우고 종료
		if (size == 2) {
			tileNum++;
			for (int i = x; i < x + size; i++) {
				for (int j = y; j < y + size; j++) {
					if (map[i][j] == 0) { // 비어있는 칸(0)에 타일 번호 부여
						map[i][j] = tileNum;
					}
				}
			}
			return;
		}

		tileNum++;
		int mid = size / 2;

		// 좌측 하단 사분면(x, y)이 비었을 경우 -> 해당 사분면의 우측 상단에 타일 배치
		if (!isFilled(x, y, mid)) map[x + mid - 1][y + mid - 1] = tileNum;

		// 우측 하단 사분면(x, y + mid)이 비었을 경우 -> 해당 사분면의 좌측 상단에 타일 배치
		if (!isFilled(x, y + mid, mid)) map[x + mid - 1][y + mid] = tileNum;

		// 좌측 상단 사분면(x + mid, y)이 비었을 경우 -> 해당 사분면의 우측 하단에 타일 배치
		if (!isFilled(x + mid, y, mid)) map[x + mid][y + mid - 1] = tileNum;

		// 우측 상단 사분면(x + mid, y + mid)이 비었을 경우 -> 해당 사분면의 좌측 하단에 타일 배치
		if (!isFilled(x + mid, y + mid, mid)) map[x + mid][y + mid] = tileNum;

		solve(mid, x, y);
		solve(mid, x, y + mid);
		solve(mid, x + mid, y);
		solve(mid, x + mid, y + mid);
	}

	private static boolean isFilled(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != 0) {
					return true;
				}
			}
		}
		return false;
	}

	private static void printMap(int size) {
		for(int i = size; i > 0; i--) {
			for(int j = 1; j <= size; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
