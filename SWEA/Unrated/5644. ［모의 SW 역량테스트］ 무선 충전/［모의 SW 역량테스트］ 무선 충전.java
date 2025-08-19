import java.io.*;
import java.util.*;

public class Solution {
    static int M, bcNum;
    static int[] A, B;
    static BC[] bcs;
    static int[] dx = {0, -1, 0, 1, 0}; // 0:정지, 1:상, 2:우, 3:하, 4:좌
    static int[] dy = {0, 0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            bcNum = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            A = new int[M + 1];
            A[0] = 0; // 0초에는 움직이지 않음
            for(int i = 1; i <= M; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            
            st = new StringTokenizer(br.readLine());
            B = new int[M + 1];
            B[0] = 0; // 0초에는 움직이지 않음
            for(int i = 1; i <= M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
            
            bcs = new BC[bcNum];
            for(int i = 0; i < bcNum; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int coverage = Integer.parseInt(st.nextToken());
                int performance = Integer.parseInt(st.nextToken());
                
                Location loc = new Location(y - 1, x - 1); // x, y 순서 바꿈!
                bcs[i] = new BC(loc, coverage, performance);
            }
            
            int result = simulate();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        
        System.out.print(sb);
    }
    
    private static int simulate() {
        int aX = 0, aY = 0; // A 시작 위치 (0,0)
        int bX = 9, bY = 9; // B 시작 위치 (9,9)
        int totalCharge = 0;
        
        for(int t = 0; t <= M; t++) {
            // t초에 따른 이동
            aX += dx[A[t]];
            aY += dy[A[t]];
            bX += dx[B[t]];
            bY += dy[B[t]];
            
            // 현재 위치에서 최대 충전량 계산
            totalCharge += getMaxCharge(aX, aY, bX, bY);
        }
        
        return totalCharge;
    }
    
    private static int getMaxCharge(int aX, int aY, int bX, int bY) {
        // A와 B가 각각 접근 가능한 BC들 찾기
        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        
        for(int i = 0; i < bcNum; i++) {
            BC bc = bcs[i];
            
            // A가 접근 가능한지 체크
            int aDist = Math.abs(bc.loc.x - aX) + Math.abs(bc.loc.y - aY);
            if(aDist <= bc.coverage) {
                aList.add(i);
            }
            
            // B가 접근 가능한지 체크  
            int bDist = Math.abs(bc.loc.x - bX) + Math.abs(bc.loc.y - bY);
            if(bDist <= bc.coverage) {
                bList.add(i);
            }
        }
        
        int maxCharge = 0;
        
        // 모든 조합 확인 (충전 안 하는 경우 포함)
        // A의 선택: 충전 안 함(-1) 또는 접근 가능한 BC들
        List<Integer> aChoices = new ArrayList<>();
        aChoices.add(-1); // 충전 안 함
        aChoices.addAll(aList);
        
        // B의 선택: 충전 안 함(-1) 또는 접근 가능한 BC들  
        List<Integer> bChoices = new ArrayList<>();
        bChoices.add(-1); // 충전 안 함
        bChoices.addAll(bList);
        
        for(int aChoice : aChoices) {
            for(int bChoice : bChoices) {
                int charge = 0;
                
                if(aChoice == -1 && bChoice == -1) {
                    // 둘 다 충전 안 함
                    charge = 0;
                } else if(aChoice != -1 && bChoice == -1) {
                    // A만 충전
                    charge = bcs[aChoice].performance;
                } else if(aChoice == -1 && bChoice != -1) {
                    // B만 충전
                    charge = bcs[bChoice].performance;
                } else {
                    // 둘 다 충전
                    if(aChoice == bChoice) {
                        // 같은 BC 공유
                        charge = bcs[aChoice].performance;
                    } else {
                        // 서로 다른 BC
                        charge = bcs[aChoice].performance + bcs[bChoice].performance;
                    }
                }
                
                maxCharge = Math.max(maxCharge, charge);
            }
        }
        
        return maxCharge;
    }
    
    static class Location {
        int x, y;
        
        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static class BC {
        Location loc;
        int coverage, performance;
        
        public BC(Location loc, int coverage, int performance) {
            this.loc = loc;
            this.coverage = coverage;
            this.performance = performance;
        }
    }
}