import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] result = new int[N];
		
		for(int i = 0; i < N; i++) {
			result[i] = i + 1;
		}
		
		for(int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			result = reverse(a, b, result);
		}

		for(int i = 0; i < N; i++) {
			System.out.print(result[i] + " ");
		}
		
	}

	private static int[] reverse(int a, int b, int[] ary) {
		final int startIndex = a - 1;
		final int endIndex = b - 1;
		int t = b - 1;
		int[] result = ary.clone();
		
		for(int i = startIndex; i <= endIndex; i++) {
			result[i] = ary[t--];
		}
		
		return result;
	}

}
