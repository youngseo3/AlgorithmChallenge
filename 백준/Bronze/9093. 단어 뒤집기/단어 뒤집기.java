import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				String s = st.nextToken();
				sb.append(reverse(s) + " ");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static String reverse(String s) {
		String result = "";
		
		for(int i = s.length() - 1; i >= 0; i--) {
			result += s.charAt(i);
		}
		
		return result;
	}

}
