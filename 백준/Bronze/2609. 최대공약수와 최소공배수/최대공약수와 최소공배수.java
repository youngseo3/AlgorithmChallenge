import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int gcd = getGCD(a, b);

		System.out.println(gcd);
		System.out.println(getLCM(a * b, gcd));

	}

	static int getGCD(int a, int b) {
		if (a == 0)
			return b;

		return getGCD(b % a, a);
	}

	static int getLCM(int m, int gcd) {
		return m / gcd;
	}

}
