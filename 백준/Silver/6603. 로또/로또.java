import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int[] arr;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            
            if(k == 0) {
                break;
            }
            
            arr = new int[k];
            selected = new int[6];
            
            for(int j = 0; j < k; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            
            dfs(0, 0);
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    static void dfs(int start, int depth) {
        // 6개의 수를 모두 선택했을 때
        if(depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        // 남은 숫자들 중에서 선택
        for (int i = start; i < k; i++) {
            selected[depth] = arr[i];
            dfs(i + 1, depth + 1);
        }
    }
}
