import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		List<Integer> arr = new ArrayList<>();
		int sum = -100;
		boolean tf = false;
		
		for(int i = 0; i < 9; i++) {
			arr.add(Integer.parseInt(br.readLine()));
			sum += arr.get(i);
		}
				
		for(int i = 0; i < 8; i++) {
			if(tf) break;
				
			for(int j = i + 1; j < 9; j++) {
				if(sum == arr.get(i) + arr.get(j)) {	
					arr.remove(i);
					arr.remove(j - 1);

					tf = !tf;
					break;
				}
			}
		}
		
		Collections.sort(arr);
		for(int i = 0; i < 7; i++) {
			System.out.println(arr.get(i));
		}
		
	}
}
