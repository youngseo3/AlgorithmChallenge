import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc <= 10; tc++) {
			sc.nextInt();
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int result = 1;
			for(int i = 0; i < M; i++) {
				result *= N;
			}
			
            System.out.println("#" + tc + " " + result );
		}
		
	}

}
