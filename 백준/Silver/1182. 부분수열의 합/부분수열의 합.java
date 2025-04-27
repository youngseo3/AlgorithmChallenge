import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, S;
    static int count = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 부분수열 탐색 시작 (0번 인덱스부터, 현재까지의 합 0으로 시작)
        dfs(0, 0);
        
        // S가 0인 경우, 공집합도 합이 0이 되므로 하나 빼줌
        if(S == 0) {
            count--;  // 공집합 제외
        }

        System.out.println(count);
    }

    // index: 현재 선택할지 말지 결정할 원소의 인덱스
    // sum: 현재까지 선택한 원소들의 합
    static void dfs(int index, int sum) {
        // 모든 원소를 고려했을 때
        if(index == N) {
            // 합이 S와 같으면 카운트 증가
            if(sum == S) {
                count++;
            }
            return;
        }

        // 현재 원소를 선택하는 경우
        dfs(index + 1, sum + arr[index]);
        
        // 현재 원소를 선택하지 않는 경우
        dfs(index + 1, sum);
    }
}