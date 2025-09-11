import java.io.*;
import java.util.*;

public class Solution {

	static int N, minTotalTime;
	static Point[] sel1;
	static Point[] sel2;
	static int[][] map;
	static List<Point> humans;
	static List<Point> entrance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			minTotalTime = Integer.MAX_VALUE;
			map = new int[N][N];
			humans = new ArrayList<>();
			entrance = new ArrayList<>();

			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if(map[i][j] == 1) {
						humans.add(new Point(i, j, humans.size() + 1));
						map[i][j] = humans.size();
					}
					else if(map[i][j] > 1) {
						entrance.add(new Point(i, j, map[i][j]));
					}
				}
			}

			for(int i = 0; i <= humans.size(); i++) {
				sel1 = new Point[i];
				sel2 = new Point[humans.size() - i];
				solve(0, i, 0);
			}

			sb.append("#").append(tc).append(" ").append(minTotalTime).append("\n");
		}

		System.out.print(sb);
	}

	private static void solve(int depth, int maxDepth, int start) {
		if(depth == maxDepth ) {
			boolean[] v = new boolean[humans.size()];

			for(Point p1: sel1) {
				for(Point p2: humans) {
					if(p1.num == p2.num) {
						v[p2.num - 1] = true;
						continue;
					}
				}
			}

			int idx = 0;
			for(Point p2: humans) {
				if(!v[p2.num - 1]) {
					sel2[idx++] = new Point(p2.x, p2.y, p2.num);
				}
			}

			//			for(int i = 0; i < sel1.length; i++) {
			//				System.out.print(sel1[i].num + " ");
			//			}
			//			System.out.println();

			getMinTime();

			return;
		}

		for(int i = start; i < humans.size(); i++) {
			sel1[depth] = humans.get(i);
			solve(depth + 1, maxDepth, i + 1);
		}

	}

	private static void getMinTime() {
		Point ent1 = entrance.get(0);
		Point ent2 = entrance.get(1);

		// 1번 계단으로 가는 그룹(sel1)의 소요 시간 계산
		int time1 = calculateTime(sel1, ent1);
		// 2번 계단으로 가는 그룹(sel2)의 소요 시간 계산
		int time2 = calculateTime(sel2, ent2);

		// 두 그룹의 시뮬레이션은 동시에 진행되므로, 둘 중 더 오래 걸리는 시간이 현재 조합의 최종 시간입니다.
		int currentTime = Math.max(time1, time2);

		// 지금까지의 최솟값과 비교하여 갱신합니다.
		minTotalTime = Math.min(minTotalTime, currentTime);
	}

	// 특정 그룹이 특정 계단을 내려가는 시간을 시뮬레이션하는 헬퍼 함수
	private static int calculateTime(Point[] group, Point ent) {
		// 해당 그룹에 아무도 없으면 0을 반환합니다.
		if (group.length == 0) {
			return 0;
		}

		// 계단 입구까지의 도착 시간을 기준으로 오름차순 정렬하는 우선순위 큐
		PriorityQueue<Integer> arrivalPQ = new PriorityQueue<>();
		for (Point person : group) {
			arrivalPQ.offer(getDistance(ent, person));
		}

		// 계단의 3개 자리가 각각 언제 비는지를 저장하는 배열
		int[] stairStatus = new int[3];
		int stairLength = ent.num; // 계단의 길이
		int lastFinishTime = 0; // 이 그룹에서 가장 마지막으로 내려간 사람의 완료 시간

		while (!arrivalPQ.isEmpty()) {
			int arrivalTime = arrivalPQ.poll();

			// 3개의 자리 중 가장 빨리 비는 자리를 찾습니다.
			// 매번 정렬하면 항상 0번 인덱스가 가장 빠른 시간이 됩니다.
			Arrays.sort(stairStatus);
			int earliestFreeTime = stairStatus[0];

			// 사람이 계단을 내려가기 시작하는 시간
			// (도착시간+1)과 (가장 빨리 비는 시간) 중 더 늦은 시간이어야 합니다.
			int startTime = Math.max(arrivalTime + 1, earliestFreeTime);

			// 이 사람이 계단을 다 내려가는 시간
			int finishTime = startTime + stairLength;

			// 이 사람이 사용한 자리를 새로운 완료 시간으로 갱신합니다.
			stairStatus[0] = finishTime;

			// 그룹의 최종 완료 시간은 모든 사람의 완료 시간 중 가장 늦은 시간입니다.
			lastFinishTime = finishTime; 
		}

		return lastFinishTime;
	}

	private static int getDistance(Point ent, Point sel) {
		return Math.abs(ent.x - sel.x) + Math.abs(ent.y - sel.y);
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;
		int num;
		int distance;

		public Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.distance = 0;
		}

		public Point(int x, int y, int num, int distance) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.distance = distance;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
}
