
import java.io.*;
import java.util.*;


public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine()); //카드의 개수
			String[] cards = new String[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				cards[i] = st.nextToken();
			}
			int mid = 0;
			if(N%2 == 0) {
				mid = N/2-1;
			}
			else {
				mid = N/2 ;
			}
			String[] arr1 = new String[mid+1];
			String[] arr2 = new String[N-mid+1];
			for(int i = 0; i<N; i++) {
				if(i<=mid) {
					arr1[i] = cards[i];
				}
				else {
					arr2[i-(mid+1)] = cards[i];
				}
			}
			for(int i = 0; i<N; i++) {
				if(i%2 == 0) {
					cards[i] = arr1[i/2];
				} 
				else {
					cards[i] = arr2[i/2];
				}
			}
			System.out.print("#"+t+" ");
			for(int i = 0; i<N; i++) {
				System.out.print(cards[i]+" ");
			}
			System.out.println();
		}
	}

}
