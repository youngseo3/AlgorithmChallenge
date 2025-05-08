import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= 10; tc++) {
            int dumpCount = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] height = new int[100];
            for (int i = 0; i < 100; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }

            // 평탄화 작업 수행
            int result = flatten(height, dumpCount);
            
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }

    private static int flatten(int[] height, int dumpCount) {
        for (int i = 0; i < dumpCount; i++) {
            // 최대값과 최소값의 인덱스 찾기
            int maxIdx = findMaxIndex(height);
            int minIdx = findMinIndex(height);
            
            // 이미 평탄화가 완료되었다면 중단
            if (height[maxIdx] - height[minIdx] <= 1) {
                break;
            }
            
            // 덤프 수행
            height[maxIdx]--;
            height[minIdx]++;
        }
        
        // 최종 높이 차이 계산
        int maxIdx = findMaxIndex(height);
        int minIdx = findMinIndex(height);
        
        return height[maxIdx] - height[minIdx];
    }
    
    private static int findMaxIndex(int[] arr) {
        int maxIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    private static int findMinIndex(int[] arr) {
        int minIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx;
    }
}