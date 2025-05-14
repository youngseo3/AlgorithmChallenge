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
        	
        	int[] code = new int[N];
        	for (int i = 0; i < code.length; i++) {
				code[i] = Integer.parseInt(st.nextToken());
			}
        	
        	int M = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
    		Queue<Integer> queue = new LinkedList<>();
    		Queue<Integer> temp = new LinkedList<>();

        	for(int i = 0; i < M; i++) {
        		String start = st.nextToken();
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		
        		for(int j = 0; j < y; j++) {
        			queue.add(Integer.parseInt(st.nextToken()));
        		}
        		
        		for(int j = x; j < x + y && j < 10; j++) {
        			temp.add(code[j]);
        			code[j] = queue.poll();
        		}
        		
        		for(int j = x + y; j < 10 ; j++) {
        			temp.add(code[j]);
        		}
        		
        		for(int j = x + y; j < 10 ; j++) {
        			code[j] = temp.poll();
        		}
        		
        		temp.clear();
        		queue.clear();
			}
        	
        	sb.append("#" + tc + " ");
        	for(int i = 0; i < 10; i++) {
        		sb.append(code[i] + " ");
        	}
        	sb.append("\n");
        	
        }
        
        System.out.println(sb);
	}

}
