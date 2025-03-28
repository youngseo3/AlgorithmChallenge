import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        long result = 0;
        int digit = 1;   // 자릿수
        int start = 1;   // 자릿수가 바뀌는 시작 숫자
        
        while (start <= N) {
            // 현재 자릿수의 마지막 숫자 계산 (N보다 작거나 같은 값)
            int end = Math.min(N, start * 10 - 1);
            
            // 현재 자릿수에 해당하는 숫자의 개수 * 자릿수를 결과에 더함
            result += (long) (end - start + 1) * digit;
            
            start *= 10;  // 다음 자릿수의 시작 숫자로 업데이트
            digit++;      // 자릿수 증가
        }
        
        System.out.println(result);
    }
}
