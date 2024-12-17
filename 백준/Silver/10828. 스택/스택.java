import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Stack st = new Stack();
		while(N > 0) {
			String command = sc.next();
			switch(command) {
				case "push":
					int x = sc.nextInt();
					st.push(x);
					break;
				case "pop":
					System.out.println(st.pop());
					break;
				case "size":
					System.out.println(st.size());
					break;
				case "empty":
					System.out.println(st.empty());
					break;
				case "top":
					System.out.println(st.top());
					break;
			}

			N--;
		}
	}
	
}

class Stack {
	int pos = -1;
	List<Integer> node = new ArrayList<>();
	
	public void push(int x) {
		node.add(x);
		pos++;
	}
	
	public int pop() {
		if(node.isEmpty()) {
			return -1;
		}
		return node.remove(pos--);
	}
	
	public int size() {
		return node.size();
	}
	
	public int empty() {
		if(node.isEmpty()) {
			return 1;
		}
		
		return 0;
	}
	
	public int top() {
		if(node.isEmpty()) {
			return -1;
		}
		
		return node.get(pos);
		
	}
}
