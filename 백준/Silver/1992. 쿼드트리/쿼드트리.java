import java.io.*;
import java.util.*;

public class Main {

	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		recursive(0, 0, N);
		
		System.out.println(sb);
	}

	private static void recursive(int x, int y, int size) {
		if(check(x, y, size)) {
			if(map[x][y] == 1) {
				sb.append(1);
			}
			else {
				sb.append(0);
			}
			return;
		}
		
		sb.append("(");
		int newSize = size / 2;
		
		recursive(x, y, newSize);
		recursive(x, y + newSize, newSize);
		recursive(x + newSize, y, newSize);
		recursive(x + newSize, y + newSize, newSize);
		
		sb.append(")");
	}

	private static boolean check(int x, int y, int size) {
		int init = map[x][y];
		
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(map[i][j] != init) {
					return false;
				}
			}
		}
		
		return true;
	}
}
