import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		Stack<Integer> stack = new Stack<>();
		StringBuffer bf = new StringBuffer();  
	    int num = 1; // 오름차순 수
	    boolean result = true;
		for(int i=0; i<A.length; i++) {
			int s = A[i];
			
			while(s >= num) {
				stack.push(num++);
				bf.append("+\n");
			}
			
			if(s < num) {
				int n = stack.pop();
				if(s < n) {
					System.out.println("NO");
					result = false;
					break;
				}
				else {
					bf.append("-\n");
				}
			}
		}
		if (result) System.out.println(bf.toString());

	}

}
