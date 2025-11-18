import java.io.*;
import java.util.*;

public class Main {
	static int N, M, min = Integer.MAX_VALUE;
	static int[][] city;
	static int[] selected;
	static List<Chicken> chickens;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		city = new int[N][N];
		selected = new int[M];
		chickens = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j] == 2) {
					chickens.add(new Chicken(i, j));
				}
			}
		}
		
		backTrack(0, 0);
		System.out.println(min);
	}
	
	static void backTrack(int depth, int start) {
		if(depth == M) {
			min = Math.min(calculateAllChicken(), min);
			return;
		}
		
		for(int i = start; i < chickens.size(); i++) {
			selected[depth] = i;
			backTrack(depth + 1, i + 1);
		}
	}
	
	static int calculateAllChicken() {
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int dist = Integer.MAX_VALUE;
				
				if(city[i][j] == 1) {
					for(int sel: selected) {
						Chicken c = chickens.get(sel);
						dist = Math.min(dist, calculateDistance(c.x, c.y, i, j));
					}
					
					sum += dist;
				}
			}
		}
		
		return sum;
	}
	
	static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	static class Chicken {
		int x;
		int y;
		
		Chicken(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
