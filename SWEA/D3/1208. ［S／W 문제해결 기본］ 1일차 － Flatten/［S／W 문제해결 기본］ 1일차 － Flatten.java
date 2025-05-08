import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    private static final int MAX_HEIGHT = 100; // 상자 높이의 최대값
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int dumpCount = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 높이별 상자 개수를 저장하는 배열
            int[] heightCount = new int[MAX_HEIGHT + 1];
            
            // 입력 받으면서 높이별 상자 개수 계산
            for (int i = 0; i < 100; i++) {
                int h = Integer.parseInt(st.nextToken());
                heightCount[h]++;
            }
            
            // 평탄화 작업 수행
            int result = flattenOptimized(heightCount, dumpCount);
            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static int flattenOptimized(int[] heightCount, int dumpCount) {
        int minHeight = 1;
        int maxHeight = 100;
        
        // 실제 최소/최대 높이 찾기
        while (heightCount[minHeight] == 0) minHeight++;
        while (heightCount[maxHeight] == 0) maxHeight--;
        
        // 평탄화 작업
        for (int i = 0; i < dumpCount; i++) {
            // 이미 평탄화가 완료되었다면 중단
            if (maxHeight - minHeight <= 1) break;
            
            // 최고 높이 감소, 최저 높이 증가
            heightCount[maxHeight]--;
            heightCount[maxHeight - 1]++;
            heightCount[minHeight]--;
            heightCount[minHeight + 1]++;
            
            // 최고/최저 높이 갱신
            if (heightCount[maxHeight] == 0) maxHeight--;
            if (heightCount[minHeight] == 0) minHeight++;
        }
        
        // 최종 높이 차이 계산
        while (heightCount[minHeight] == 0) minHeight++;
        while (heightCount[maxHeight] == 0) maxHeight--;
        
        return maxHeight - minHeight;
    }
}