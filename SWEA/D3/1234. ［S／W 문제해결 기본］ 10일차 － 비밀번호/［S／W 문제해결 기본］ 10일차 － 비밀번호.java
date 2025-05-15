import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			String password = st.nextToken();
			
			Stack<Character> stack = new Stack<>();
			
			for(int i = 0; i < N; i++) {
				char current = password.charAt(i);
				
				if(!stack.isEmpty() && stack.peek() == current) {
					stack.pop();
				} else {
					stack.push(current);
				}
			}
			
			sb.append("#" + tc + " ");
			
			StringBuilder result = new StringBuilder();
			while(!stack.isEmpty()) {
				result.append(stack.pop());
			}
			
			sb.append(result.reverse().toString() + "\n");
		}
        
        System.out.println(sb);
	}
}
