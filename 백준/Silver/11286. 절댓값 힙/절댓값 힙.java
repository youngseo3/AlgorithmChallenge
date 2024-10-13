import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main { // 우선순위 큐 사용 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> PQ = new PriorityQueue<>((o1, o2) -> {
			int first_abs = Math.abs(o1);
			int second_abs = Math.abs(o2);
			if(first_abs == second_abs) {
				return o1 > o2 ? 1 : -1;
			}
			else
				return first_abs - second_abs;
		});
		int A[] = new int[N];
		for(int i=0; i<N; i++) {
			int r = Integer.parseInt(br.readLine());
			if(r == 0) {
				if(PQ.isEmpty()) {
					System.out.println(0);
				}
				else
					System.out.println(PQ.poll());
			}
			else {
				PQ.add(r);
			}
		}
		
	}

}
