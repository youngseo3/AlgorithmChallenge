import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] result = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			result[i] = i;
		}

		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			int temp = result[a];
			result[a] = result[b];
			result[b] = temp;
		}
		
		for(int i = 1; i <= N; i++) {
			System.out.print(result[i] + " ");
		}
		
	}
}