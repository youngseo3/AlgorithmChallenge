import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			String s = br.readLine();
			
			if(isVPS(s)) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		
		System.out.println(sb);
	}

	static boolean isVPS(String s) {
		Stack<Character> stack = new Stack<>();

		for(int i = 0; i < s.length(); i++) {
			char tmp = s.charAt(i);
			
			if(tmp == '(') {
				stack.push(tmp);
			} else if(stack.isEmpty()) {
				return false;
			} else if(tmp == ')') {
				stack.pop(); 
			} 
		}
		
		return stack.isEmpty();
	}
}
