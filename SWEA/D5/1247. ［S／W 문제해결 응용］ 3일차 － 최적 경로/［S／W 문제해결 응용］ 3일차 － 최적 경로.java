import java.io.*;
import java.util.*;

public class Solution {

    static int N, minDistance = Integer.MAX_VALUE;
    static Point company;
    static Point home;
    static Point[] customers;
    static boolean[] visited;
    static Point[] sel;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            company = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            customers = new Point[N];
            sel = new Point[N];
            visited = new boolean[N];
            minDistance = Integer.MAX_VALUE;
            
            for(int i = 0; i < N; i++) {
                customers[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            
            dfs(0);
            
            System.out.println("#" + tc + " " + minDistance);
        }
        
    }

    private static void dfs(int depth) {
        if(depth == N) {
//            System.out.println(Arrays.toString(sel));
            minDistance = Math.min(minDistance, calcEveryDistance());
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                sel[depth] = customers[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int calcEveryDistance() {
        int distance = 0;
        int homeToFirst = calcDistance(company.x, company.y, sel[0].x, sel[0].y);
        
        for(int i = 0; i < N - 1; i++) {
            distance += calcDistance(sel[i].x, sel[i].y, sel[i + 1].x, sel[i + 1].y);
        }
        
        int lastCSToHome = calcDistance(sel[N - 1].x, sel[N - 1].y, home.x, home.y);
        
        return homeToFirst + distance + lastCSToHome;
    }
    
    static int calcDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    static class Point {
        int x;
        int y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
    }
}

