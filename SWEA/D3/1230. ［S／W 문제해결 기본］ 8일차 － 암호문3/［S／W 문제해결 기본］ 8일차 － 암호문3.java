import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    	StringTokenizer st;

        for(int tc = 1; tc <= 10; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	
        	List<Integer> list = new ArrayList<>();
        	while(st.hasMoreTokens()) {
        		list.add(Integer.parseInt(st.nextToken()));
        	}
        	
        	int M = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	
        	for(int i = 0; i < M; i++) {
        		String instruction = st.nextToken();
        		
        		if(instruction.equals("A")) {
            		int y = Integer.parseInt(st.nextToken());
            		for(int j = 0; j < y; j++) {
    					list.add(list.size() - 1, Integer.parseInt(st.nextToken()));
            		}
        			
					continue;
				}
        		
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		
        		if(instruction.equals("I")) {
        			for(int j = x; j < x + y; j++) {
    					list.add(j, Integer.parseInt(st.nextToken()));
            		}
        		} else if(instruction.equals("D")) {
					for(int j = 0; j < y; j++) {
						list.remove(x);
					}
				} 
        	}
        	
        	sb.append("#" + tc + " ");
        	for(int i = 0; i < 10; i++) {
        		sb.append(list.get(i) + " ");
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
	}

}
