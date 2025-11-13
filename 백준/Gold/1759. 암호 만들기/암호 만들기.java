import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static char[] pwd;
	static char[] selected;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		pwd = new char[C];
		selected = new char[L];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < C; i++) {
			pwd[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(pwd);
		
		solve(0, 0);
		System.out.println(sb);
	}

	private static void solve(int depth, int start) {
		if(depth == L) {
			if(isValid()) {
				for(int i = 0; i < selected.length; i++) {
					sb.append(selected[i]);
				}
				sb.append("\n");
			}
			
			return;
		}
		
		for(int i = start; i < C; i++) {
			selected[depth] = pwd[i];
			solve(depth + 1, i + 1);
		}
	}
	
	private static boolean isValid() {
		int vowelCount = 0; // 모음 개수
		int consonantCount = 0; // 자음 개수
		
		for(char c : selected) {
			if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				vowelCount++;
			} else {
				consonantCount++;
			}
		}
		
		// "최소 한 개의 모음"과 "최소 두 개의 자음" 조건을 만족하는지 반환
		return vowelCount >= 1 && consonantCount >= 2;
	}
}
