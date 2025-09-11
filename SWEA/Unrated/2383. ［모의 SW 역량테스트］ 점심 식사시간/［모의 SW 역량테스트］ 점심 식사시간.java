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

private static int calculateTime(Point[] group, Point ent) {
    if (group.length == 0) {
        return 0;
    }

    // 1. 모든 사람의 도착 시간을 리스트에 담아 정렬합니다. (도착 예정 그룹)
    List<Integer> arrivalTimes = new ArrayList<>();
    for (Point person : group) {
        // 도착하면 1분 대기 후 내려갈 수 있으므로, 실제 계단 이용 가능 시간은 도착시간+1 입니다.
        arrivalTimes.add(getDistance(ent, person) + 1);
    }
    Collections.sort(arrivalTimes);

    int time = 0; // 현재 시간
    int finishedCount = 0; // 완료한 사람 수
    int arrivalIdx = 0; // 도착 예정 그룹에서 다음으로 도착할 사람의 인덱스

    Queue<Integer> waitingQueue = new LinkedList<>(); // 대기 그룹
    Queue<Integer> onStairs = new LinkedList<>(); // 계단 위 그룹 (완료 시간 저장)

    // 모든 사람이 완료할 때까지 시간을 흐르게 합니다.
    while (finishedCount < group.length) {
        time++; // 1분 경과

        // [STEP 1] 시간이 다 돼서 계단에서 내려온 사람 처리 
        // onStairs 큐에는 '완료 시간'이 들어있으므로, 현재 시간과 같은 사람을 모두 내보냅니다.
        while (!onStairs.isEmpty() && onStairs.peek() == time) {
            onStairs.poll();
            finishedCount++;
        }

        // [STEP 2] 계단 입구에 새로 도착한 사람 처리
        // arrivalTimes 리스트에서 현재 시간에 도착한 사람들을 모두 대기 큐로 옮깁니다.
        while (arrivalIdx < arrivalTimes.size() && arrivalTimes.get(arrivalIdx) <= time) {
            waitingQueue.offer(arrivalTimes.get(arrivalIdx));
            arrivalIdx++;
        }

        // [STEP 3] 대기 중인 사람을 계단에 올리기 
        // 계단에 빈자리가 있고(3명 미만), 기다리는 사람이 있다면 계단에 올립니다.
        while (onStairs.size() < 3 && !waitingQueue.isEmpty()) {
            waitingQueue.poll(); // 대기 그룹에서 한 명 나옴
            
            // 현재 시간에 계단을 오르기 시작했으므로, 완료 시간은 (현재시간 + 계단길이) 입니다.
            int finishTime = time + ent.num;
            onStairs.offer(finishTime); // 계단 위 그룹에 완료 시간을 추가
        }
    }

    // 모든 사람이 내려왔을 때의 시간이 최종 소요 시간입니다.
    return time;
}

	private static int getDistance(Point ent, Point sel) {
		return Math.abs(ent.x - sel.x) + Math.abs(ent.y - sel.y);
	}

	static class Point {
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

	}
}
