import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		ArrayDeque<Character> dq = new ArrayDeque<>();
		int sum = 0;
		
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '(') {
				dq.addFirst('(');
				sum += 1;
				
				if(input.charAt(i + 1) == ')') {
					// 2는 위에 들어간 1을 빼는 것 + removeLast를 여기서 안하기 때문에 size값에 -1
					sum += dq.size() - 2;
				}
			} 
			
			if(input.charAt(i) == ')') {
				dq.removeLast();
			}
		}
		
		System.out.println(sum);
	}

}
