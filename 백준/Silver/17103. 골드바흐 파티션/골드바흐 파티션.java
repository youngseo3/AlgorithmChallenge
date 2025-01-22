import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static final int MAX_NUM = 1000001;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        boolean[] isPrime = new boolean[MAX_NUM];
        // true는 소수, false는 소수 X
        for(int i = 2; i < MAX_NUM; i++) {
        	isPrime[i] = true;
        }
        
        for(int i = 2; i < Math.sqrt(MAX_NUM); i++) {
        	if(isPrime[i]) {
            	for(int j = i * i; j < MAX_NUM; j += i) {
            		isPrime[j] = false;
            	}
        	}
        }
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
        	int n = Integer.parseInt(br.readLine());
        	int count = 0;
        	
        	for(int i = 2; i <= n / 2; i++) {
        		if(isPrime[i] && isPrime[n - i]) {
        			count++;
            	}
        	}
        	
        	sb.append(count + "\n");
        }
        
        System.out.println(sb);
	}

}
