import java.io.*;
import java.util.*;

public class Solution {

    static int N, M;
    static List<List<Integer>> relation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            relation = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                relation.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                relation.get(a).add(b);
                relation.get(b).add(a);
            }

            // BFS를 호출하여 초대할 친구의 수를 계산합니다.
            int inviteCount = bfs();

            sb.append("#").append(tc).append(" ").append(inviteCount).append("\n");
        }
        System.out.println(sb);
    }

    /**
     * 너비 우선 탐색(BFS)을 통해 상원이의 친구와 친구의 친구를 찾습니다.
     * @return 초대할 사람의 총 수
     */
    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int count = 0;

        // Queue에는 {사람 번호, 상원이와의 거리(깊이)}를 저장합니다.
        queue.offer(new int[]{1, 0});
        visited[1] = true; // 상원이는 방문 처리 (초대 인원에 포함하지 않음)

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int person = current[0];
            int depth = current[1];

            // 친구의 친구(depth=2)까지만 초대할 수 있으므로,
            // 현재 깊이가 2 이상이면 더 이상 친구를 초대하지 않습니다.
            if (depth >= 2) {
                continue;
            }

            // 현재 사람의 친구 목록을 순회합니다.
            for (int friend : relation.get(person)) {
                // 아직 초대 명단에 없는 친구라면
                if (!visited[friend]) {
                    visited[friend] = true; // 초대 명단에 추가 (방문 처리)
                    count++; // 초대 인원 수 증가
                    queue.offer(new int[]{friend, depth + 1}); // 다음 탐색을 위해 큐에 추가
                }
            }
        }
        return count;
    }
}