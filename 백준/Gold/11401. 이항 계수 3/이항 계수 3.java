import java.util.*;

public class Main {

	final static long P = 1000000007;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long K = sc.nextLong();
		
		// N!
		long numer = factorial(N);
		
		// K! * (N - K)! mod P
		long denom = factorial(K) * factorial(N - K) % P;
		
		
		System.out.println(numer * pow(denom, P - 2) % P);
	}

	private static long factorial(long N) {
		long fac = 1L;
		
		while(N > 1) {
			fac = (fac * N) % P;
			N--;
		}
		
		return fac;
	}


	//a^b = a^(b/2)^2
	//
	//a * a^(b/2) * a^(b/2) 
	//
	//
	//2^8 = 2^4 * 2^4
	//
	//2^9 = 2^4 * 2^4 * 2
	
	private static long pow(long base, long expo) {
		if(expo == 1) {
			return base % P;
		}
		
		long temp = pow(base, expo / 2);
		
		if(expo % 2 == 1) {
			return (temp * temp % P) * base % P;
		}
		
		return temp * temp % P;
	}
}

