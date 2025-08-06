import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;  // 부모 노드를 저장하는 배열
    static boolean[] deleted;  // 삭제된 노드를 표시
    static int root;  // 루트 노드
    static int deleteNode;  // 삭제할 노드
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        parent = new int[N];
        deleted = new boolean[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if(parent[i] == -1) {
                root = i;  // 루트 노드 찾기
            }
        }
        
        deleteNode = Integer.parseInt(br.readLine());
        
        // 삭제할 노드와 그 하위 트리 모두 삭제 표시
        deleteSubtree(deleteNode);
        
        // 리프 노드 개수 세기
        int leafCount = 0;
        for(int i = 0; i < N; i++) {
            if(!deleted[i] && isLeaf(i)) {
                leafCount++;
            }
        }
        
        System.out.println(leafCount);
    }
    
    // 특정 노드와 그 하위 트리를 모두 삭제 표시
    static void deleteSubtree(int node) {
        deleted[node] = true;
        
        // 해당 노드를 부모로 하는 모든 자식들도 삭제
        for(int i = 0; i < N; i++) {
            if(!deleted[i] && parent[i] == node) {
                deleteSubtree(i);
            }
        }
    }
    
    // 리프 노드인지 확인
    static boolean isLeaf(int node) {
        // 루트가 삭제된 경우
        if(deleted[root]) {
            return false;
        }
        
        // 자식이 있는지 확인
        for(int i = 0; i < N; i++) {
            if(!deleted[i] && parent[i] == node) {
                return false;  // 자식이 있으면 리프가 아님
            }
        }
        
        return true;  // 자식이 없으면 리프 노드
    }
}