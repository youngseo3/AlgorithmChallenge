import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[][] p;
	static boolean[][] visit;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		p = new int[N][M];
		visit = new boolean[N][M];

		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<M;j++)
				p[i][j] = input.charAt(j) - '0';
		}
		
		DFS(0, 0, 0);
		System.out.println(answer);
	}

	static void DFS(int depth, int num, int sum) {
		if(depth == N*M) {
			answer = Math.max(sum, answer);
			return;
		}

		int r = depth / M;
		int c = depth % M;

		if(visit[r][c]) {
			DFS(depth + 1, num, sum);
		}
		else {
			visit[r][c] = true;
			num = num * 10 + p[r][c];
			DFS(depth + 1, 0, sum + num);

			int i, temp = num;
			for(i=r+1;i<N;i++) {
				if(visit[i][c])
					break;

				visit[i][c] = true;
				temp = temp * 10 + p[i][c];
				DFS(depth + 1, 0, sum + temp);
			}
			
			for(int j=r+1;j<i;j++)
				visit[j][c] = false;
			
			temp = num;
			for(i=c+1;i<M;i++) {
				if(visit[r][i])
					break;
				visit[r][i] = true;
				temp = temp * 10 + p[r][i];
				DFS(depth + i - c + 1, 0, sum + temp);
			}

			for(int j=c+1;j<i;j++)
				visit[r][j] = false;
			
			visit[r][c] = false;
		}
	}
}
