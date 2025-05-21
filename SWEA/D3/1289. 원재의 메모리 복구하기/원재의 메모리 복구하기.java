import java.io.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	String origin = br.readLine();
        	int cnt = 0;
        	
        	if(origin.charAt(0) == '1') {
        		cnt++;
			}
        	
        	for(int i = 1; i < origin.length(); i++) {
        		char now = origin.charAt(i);
        		char pre = origin.charAt(i - 1);
        		
        		if(now != pre) {
					cnt++;
				}
        	}
        	
        	sb.append("#" + tc + " ").append(cnt + "\n");
        }
        
        System.out.println(sb);
	}

}
