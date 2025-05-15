import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        String[] nums = {"ZRO", "ONE", "TWO", "THR", "FOR", "FIV", "SIX", "SVN", "EGT", "NIN"};
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	String testCase = st.nextToken();
        	int wordCount = Integer.parseInt(st.nextToken());
        	
        	String[] words = new String[wordCount];
        	int[] wordsTI = new int[wordCount];
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < wordCount; i++) {
        		words[i] = st.nextToken();
        	}
        	
        	for(int i = 0; i < wordCount; i++) {
        		for(int j = 0; j < nums.length; j++) {
            		words[i] = words[i].replace(nums[j], String.valueOf(j));
            	}        	
        	}
        	
        	for(int i = 0; i < wordCount; i++) {
        		wordsTI[i] = Integer.parseInt(words[i]);
        	}
        	
        	Arrays.sort(wordsTI);
        	
        	for(int i = 0; i < wordCount; i++) {
    			words[i] = nums[wordsTI[i]];
        	}
        	
        	sb.append(testCase + "\n");
        	for(int i = 0; i < wordCount; i++) {
        		sb.append(words[i] + " ");
        	}
        	sb.append("\n");
        }
        
        System.out.println(sb);
	}

}
