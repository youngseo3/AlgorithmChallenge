import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int[] ary = new int[size];
            long sum = 0;
            
            for(int i = 0; i < size; i++) {
                ary[i] = Integer.parseInt(st.nextToken());
            }
            
            for(int i = 0; i < size - 1; i++) {
                for(int j = i + 1; j < size; j++) {
                    sum += getGCD(ary[i], ary[j]);
                }
            }
            
            sb.append(sum).append("\n");
        }
        
        System.out.println(sb);
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
