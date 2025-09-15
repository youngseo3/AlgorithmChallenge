import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<String> A;
			Queue<String> B;
			int aSize, bSize = N / 2;
			
			if(N % 2 == 1) {
				aSize = N / 2 + 1;
			}
			else {
				aSize = N / 2;
			}
			A = new ArrayDeque<>();
			B = new ArrayDeque<>();
			
			for(int i = 0; i < aSize; i++) {
				A.add(st.nextToken());
			}
			
			for(int i = 0; i < bSize; i++) {
				B.add(st.nextToken());
			}
			
			sb.append("#" + tc + " ");
			while(!A.isEmpty()) {
				sb.append(A.poll() + " ");
				
				if(!B.isEmpty()) {
					sb.append(B.poll() + " ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
