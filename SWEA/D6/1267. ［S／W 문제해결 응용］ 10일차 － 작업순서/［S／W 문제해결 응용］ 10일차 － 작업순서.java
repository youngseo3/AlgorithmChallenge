import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int tc = 1; tc <= 10; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            int[] inDegree = new int[V + 1];
            List<List<Integer>> edges = new ArrayList<>();
            for (int i = 0; i <= V; i++) {
                edges.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                edges.get(a).add(b);
                inDegree[b]++;
            }

            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= V; i++) {
                if (inDegree[i] == 0) {
                    q.offer(i);
                }
            }

            sb.append("#").append(tc).append(" ");

            while (!q.isEmpty()) {
                int current = q.poll();
                sb.append(current).append(" ");

                for (int next : edges.get(current)) {
                    inDegree[next]--;
                    if (inDegree[next] == 0) {
                        q.offer(next);
                    }
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}