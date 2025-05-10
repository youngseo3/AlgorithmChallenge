import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {
			br.readLine();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> queue = new ArrayDeque<>();
			
			for(int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			int i = 1;
			while(true) {
				int temp = queue.poll();

				if(i > 5) {
					i = 1;
				}
				
				if(temp - i <= 0) {
					temp = 0;
					queue.add(temp);
					
					break;
				}
				
				queue.add(temp - i++);
			}
			
			sb.append("#" + tc + " ");
			
			while(!queue.isEmpty()) {
				sb.append(queue.poll() + " ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
