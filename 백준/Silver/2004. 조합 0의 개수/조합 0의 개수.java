import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    // 2의 개수를 세는 함수
    public static long countTwo(long n) {
        long count = 0;
        for (long i = 2; i <= n; i *= 2) {
            count += n / i;
        }
        return count;
    }
    
    // 5의 개수를 세는 함수
    public static long countFive(long n) {
        long count = 0;
        for (long i = 5; i <= n; i *= 5) {
            count += n / i;
        }
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());
        
        // 2의 개수 계산: n! - (n-m)! - m!
        long two = countTwo(n) - countTwo(n-m) - countTwo(m);
        
        // 5의 개수 계산: n! - (n-m)! - m!
        long five = countFive(n) - countFive(n-m) - countFive(m);
        
        // 2와 5 중 더 작은 수가 10의 개수가 됨
        System.out.println(Math.min(two, five));
    }
}