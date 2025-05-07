import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		
		for(int tc = 1; tc <= T; tc++) {
			int distance = 0;
			int speed = 0;
			int N = sc.nextInt();
			
			while(N-- > 0) {
				int command = sc.nextInt();
				if(command == 1) {
					speed += sc.nextInt();
				} else if(command == 2) {
					speed -= sc.nextInt();
				}
                
				if(speed < 0) {
					speed = 0;
				}
				distance += speed;
			}
			
			sb.append("#" + tc + " ").append(distance + "\n");
		}
		
		System.out.println(sb);
	}
}
