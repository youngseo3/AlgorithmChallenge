import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int L, C;
	static char[] aeiou = {'a', 'e', 'i', 'o', 'u'};
 	static char[] arr;
	static char[] selected;

	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        arr = new char[C];
        selected = new char[L];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
        	arr[i] = st.nextToken().charAt(0);
        }
        
        Arrays.sort(arr);
        
        dfs(0, 0);
        
        System.out.println(sb);
	}

    static void dfs(int start, int depth) {
    	if(depth == L) {
    		if(isCode()) {
    			for(char c: selected) {
        			sb.append(c);
        		}
        		sb.append("\n");
    		}
 
    		return;
    	}
    	
    	for(int i = start; i < C; i++) {
    		selected[depth] = arr[i];
    		dfs(i + 1, depth + 1);
    	}
    }
    
    static boolean isCode() {
    	int aeiouCnt = 0;
    	
    	for(char s: selected) {
    		for(char c: aeiou) {
        		if(c == s) {
        			aeiouCnt++;
        		}
        	}
    	}    	
    	
    	int remainCnt = L - aeiouCnt;
    	
    	if(remainCnt >= 2 && aeiouCnt >= 1) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
