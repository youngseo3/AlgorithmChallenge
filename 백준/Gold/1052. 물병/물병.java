import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int answer = 0;

        if (N <= K) {
            System.out.println(0);
            return;
        }

        while (true) {
            if (Integer.bitCount(N) <= K) {
                break;
            }

            N++;
            answer++;
        }

        System.out.println(answer);
    }
}
