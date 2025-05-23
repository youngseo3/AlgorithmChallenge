import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			boolean[] space = new boolean[n + 1]; // n개의 주차공간이 비어있는지 
			int[] location = new int[m + 1]; // m개의 차량이 몇 번 주차공간에 주차했는지 
			int[] R = new int[n + 1]; // i번째 주차 공간의 단위 무게당 요금 
			int[] W = new int[m + 1]; // 차량 i의 무게, 차량 번호 i와 도착 순서는 관계 X
			int cost = 0;
			
			for(int i = 1; i <= n; i++) {
				R[i] = Integer.parseInt(br.readLine());
			}
			
			for(int i = 1; i <= m; i++) {
				W[i] = Integer.parseInt(br.readLine());
			}
			
			// 2m 개의 줄에 차량들의 주차장 출입 순서가 하나의 정수 x 로 주어진다.
			// x > 0 차가 들어옴, x < 0 차가 나감
			int[] order = new int[2 * m + 1];			
			for(int i = 1; i <= 2 * m; i++) {
				order[i] = Integer.parseInt(br.readLine());
			}
			
			List<Integer> list = new ArrayList<>();
			
			for(int i = 1; i <= 2 * m; i++) {
				if(order[i] > 0) {
					int cnt = 0;
					for(int j = 1; j <= n; j++) {
						if(space[j]) {
							cnt++;
						}
					}
					
					if(cnt == space.length - 1) {
						list.add(order[i]);
					}
					
					for(int j = 1; j <= n; j++) {
						if(!space[j]) {
							cost += W[order[i]] * R[j];
							location[order[i]] = j;
							space[j] = true;
							break;
						}
					}			
				} else {
					int temp = -order[i];			
					space[location[temp]] = false;
					
					if(!list.isEmpty()) {
						int wait = list.remove(0);
						cost += W[wait] * R[location[temp]];
						location[wait] = location[temp];
						space[location[temp]] = true;
					}
				}
			}
			
			sb.append("#" + t + " ").append(cost + "\n");
		}
		
		System.out.println(sb);
	}

}
