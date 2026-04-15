import java.util.*;

class Solution {
    static int m;       // 심사관 명수 
    static long answer;
    
    public long solution(int n, int[] times) {
        this.m = times.length;
        this.answer = 0;
        
        Arrays.sort(times);
        
        // n명 처리 가능한 최소 시간 찾기 (이분탐색)
        long start = 1;
        long end = (long)times[m-1] * (long)n;
        while(start<=end){
            long mid = (start+end)/2;
            
            // mid 시간 동안 처리 가능한 사람 수 계산하기 
            long count = 0;
            for(int i=0;i<m;i++){
                count += mid/times[i];
                
                if(count>=n){
                    break;
                }
            }
            
            // 2. n명 이상 처리 가능한지 판별하기 
            if (count >= n) {
                answer = mid;   // 가능한 시간이니까 저장
                end = mid - 1; // 시간 충분 -> 더 작은 시간 탐색
            } else {
                start = mid + 1;  // 시간 부족 -> 늘려야 함
            }
        }
        
        return answer;
    }
}
