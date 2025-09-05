import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] T = new int[N]; // 상담 기간
        int[] P = new int[N]; // 상담 수익
        for (int i = 0; i < N; i++) {
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }

        // dp[i] : i번째 날부터 마지막 날까지 얻을 수 있는 최대 수익
        int[] dp = new int[N + 1]; // N+1 크기로 만들어 마지막 날(퇴사일) 처리를 편하게 함

        // 뒤에서부터 거꾸로 DP 테이블을 채워나감
        for (int i = N - 1; i >= 0; i--) {
            int dayAfter = i + T[i]; // 상담이 끝나는 날짜

            // 1. 상담을 할 수 있는 경우 (퇴사일을 넘기지 않는 경우)
            if (dayAfter <= N) {
                // dp[i] = max(오늘 상담 안하고 내일부터 버는 돈, 오늘 상담하고 버는 돈)
                // 오늘 상담하고 버는 돈 = P[i] + (상담 끝난 날부터 벌 수 있는 최대 수익)
                dp[i] = Math.max(dp[i + 1], P[i] + dp[dayAfter]);
            }
            // 2. 상담을 할 수 없는 경우 (퇴사일을 넘기는 경우)
            else {
                // 오늘 상담은 포기해야 하므로, 최대 수익은 내일부터 버는 돈과 같음
                dp[i] = dp[i + 1];
            }
        }

        // dp[0]에 0일부터 마지막까지 얻는 최대 수익이 저장됨
        System.out.println(dp[0]);
        
        sc.close();
    }
}