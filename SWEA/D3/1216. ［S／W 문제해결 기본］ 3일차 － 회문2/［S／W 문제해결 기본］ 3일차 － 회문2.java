import java.io.*;
import java.util.*;
 
class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        for(int tc=1; tc<=10; tc++) {
            int result = 1;
            char[][] board = new char[100][100];
             
            int test_case = Integer.parseInt(br.readLine());
             
            for(int i=0; i<100; i++) {
                String str = br.readLine();
                for(int j=0; j<100; j++) {
                    board[i][j] = str.charAt(j);                
                }
            }
             
            outerLoop: 
            for(int len = 100; len >= 2; len--) {
                 
                for(int i=0; i<100; i++) {
                    for(int j=0; j<=100-len; j++) {
                         
                        boolean isPalin = true;
                        // 시간복잡도를 위해 긴 회문부터 먼저 탐색
                        for(int k=0; k < len/2; k++) { // 반만 확인하면 되므로 len/2까지
                            if(board[i][j+k] !=
                                    board[i][j+len-1-k]) { // j+len-1-k == 역순으로 k번째
                                isPalin = false;
                                break;
                            }
                        }
                        if(isPalin) {
                            result = len;
                            break outerLoop; // 긴 것부터 찾으므로 더 확인 필요 X
                        }
                    }
                }
                 
                for(int j=0; j<100; j++) {
                    for(int i=0; i<=100-len; i++) {
                        boolean isPalin = true;
                        for(int k=0; k<len/2; k++) {
                            if(board[i+k][j] != board[i+len-1-k][j]) {
                                isPalin = false;
                                break;
                            }
                        }
                        if(isPalin) {
                            result = len;
                            break outerLoop;
                        }
                    }
                }
                 
            }
             
            bw.write("#" + test_case + " " + result + "\n");
        }
 
        bw.flush();
        bw.close();
        br.close();
 
    }
 
}