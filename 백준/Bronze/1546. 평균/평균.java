import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		double m = 0, sum=0;
		double ary [] = new double[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			ary[i] = Double.parseDouble(st.nextToken());
			if(m<ary[i]) m = ary[i];
		}
		
		for(int j=0; j<n; j++) {
			sum += ary[j]*100.0/m;
		}
		
		bw.write(String.valueOf(sum/n));
		bw.flush();
		bw.close();
		br.close();
	}

}
