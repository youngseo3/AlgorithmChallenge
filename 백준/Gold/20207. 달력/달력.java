import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int count[] = new int[366];
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			for(int j = start; j <= end; j++) {
				count[j]++;
			}
		}
		
		int height = 1;
		int width = 0;
		int sum = 0;
		for(int i = 1; i <= 365; i++) {
			if(count[i] != 0) {
				height = Math.max(height, count[i]);
				width++;
			} else {
				sum += width * height;
				width = 0;
				height = 1;
			}
		}
		
		sum += width * height;
		
		System.out.println(sum);
	}

}
