import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sum = 0;
		
		while(st.hasMoreTokens()) {
			int a = Integer.parseInt(st.nextToken());
			sum += a;
		}
		System.out.println(sum);
	}
}
