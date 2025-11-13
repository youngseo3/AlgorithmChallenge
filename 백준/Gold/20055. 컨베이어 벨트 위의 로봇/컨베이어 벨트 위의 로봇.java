import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int[] belt; // 내구도
	static List<Integer> robot; // 컨베이어 벨트 위에서의 로봇의 위치
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		belt = new int[2 * N];
		robot = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve());
	}

	private static int solve() {
	    int time = 0;
	    
	    while(true) {
	        time++;
	        
	        rotate();
	        moveRobot();
	        
	        if(belt[0] > 0) {
	        	robot.add(0, 0);
	            belt[0]--;
	        }

	        int zeroCount = 0;
	        for(int a: belt) {
	            if(a == 0) zeroCount++;
	        }
	        
	        if(zeroCount >= K) {
	            break;
	        }
	    }
	    
	    return time;
	}
	
	private static void rotate() {
		int[] copyBelt = Arrays.copyOf(belt, 2 * N);
		int tempBelt = belt[2 * N - 1];
		
		for(int i = 1; i < N * 2; i++) {
			belt[i] = copyBelt[i - 1];
		}
		belt[0] = tempBelt;
		
		for(int i = 0; i < robot.size(); i++) {
			robot.set(i, robot.get(i) + 1);
		}
		
		for(int i = robot.size() - 1; i >= 0; i--) {
		    if(robot.get(i) >= N - 1) {
		        robot.remove(i);
		    }
		}
	}
	
	private static void moveRobot() {
	    for(int i = robot.size() - 1; i >= 0; i--) {
	        int robotIdx = robot.get(i);
	        int nextIdx = robotIdx + 1; // 이동하려는 다음 위치
	        
	        if(isOnRobot(nextIdx) && belt[nextIdx] > 0) {
	            belt[nextIdx]--;
	            
	            if(nextIdx == N - 1) {
	                robot.remove(i);
	            } else {
	                robot.set(i, nextIdx);
	            }
	        }
	    }
	    
	}

	private static boolean isOnRobot(int findIndex) {
		for(int robotIndex: robot) {
			if(robotIndex == findIndex) return false;
		}
		return true;
	}
}
