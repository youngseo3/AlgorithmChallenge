import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		MyDequeue dq = new MyDequeue();
 
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String[] s = br.readLine().split(" ");
			
			switch(s[0]) {
			
				case "push_front" : {
					dq.pushFront(Integer.parseInt(s[1]));
					break;
				}
				
				case "push_back" : {
					dq.pushBack(Integer.parseInt(s[1]));
					break;
				}
				
				case "pop_front" : {
					sb.append(dq.popFront()).append('\n');
					break;
				}
				
				case "pop_back" : {
					sb.append(dq.popBack()).append('\n');
					break;
				}
				
				case "size" : {
					sb.append(dq.size()).append('\n');
					break;
				}
				
				case "empty" : {
					sb.append(dq.empty()).append('\n');
					break;
				}
				
				case "front" : {
					sb.append(dq.front()).append('\n');
					break;
				}
				
				case "back" : {
					sb.append(dq.back()).append('\n');
					break;
				}
			}
			
		}
		System.out.println(sb);
	}

	static class MyDequeue {
		int[] ary = new int[10000];
		int front = 0;
		int back = 0;
		int size = 0;
		
		void pushFront(int x) {
			ary[front] = x;
			front = (front - 1 + 10000) % 10000;
			size++;
		}
		
		void pushBack(int x) {
			back = (back + 1) % 10000;
			size++;
			ary[back] = x;

		}

		int popFront() {
			if (size == 0) {
				return -1;
			} 
			
			int result = ary[(front + 1) % 10000];
			front = (front + 1) % 10000;
			size--;	
			
			return result;
		}
		
		int popBack() {
			if (size == 0) {
				return -1;
			} 
			
			int result = ary[back];
			back = (back - 1 + 10000) % 10000;
			size--;
			
			return result;
		}
		
		int size() {
			return size;
		}

		int empty() {
			if(size == 0) {
				return 1;
			}
			
			return 0;
		}

		int front() {
			if(size == 0) {
				return -1;
			}
			return ary[(front + 1) % 10000];
		}

		int back() {
			if(size == 0) {
				return -1;
			}
			
			return ary[back];
		}
	}
}
