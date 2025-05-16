import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int cityDistance = Integer.MAX_VALUE;
    static int[][] city;
    static List<int[]> homes = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> selected = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 1) {
                    homes.add(new int[]{i, j});
                } else if(city[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        // M개의 치킨집을 선택하는 모든 조합 시도
        selectChicken(0, 0);

        System.out.println(cityDistance);
    }

    // 치킨집 M개를 선택하는 조합 (DFS)
    static void selectChicken(int start, int depth) {
        if(depth == M) {
            // M개의 치킨집이 선택되면 도시의 치킨 거리 계산
            cityDistance = Math.min(cityDistance, calculateCityDistance());
            return;
        }

        // 조합을 구하기 위한 반복 (치킨집 중에서 M개 선택)
        for(int i = start; i < chickens.size(); i++) {
            selected.add(chickens.get(i));
            selectChicken(i + 1, depth + 1);
            selected.remove(selected.size() - 1);
        }
    }

    // 도시의 치킨 거리 계산
    static int calculateCityDistance() {
        int totalDistance = 0;
        
        // 각 집에 대해 가장 가까운 치킨집까지의 거리 계산
        for(int[] home : homes) {
            int minDist = Integer.MAX_VALUE;
            for(int[] chicken : selected) {
                int dist = Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]);
                minDist = Math.min(minDist, dist);
            }
            totalDistance += minDist;
        }
        
        return totalDistance;
    }
}