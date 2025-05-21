import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 손님의 수
            int M = Integer.parseInt(st.nextToken()); // 붕어빵 만드는데 걸리는 시간
            int K = Integer.parseInt(st.nextToken()); // M초당 만들 수 있는 붕어빵 개수

            st = new StringTokenizer(br.readLine());

            int[] arrived = new int[N]; // 손님이 도착하는 시간
            for(int i = 0; i < N; i++) {
                arrived[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrived); // 시간순으로 정렬
            
            boolean isPossible = true;
            
            // 각 손님이 도착할 때 붕어빵이 충분한지 확인
            for(int i = 0; i < N; i++) {
                // 현재 손님 도착 시간까지 만들어진 붕어빵 개수
                int madeFishBread = (arrived[i] / M) * K;
                
                // 이 시점까지 온 손님 수(현재 손님 포함): i+1
                if(madeFishBread < i+1) {
                    isPossible = false;
                    break;
                }
            }

            if(isPossible) {
                sb.append("#" + tc + " ").append("Possible" + "\n");
            } else {
                sb.append("#" + tc + " ").append("Impossible" + "\n");
            }
        }

        System.out.println(sb);
    }
}