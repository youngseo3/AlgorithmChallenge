import java.io.*;
import java.util.*;

public class Main {

    private static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(B).add(A);
        }

        int max = 0;
        int[] counts = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            counts[i] = search(i, N, graph);
            max = Math.max(max, counts[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (counts[i] == max) {
                sb.append(i).append(" ");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int search(int start, int N, List<List<Integer>> graph) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int next : graph.get(curr)) {
                if (visited[next]) {
                    continue;
                }
                visited[next] = true;
                queue.add(next);
                count++;
            }
        }

        return count;
    }
}
