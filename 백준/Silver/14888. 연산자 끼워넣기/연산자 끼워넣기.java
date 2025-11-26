import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	static int[] nums;
	static int[] op;
	static int[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		op = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}

		selected = new int[N - 1];
		Arrays.fill(selected, -1);

		solve(0);
		
		System.out.println(max);
		System.out.println(min);
	}

	private static void solve(int depth) {
		if(depth == N - 1) {
			// 계산 
			int sum = nums[0];

			for(int i = 0; i < selected.length; i++) {
				int idx = selected[i];
				switch(idx) {
				case 0:
					sum += nums[i + 1];
					break;
				case 1:
					sum -= nums[i + 1];
					break;
				case 2:
					sum *= nums[i + 1];
					break;
				case 3:
					sum /= nums[i + 1];
					break;
				}
			}
			
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			
			return;
		}

		for(int i = 0; i < 4; i++) {
			if(op[i] > 0) {
				int temp = selected[depth];
				selected[depth] = i;
				op[i]--;

				solve(depth + 1);

				selected[depth] = temp;
				op[i]++;
			}
		}
	}
}
