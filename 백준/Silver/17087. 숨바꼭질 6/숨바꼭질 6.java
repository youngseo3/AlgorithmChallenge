import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int min = Integer.MAX_VALUE;
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] ary = new int[N];
        
        for(int i = 0; i < N; i++) {
        	ary[i] = Math.abs(S - Integer.parseInt(st.nextToken()));
        }
        
        int gcd = ary[0];
        for(int i = 1; i < N; i++) {
    		gcd = getGCD(gcd, ary[i]);
    	}
        
        System.out.println(gcd);
	}
	
	static int getGCD(int a, int b) {
		while(b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

}
