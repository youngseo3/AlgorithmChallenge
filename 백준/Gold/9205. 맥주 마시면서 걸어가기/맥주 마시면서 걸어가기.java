import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	// 집, 편의점, 페스티벌 모든 장소를 저장할 리스트
	static ArrayList<Point> locations; 
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			n = Integer.parseInt(br.readLine()); // 편의점 개수
			locations = new ArrayList<>();
			
			// 1. 집, 편의점, 페스티벌을 모두 리스트에 추가 (총 n+2개)
			
			// 집 (인덱스 0)
			StringTokenizer st = new StringTokenizer(br.readLine());
			locations.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
			// 편의점 (인덱스 1 ~ n)
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				locations.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			// 페스티벌 (인덱스 n+1)
			st = new StringTokenizer(br.readLine());
			locations.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			
			// BFS 실행
			bfs();
		}
		
		System.out.println(sb);
	}
	
	private static void bfs() {
		// Queue에는 Point 객체 대신 locations 리스트의 '인덱스'를 저장합니다.
		Queue<Integer> q = new ArrayDeque<>();
		// locations 리스트의 크기(n+2)만큼 visited 배열 생성
		boolean[] visited = new boolean[n + 2]; 
		
		q.offer(0); // 시작점: 집 (인덱스 0)
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int currentIndex = q.poll();
			Point currentPoint = locations.get(currentIndex);
			
			// 2. 현재 위치가 페스티벌 장소(인덱스 n+1)인지 확인
			if(currentIndex == n + 1) {
				sb.append("happy\n");
				return; // 목적지 도달!
			}
			
			// 3. 현재 위치에서 다른 모든 위치(집, 편의점, 페스티벌)를 확인
			for(int nextIndex = 0; nextIndex < n + 2; nextIndex++) {
				
				// 아직 방문하지 않았다면
				if(!visited[nextIndex]) {
					Point nextPoint = locations.get(nextIndex);
					
					// 4. 맨해튼 거리 계산
					int distance = Math.abs(currentPoint.x - nextPoint.x) + 
					             Math.abs(currentPoint.y - nextPoint.y);
					
					// 5. 거리가 1000m(맥주 20병 * 50m) 이내인지 확인
					if(distance <= 1000) {
						visited[nextIndex] = true; // 방문 처리
						q.offer(nextIndex); // 큐에 다음 목적지 인덱스 추가
					}
				}
			}
		}
		
		// 6. 큐가 비었는데 페스티벌에 도달 못 한 경우
		sb.append("sad\n");
	}

	// Point 클래스 (Comparable 불필요)
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}