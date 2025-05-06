import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] lastDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	int untilDays = 0;
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int m1 = Integer.parseInt(st.nextToken());
        	int d1 = Integer.parseInt(st.nextToken());
        	int m2 = Integer.parseInt(st.nextToken());
        	int d2 = Integer.parseInt(st.nextToken());
        	
        	for(int m = m1; m <= m2; m++) {
        		untilDays += lastDays[m];
        	}
        	
        	untilDays = untilDays - d1 + 1 - (lastDays[m2] - d2);
        	
        	sb.append("#" + tc + " ").append(untilDays + "\n");
        }
        
        System.out.println(sb);
	}

}
