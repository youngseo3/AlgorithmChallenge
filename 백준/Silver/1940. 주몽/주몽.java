import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long M = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A[] = new long[N];
        for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		int i = 0;
		int j = N-1;
		int count = 0;
		while(i < j) {
			if(A[i] + A[j] == M) {
				j--;
				i++;
				count ++;
			}
			else if(A[i] + A[j] < M) {
				i++;
			}
			else if(A[i] + A[j] > M) {
				j--;
			}
		}
		System.out.println(count);
	}

}