import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] A = new int[N + 1]; // 기존 값 배열
		int[] F = new int[1000001]; // 카운트 배열
		int[] NGF = new int[N + 1]; // 오등큰수 배열
		Stack<Integer> stack = new Stack<>(); // A 배열의 인덱스를 저장
		StringBuilder sb = new StringBuilder();

		for(int i = 1; i < N + 1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N + 1; i++) {
			int temp = A[i];
			F[temp] += 1;
		}
		
		for(int i = 1; i < N + 1; i++) {
			while(!stack.isEmpty() && F[A[i]] > F[A[stack.peek()]]) {
				NGF[stack.pop()] = A[i];
			}
			
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			NGF[stack.pop()] = -1;
		}
		
		for(int i = 1; i < N + 1; i++) {
			sb.append(NGF[i] + " ");
		}
		
		System.out.println(sb);
	}

}
