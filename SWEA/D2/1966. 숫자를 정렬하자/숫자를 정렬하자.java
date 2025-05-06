import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[] arr = new int[N];

        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	Arrays.sort(arr);
        	
        	sb.append("#" + tc + " ");
        	for(int i = 0; i < N; i++) {
            	sb.append(arr[i] + " ");
        	}
            sb.append("\n");

        }
        
        System.out.println(sb);
	}

}
