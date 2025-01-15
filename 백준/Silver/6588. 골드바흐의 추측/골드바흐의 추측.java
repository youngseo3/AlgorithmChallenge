import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        boolean[] isPrime = new boolean[1000001];
        
        // true는 소수 X, false는 소수
        isPrime[0] = isPrime[1] = true;
        
        for(int i = 2; i < Math.sqrt(isPrime.length); i++) {
        	if(!isPrime[i]) {
            	for(int j = i * i; j < isPrime.length; j += i) {
            		isPrime[j] = true;
            	}
        	}
        }
        
        while(true) {
            int n = Integer.parseInt(br.readLine());
        	boolean ok = false;

            if(n == 0) {
        		break;
        	}
            for(int i = 3; i <= n / 2; i += 2) {
        		if(!isPrime[i] && !isPrime[n - i]) {
        			sb.append(n + " = " + i + " + " + (n-i) + "\n");
        			ok = true;
        			break;
        		}
        	}
            
            if(!ok)
        		sb.append("Goldbach's conjecture is wrong.\n");
        }
        
        System.out.println(sb);
	}

}
