import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {
    static ArrayDeque<Integer> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			switch(st.nextToken()) {
				case "push":
					push(Integer.parseInt(st.nextToken()));
					break;
				case "pop":
					sb.append(pop() + "\n");
					break;
				case "size":
					sb.append(size() + "\n");
					break;
				case "empty":
					sb.append(empty() + "\n");
					break;
				case "front":
					sb.append(front() + "\n");
					break;
				case "back":
					sb.append(back() + "\n");
					break;
			}
		}
		System.out.println(sb);
	}
	
	static void push(int x) {
        queue.addLast(x);  // O(1)
	}
	
	static int pop() {
		if(queue.isEmpty()) {
			return -1;
		}
		
        return queue.removeFirst();  // O(1)
	}

	static int size() {
		return queue.size();
	}

	static int empty() {
		if(queue.isEmpty()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	static int front() {
		if(queue.isEmpty()) {
			return -1;
		}
		
        return queue.getFirst();  // O(1)
	}

	static int back() {
		if(queue.isEmpty()) {
			return -1;
		}
		
		return queue.getLast();
	}
}

