import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine().trim());
        
        for (int tc = 1; tc <= T; tc++) {
            String str = br.readLine().trim();
            
            // 마디 길이 찾기
            int madiLength = findPatternLength(str);
            
            // 결과 출력 형식에 맞게 저장
            sb.append("#").append(tc).append(" ").append(madiLength).append("\n");
        }
        
        System.out.println(sb);
    }
    
    // 패턴의 길이를 찾는 함수
    private static int findPatternLength(String str) {
        // 마디의 최대 길이는 10이므로 1부터 10까지 확인
        for (int len = 1; len <= 10; len++) {
            String pattern = str.substring(0, len);
            boolean isPattern = true;
            
            // 패턴 길이 단위로 반복되는지 확인
            for (int i = len; i + len <= str.length(); i += len) {
                if (!pattern.equals(str.substring(i, i + len))) {
                    isPattern = false;
                    break;
                }
            }
            
            if (isPattern) {
                return len;
            }
        }
        
        return str.length(); // 패턴을 찾지 못한 경우 전체 길이 반환
    }
}