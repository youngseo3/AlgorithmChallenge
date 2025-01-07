import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		
        int idx = 0;
		Stack<Character> stack = new Stack<>();
		
        while(idx<s.length()){
            //< tag가 나오면 > 나올 때까지 sb에 이동
            if(s.charAt(idx)=='<'){
                while(true){
                    sb.append(s.charAt(idx++));
                    if(s.charAt(idx)=='>'){
                        sb.append(s.charAt(idx));
                        idx++;
                        break;
                    }
                }
            }
            else if(s.charAt(idx)==' ') {
                sb.append(' ');
                idx++;
            }
            else{
                //단어가 나오면 뒤집기
                while(idx<s.length() && s.charAt(idx)!=' ' && s.charAt(idx)!='<'){
                    stack.push(s.charAt(idx++));
                }
                
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
            }
        }
		
		
		System.out.println(sb);
	}
}
