import java.util.*;
import java.io.*;

// ACM 호텔
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());

            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            System.out.println(calcRoomNum(H, W, N));
        }
    }
    public static String calcRoomNum(int H, int W, int N) {
        int Y = N % H;
        int X = N / H+1;
        if (Y == 0) {
            X --;
            Y = H;
        }
        return String.format("%d%02d", Y, X);
    }
}