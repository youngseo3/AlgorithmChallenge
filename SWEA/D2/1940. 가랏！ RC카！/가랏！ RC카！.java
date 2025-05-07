import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            int distance = 0;
            int speed = 0;
            int N = Integer.parseInt(br.readLine());

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int command = Integer.parseInt(st.nextToken());
                
                if(command == 1) { // 가속
                    speed += Integer.parseInt(st.nextToken());
                } else if(command == 2) { // 감속
                    speed -= Integer.parseInt(st.nextToken());
                }
                // command == 0인 경우는 현재 속도 유지

                if(speed < 0) {
                    speed = 0;
                }
                distance += speed;
            }

            sb.append("#").append(tc).append(" ").append(distance).append("\n");
        }

        System.out.println(sb);
    }
}