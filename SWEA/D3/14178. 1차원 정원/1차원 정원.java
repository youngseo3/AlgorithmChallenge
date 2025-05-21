import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            
            int cnt = N / (2 * D + 1);
            if(N % (2 * D + 1) != 0) {
            	cnt++;
            }
            
            sb.append("#" + tc + " ").append(cnt + "\n");
        }
        
        System.out.println(sb);
	}

}
