import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int divCnt = calculateDivCnt(N);
        int result = 0;
        int numCnt = 9;
        int S = 0;
        
        for(int i = 1; i <= divCnt; i++) {
        	result += numCnt * i;
        	S += numCnt;
        	numCnt *= 10;
        }
        
        if(N - S > 0) {
        	result += (N - S) * (divCnt + 1);
        }
        
        System.out.println(result);
	}
	
	static int calculateDivCnt(int N) {
        int divCnt = 0;

		while(N / 10 > 0) {
        	N /= 10;
        	divCnt++;
        }
		
		return divCnt;
	}

}
