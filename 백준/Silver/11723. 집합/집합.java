import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static boolean[] S = new boolean[21];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        
        while(M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            
            if (command.equals("all")) {
                for(int i = 1; i <= 20; i++) {
                    S[i] = true;
                }
            } else if (command.equals("empty")) {
                for(int i = 1; i <= 20; i++) {
                    S[i] = false;
                }
            } else {
                int num = Integer.parseInt(st.nextToken());
                
                switch(command) {
                    case "add":
                        add(num);
                        break;
                    case "remove":
                        remove(num);
                        break;
                    case "check":
                        sb.append(check(num) ? 1 : 0).append('\n');
                        break;
                    case "toggle":
                        toggle(num);
                        break;
                }
            }
        }
        
        System.out.print(sb);
    }
    
    static boolean check(int x) {
        return S[x];
    }
    
    static void add(int x) {
        S[x] = true;
    }
    
    static void remove(int x) {
        S[x] = false;
    }
    
    static void toggle(int x) {
        S[x] = !S[x];
    }
}