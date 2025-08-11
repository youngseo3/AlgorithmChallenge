import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] population;
	static List<List<Integer>> integers;
	static int max = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		population = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			int people = Integer.parseInt(st.nextToken());
			population[i] = people;
		}
		
		integers = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			integers.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int area = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j < area; j++) {
				int temp = Integer.parseInt(st.nextToken());
				integers.get(i).add(temp);
			}
		}
		
		comb(1, new ArrayList<>());
		
		System.out.println(max == Integer.MAX_VALUE ? -1 : max);
	}

	private static void comb(int depth, List<Integer> sub) {
		if(depth > N) {
			if(sub.size() == 0 || sub.size() == N) {
				return;
			}
			
			// 두 그룹으로 나누어 연결성 확인
			checkConnection(sub);
			return;
		}
		
		// 현재 구역을 포함하지 않는 경우
		comb(depth + 1, sub);
		
		// 현재 구역을 포함하는 경우
		sub.add(depth);
		comb(depth + 1, sub);
		sub.remove(sub.size() - 1);
	}

	private static void checkConnection(List<Integer> sub1) {
		List<Integer> sub2 = new ArrayList<>();
		
		for(int i = 1; i <= N; i++) {
			if(!sub1.contains(i)) {
				sub2.add(i);
			}
		}
		
		if(isConnected(sub1) && isConnected(sub2)) {
			int sum1 = 0;
			int sum2 = 0;
			
			// Position 배열을 사용해서 인구수 계산
            for(int node : sub1) {
                sum1 += population[node];
            }
            
            for(int node : sub2) {
                sum2 += population[node];
            }
            
            int diff = Math.abs(sum1 - sum2);
            max = Math.min(max, diff);
		}
		
	}

	private static boolean isConnected(List<Integer> sub) {
		if(sub.isEmpty()) return false;
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		boolean[] inSub = new boolean[N + 1];
		
		for(int node: sub) {
			inSub[node] = true;
		}
		
		q.offer(sub.get(0));
		visited[sub.get(0)] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: integers.get(current)) {
				if(inSub[next] && !visited[next]) {
					visited[next] = true;
					q.offer(next);
					cnt++;
				}
			}
		}
		
		return cnt == sub.size();
	}

}
