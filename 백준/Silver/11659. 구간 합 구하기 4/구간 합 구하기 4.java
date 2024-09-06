import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int A[] = new int[N+1];
		for(int i=1; i<=N; i++) {
			A[i] = sc.nextInt();
		}
		
		long S[] = new long[N+1];
		S[0] = 0;
		for(int i=1; i<=N; i++) {
			S[i] = S[i-1] + A[i];
		}
		
		int a = 0;
		int b = 0;
		for(int i=0; i<M; i++) {
			a = sc.nextInt();
			b = sc.nextInt();
			System.out.println(S[b]-S[a-1]);
		}
	}

}