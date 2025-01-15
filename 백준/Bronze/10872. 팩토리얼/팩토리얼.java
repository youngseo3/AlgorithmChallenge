import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        System.out.println(factorial(n));
	}
	
	static int factorial(int n) {
		if(n == 0) return 1;
		return n * factorial(n - 1);
	}

}
