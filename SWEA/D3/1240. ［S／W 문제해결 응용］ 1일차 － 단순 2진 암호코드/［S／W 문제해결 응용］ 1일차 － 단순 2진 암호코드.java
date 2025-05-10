import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static String[] numbers = {"0001101", "0011001", "0010011", "0111101", "0100011",
			"0110001", "0101111", "0111011", "0110111", "0001011"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		

		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int result = 0;

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
		
			int[] code = new int[8];
			int idx = 7;
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				
				for(int j = M - 1; j >= M - 56 - 1; j--) {
					if(str.charAt(j) == '1') {
						for(int k = j; k >= 0; k -= 7 ) {
							if(idx == -1) {
								break;
							}
							
							int num = findNumbers(str.substring(k - 6, k + 1));
							code[idx--] = num;
						}
					}
				}
				
				int sumOdd = 0;
				int sumEven = 0;
				
				for(int j = 0; j < code.length; j++) {					

					if(j % 2 == 0) {
						sumOdd += code[j];
					} else {
						sumEven += code[j];
					}
				}
				
				sumOdd *= 3;

				if((sumEven + sumOdd) % 10 == 0 && (sumEven + sumOdd) > 0 && result == 0) {
					for(int j = 0; j < code.length; j++) {					
						result += code[j];
					}
				}
			}
			
			sb.append("#" + tc + " ").append(result + "\n");


		}
		
		System.out.println(sb);
	}
	
	static int findNumbers(String num) {
		int result = 0;
		
		for(int i = 0; i < numbers.length; i++) {
			if(num.equals(numbers[i])) {
				result = i;
			}
		}
		
		return result;
	}

}
