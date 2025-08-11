import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. BufferedReader로 입력 준비
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        // 좌표를 저장할 List<List<Long>> 생성
        List<List<Long>> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()); // 각 점의 좌표 라인을 읽음
            List<Long> point = new ArrayList<>();
            point.add(Long.parseLong(st.nextToken())); // x 좌표
            point.add(Long.parseLong(st.nextToken())); // y 좌표
            points.add(point);
        }

        int maxCount = 0;

        // 2. 모든 점의 x좌표를 사각형의 왼쪽 변으로 설정
        for (List<Long> p1 : points) {
            long leftX = p1.get(0);

            // 3. 모든 점의 y좌표를 사각형의 아래쪽 변으로 설정
            for (List<Long> p2 : points) {
                long bottomY = p2.get(1);

                // 4. 현재 사각형의 범위 정의
                long rightX = leftX + A;
                long topY = bottomY + B;

                int currentCount = 0;

                // 5. 모든 점을 순회하며 현재 사각형에 포함되는지 확인
                for (List<Long> pCheck : points) {
                    long checkX = pCheck.get(0);
                    long checkY = pCheck.get(1);

                    // 점이 사각형의 경계를 포함하여 내부에 있는지 확인
                    if (checkX >= leftX && checkX <= rightX &&
                        checkY >= bottomY && checkY <= topY) {
                        currentCount++;
                    }
                }

                // 6. 최댓값 갱신
                if (currentCount > maxCount) {
                    maxCount = currentCount;
                }
            }
        }

        // 7. 최종 결과 출력
        System.out.println(maxCount);
        br.close();
    }
}