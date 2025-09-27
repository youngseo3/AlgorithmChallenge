import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] dist = new boolean[N + 1][N + 1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			dist[a][b] = true;
		}

		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(dist[i][k] && dist[k][j]) {
						dist[i][j] = true;
					}
				}
			}
		}
		// (이전 플로이드-워셜 코드는 그대로 둡니다)

		int answer = 0;
		int mid = (N + 1) / 2;

		// 1번부터 N번 구슬까지 하나씩 검사
		for(int i = 1; i <= N; i++) {
			int heavierCount = 0; // i보다 무거운 구슬의 개수
			int lighterCount = 0; // i보다 가벼운 구슬의 개수

			for(int j = 1; j <= N; j++) {
				if(i == j) continue;

				// dist[j][i]가 true이면, j가 i보다 무겁다는 의미
				if(dist[j][i]) {
					heavierCount++;
				}

				// dist[i][j]가 true이면, i가 j보다 무겁다는 의미 (즉, j는 i보다 가볍다)
				if(dist[i][j]) {
					lighterCount++;
				}
			}

			// 나보다 무거운 구슬 또는 나보다 가벼운 구슬의 수가 중간값을 넘으면
			// 이 구슬은 절대 중간 무게가 될 수 없다.
			if(heavierCount >= mid || lighterCount >= mid) {
				answer++;
			}
		}

		System.out.println(answer);
	}


}
