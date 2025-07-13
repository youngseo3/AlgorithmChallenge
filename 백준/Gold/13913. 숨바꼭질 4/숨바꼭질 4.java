import java.io.*;
import java.util.*;

public class Main {

    static int[] check;
    static int[] next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        check = new int[100001];
        next = new int[100001];
        
        // check 배열을 -1로 초기화 (방문하지 않은 곳)
        Arrays.fill(check, -1);

        if(N == K) {
            System.out.println("0");
            System.out.println(N);
            return;
        }

        bfs(N, K);

        Stack<Integer> stack = new Stack<>();
        stack.push(K);
        int index = K;

        while(index != N) {
            stack.push(next[index]);
            index = next[index];
        }

        sb.append(check[K] + "\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }

    static void bfs(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(N);
        check[N] = 0;  // 시작점은 0

        while(!queue.isEmpty()) {
            int x = queue.poll();

            if(x == K) break;

            // x - 1로 이동
            if(x - 1 >= 0 && check[x - 1] == -1) {
                queue.offer(x - 1);
                check[x - 1] = check[x] + 1;
                next[x - 1] = x;
            }

            // x + 1로 이동  
            if(x + 1 < check.length && check[x + 1] == -1) {
                queue.offer(x + 1);
                check[x + 1] = check[x] + 1;
                next[x + 1] = x;
            }

            // x * 2로 이동
            if(x * 2 < check.length && check[x * 2] == -1) {
                queue.offer(x * 2);
                check[x * 2] = check[x] + 1;
                next[x * 2] = x;
            }
        }
    }
}