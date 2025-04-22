import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

	static int K;
	static char arr[];
	static boolean visited[] = new boolean[10];
	static ArrayList<String> result = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        arr = new char[K];
        
        for(int i = 0; i < K; i++) {
        	arr[i] = st.nextToken().charAt(0);
        }
        
        for(int i = 0; i < 10; i++) {
        	visited[i] = true;
        	dfs(i, 0, i+"");
        	visited[i] = false;
        }
        
        System.out.println(result.get(result.size() - 1));
        System.out.println(result.get(0));
	}
    
    static void dfs(int now, int depth, String number) {
    	if(depth == K) {
    		result.add(number);
    		return;
    	}
    	
    	for(int next = 0; next < 10; next++) {
    		if(!visited[next]) {
    			if((arr[depth] == '<' && now < next)
    					|| (arr[depth] == '>' && now > next)) {
    				visited[next] = true;
    				dfs(next, depth + 1, number + next);
    				visited[next] = false;
    			}
    		}
    	}
    }

}
