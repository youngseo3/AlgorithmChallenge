import java.io.*;
import java.util.*;

public class Main {
    static String expression;
    static int N;
    static int maxResult = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expression = br.readLine();
        
        // 첫 번째 숫자부터 시작
        dfs(0, expression.charAt(0) - '0');
        
        System.out.println(maxResult);
    }
    
    // pos: 현재 위치, current: 현재까지의 계산 결과
    static void dfs(int pos, int current) {
        // 마지막 숫자까지 처리했으면 결과 갱신
        if (pos >= N - 1) {
            maxResult = Math.max(maxResult, current);
            return;
        }
        
        char operator = expression.charAt(pos + 1);
        int nextNum = expression.charAt(pos + 2) - '0';
        
        // 1. 괄호를 추가하지 않는 경우
        int result1 = calculate(current, operator, nextNum);
        dfs(pos + 2, result1);
        
        // 2. 다음 연산에 괄호를 추가하는 경우
        if (pos + 4 < N) {
            char nextOperator = expression.charAt(pos + 3);
            int nextNextNum = expression.charAt(pos + 4) - '0';
            
            // 괄호 안의 연산을 먼저 수행
            int bracketResult = calculate(nextNum, nextOperator, nextNextNum);
            
            // 현재 결과와 괄호 결과를 연산
            int result2 = calculate(current, operator, bracketResult);
            
            // 괄호를 추가했으므로 4칸 이동
            dfs(pos + 4, result2);
        }
    }
    
    // 두 수와 연산자로 계산
    static int calculate(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }
}