import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int A[] = new int[N]; // 수열 배열 
		int result[] = new int[N]; // 정답 배열 
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> myStack = new Stack<>();
		myStack.push(0);
		for(int i=1; i<N; i++) {
			while(!myStack.isEmpty() && A[myStack.peek()] < A[i]) {
				result[myStack.pop()] = A[i];
			}
			myStack.push(i);
		}
		
		while(!myStack.empty()) {
			result[myStack.pop()] = -1;
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i=0; i<N; i++) {
			bw.write(result[i] + " ");
		}
		
		bw.write("\n");
		bw.flush();
		
	}
}
