import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] sub = new int[N - 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());
			arr[i] = value;
		}
		Arrays.sort(arr);
		
		for(int i = 0; i < N - 1; i++) {
			sub[i] = arr[i + 1] - arr[i];
		}
		Arrays.sort(sub);
		
		int sum = 0;
		for(int i = 0; i < N - 1 - (K - 1); i++) {
			sum += sub[i];
		}
		
		System.out.println(sum);
	}

}
