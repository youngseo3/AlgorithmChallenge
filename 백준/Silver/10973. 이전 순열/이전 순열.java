import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
		N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        if(behindPermutation()) {
        	for(int i = 0; i < N; i++) {
            	sb.append(arr[i] + " ");
            }
        } else {
        	sb.append("-1");
        }
        
        System.out.println(sb);
	}
	
	static boolean behindPermutation() {
		int i = N - 1;
		while(i > 0 && arr[i - 1] < arr[i]) {
			i--;
		}
		
		if (i == 0) {
            return false;
        }
		
		int j = N - 1;
		while(arr[j] > arr[i - 1]) {
			j--;
		}
		
		swap(i - 1, j);
		
		j = N - 1;
		while(i < j) {
			swap(i, j);
			i++;
			j--;
		}
		
		return true;
	}
	
	static void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
