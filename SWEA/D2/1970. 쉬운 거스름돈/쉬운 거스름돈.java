import java.io.*;

public class Solution {

	public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T;
        T=Integer.parseInt(br.readLine());
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int[] count = new int[8];
            int target = Integer.parseInt(br.readLine());
            int[] money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	
			for(int i=0; i<money.length; i++) {
				if(target>=money[i]) {
					count[i] = target/money[i];
					target %= money[i];
				}
			}
            
            bw.write("#" + test_case + "\n");
            for (int i : count) {
                bw.write(i + " ");
            }
            bw.write("\n");
        }
        br.close();
        bw.close();
    }

}
