import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int year = 0, e = 1, s = 1, m = 1;
        
        while(true) {
        	year++;
        	if(E == e && S == s && M == m) {
        		System.out.println(year);
        		break;
        	}
        	
        	if(++e > 15) e = 1;
        	if(++s > 28) s = 1;
        	if(++m > 19) m = 1;
        }
    }

}
