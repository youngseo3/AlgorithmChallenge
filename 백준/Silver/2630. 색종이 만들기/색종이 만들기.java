import java.io.*;
import java.util.*;

public class Main {

	static int white, blue;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		partition(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	}

	private static void partition(int row, int col, int size) {
		if(check(row, col, size)) {
			if(map[row][col] == 0) {
				white++;
			}
			else {
				blue++;
			}
			return;
		}
		
		int newSize = size / 2;
		
		partition(row, col, newSize); // 2사분면
		partition(row, col + newSize, newSize); // 1사분면
		partition(row + newSize, col, newSize); // 3사분면
		partition(row + newSize, col + newSize, newSize); // 4사분면
	}

	private static boolean check(int row, int col, int size) {
		int color = map[row][col];
		
		for(int i = row; i < row + size; i++) {
			for(int j = col; j < col + size; j++) {
				// 색상이 같이 않다면 false를 리턴 
				if(map[i][j] != color) {
					return false;
				}
			}
		}
		
		return true;
	}

}
