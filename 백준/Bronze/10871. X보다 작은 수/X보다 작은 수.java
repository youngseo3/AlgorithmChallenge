import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 int n = Integer.parseInt(st.nextToken());
		 int big = Integer.parseInt(st.nextToken());
		 
		 StringTokenizer st2 = new StringTokenizer(br.readLine());
		 while(st2.hasMoreTokens()) {
			 int i = Integer.parseInt(st2.nextToken());
			 if(big > i) bw.write(String.valueOf(i)+" ");
		 }
		 bw.flush();
		 bw.close();
		 br.close();
	}

}
