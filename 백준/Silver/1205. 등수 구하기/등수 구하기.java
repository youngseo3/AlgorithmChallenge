import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int newScore = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] scores = new int[P];
		
		if(N > 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
			}
		} else {
			Arrays.fill(scores, -1);
		}
		
         if (N == P && newScore <= scores[N - 1]) {
            System.out.println(-1);
            return; // 프로그램 종료
         }
        
		int rank = 1;
        for (int i = 0; i < N; i++) {
            // 나보다 점수가 높은 사람이 있으면 내 등수는 밀려난다.
            // 하지만 내 점수가 기존 점수보다 크거나 같은 순간, 그 자리가 내 등수이다.
            if (newScore >= scores[i]) {
                rank = i + 1; // 등수는 1부터 시작하므로 i+1
                break; // 내 자리를 찾았으니 더 이상 탐색할 필요 없음
            } else {
                // 내 점수가 더 작으면, 일단 다음 등수로 밀려난다.
                rank = i + 2;
            }
        }
		
		System.out.println(rank);
	}

}
