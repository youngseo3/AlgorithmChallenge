import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		LinkedList<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		while(!queue.isEmpty()) {
			for (int i = 0; i < K; i++) {
                if (i == K - 1) { // 1번 사람 부터 시작하기 때문에 k-1 과 매치한다.
                    if (queue.size() == 1) { // 마지막 인원은 쉼표를 제외하고 넣어준다.
                        sb.append(queue.remove());
                    } else {
                        sb.append(queue.remove() + ", ");
                    }
                } else {
                	queue.add(queue.remove());
                }
            }
		}
		sb.append(">");
		System.out.println(sb);
	}

}
